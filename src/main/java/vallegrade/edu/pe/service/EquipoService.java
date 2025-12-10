package vallegrade.edu.pe.service;

import vallegrade.edu.pe.database.EquipoDAO;
import vallegrade.edu.pe.model.Equipo;
import java.util.List;

public class EquipoService {
    private EquipoDAO dao;

    public EquipoService() {
        this.dao = new EquipoDAO();
    }

    public boolean crearEquipo(Equipo equipo) {
        if (equipo.getCodigo() == null || equipo.getCodigo().trim().isEmpty()) {
            System.err.println("Error: El codigo es obligatorio");
            return false;
        }
        if (dao.existeCodigo(equipo.getCodigo())) {
            System.err.println("Error: El codigo ya existe");
            return false;
        }
        return dao.crearEquipo(equipo);
    }

    public List<Equipo> obtenerTodos() {
        return dao.obtenerTodos();
    }

    public Equipo obtenerPorId(int id) {
        if (id <= 0) {
            System.err.println("Error: ID invalido");
            return null;
        }
        return dao.obtenerPorId(id);
    }

    public boolean actualizarEquipo(Equipo equipo) {
        if (equipo.getId() <= 0) {
            System.err.println("Error: ID invalido");
            return false;
        }
        if (dao.existeCodigoExcluido(equipo.getCodigo(), equipo.getId())) {
            System.err.println("Error: El codigo ya existe");
            return false;
        }
        return dao.actualizarEquipo(equipo);
    }

    public boolean eliminarEquipo(int id) {
        if (id <= 0) {
            System.err.println("Error: ID invalido");
            return false;
        }
        return dao.eliminarFisico(id);
    }

    public List<Equipo> buscar(String criterio, String valor) {
        if (valor == null || valor.trim().isEmpty()) {
            return obtenerTodos();
        }
        return dao.buscar(criterio, valor);
    }

    public boolean existeCodigo(String codigo) {
        return dao.existeCodigo(codigo);
    }
}
