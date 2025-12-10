package vallegrade.edu.pe.database;

import vallegrade.edu.pe.model.Equipo;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EquipoDAO {

    public boolean crearEquipo(Equipo equipo) {
        String sql = "INSERT INTO equipos (codigo, tipo, marcas, modelo, so, almacenamiento, ram, estado, mantenimiento, fecha_registro) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, equipo.getCodigo());
            pstmt.setString(2, equipo.getTipo());
            pstmt.setString(3, equipo.getMarcas());
            pstmt.setString(4, equipo.getModelo());
            pstmt.setString(5, equipo.getSo());
            pstmt.setInt(6, equipo.getAlmacenamiento());
            pstmt.setInt(7, equipo.getRam());
            pstmt.setString(8, equipo.getEstado());
            pstmt.setDate(9, equipo.getMantenimiento() != null ? Date.valueOf(equipo.getMantenimiento()) : null);
            pstmt.setDate(10, Date.valueOf(equipo.getFechaRegistro()));

            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.err.println("Error al crear equipo: " + e.getMessage());
            return false;
        }
    }

    public List<Equipo> obtenerTodos() {
        List<Equipo> equipos = new ArrayList<>();
        String sql = "SELECT id, codigo, tipo, marcas, modelo, so, almacenamiento, ram, estado, mantenimiento, fecha_registro FROM equipos";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                equipos.add(mapearEquipo(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener equipos: " + e.getMessage());
        }
        return equipos;
    }

    public Equipo obtenerPorId(int id) {
        String sql = "SELECT id, codigo, tipo, marcas, modelo, so, almacenamiento, ram, estado, mantenimiento, fecha_registro FROM equipos WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapearEquipo(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener equipo por ID: " + e.getMessage());
        }
        return null;
    }

    public boolean actualizarEquipo(Equipo equipo) {
        String sql = "UPDATE equipos SET codigo = ?, tipo = ?, marcas = ?, modelo = ?, so = ?, " +
                     "almacenamiento = ?, ram = ?, estado = ?, mantenimiento = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, equipo.getCodigo());
            pstmt.setString(2, equipo.getTipo());
            pstmt.setString(3, equipo.getMarcas());
            pstmt.setString(4, equipo.getModelo());
            pstmt.setString(5, equipo.getSo());
            pstmt.setInt(6, equipo.getAlmacenamiento());
            pstmt.setInt(7, equipo.getRam());
            pstmt.setString(8, equipo.getEstado());
            pstmt.setDate(9, equipo.getMantenimiento() != null ? Date.valueOf(equipo.getMantenimiento()) : null);
            pstmt.setInt(10, equipo.getId());

            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar equipo: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarFisico(int id) {
        String sql = "DELETE FROM equipos WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar equipo: " + e.getMessage());
            return false;
        }
    }

    public List<Equipo> buscar(String criterio, String valor) {
        List<Equipo> equipos = new ArrayList<>();
        String sql = "";

        if ("codigo".equals(criterio)) {
            sql = "SELECT id, codigo, tipo, marcas, modelo, so, almacenamiento, ram, estado, mantenimiento, fecha_registro FROM equipos WHERE codigo LIKE ?";
        } else if ("tipo".equals(criterio)) {
            sql = "SELECT id, codigo, tipo, marcas, modelo, so, almacenamiento, ram, estado, mantenimiento, fecha_registro FROM equipos WHERE tipo LIKE ?";
        } else if ("marcas".equals(criterio)) {
            sql = "SELECT id, codigo, tipo, marcas, modelo, so, almacenamiento, ram, estado, mantenimiento, fecha_registro FROM equipos WHERE marcas LIKE ?";
        } else if ("estado".equals(criterio)) {
            sql = "SELECT id, codigo, tipo, marcas, modelo, so, almacenamiento, ram, estado, mantenimiento, fecha_registro FROM equipos WHERE estado LIKE ?";
        }

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "%" + valor + "%");
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    equipos.add(mapearEquipo(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error en la busqueda: " + e.getMessage());
        }
        return equipos;
    }

    public boolean existeCodigo(String codigo) {
        String sql = "SELECT COUNT(*) FROM equipos WHERE codigo = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, codigo);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al verificar codigo: " + e.getMessage());
        }
        return false;
    }

    public boolean existeCodigoExcluido(String codigo, int idExcluido) {
        String sql = "SELECT COUNT(*) FROM equipos WHERE codigo = ? AND id != ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, codigo);
            pstmt.setInt(2, idExcluido);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al verificar codigo: " + e.getMessage());
        }
        return false;
    }

    private Equipo mapearEquipo(ResultSet rs) throws SQLException {
        return new Equipo(
                rs.getInt("id"),
                rs.getString("codigo"),
                rs.getString("tipo"),
                rs.getString("marcas"),
                rs.getString("modelo"),
                rs.getString("so"),
                rs.getInt("almacenamiento"),
                rs.getInt("ram"),
                rs.getString("estado"),
                rs.getDate("mantenimiento") != null ? rs.getDate("mantenimiento").toLocalDate() : null,
                rs.getDate("fecha_registro").toLocalDate()
        );
    }
}
