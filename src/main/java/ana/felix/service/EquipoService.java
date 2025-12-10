package ana.felix.service;

import ana.felix.database.EquipoDAO;
import ana.felix.model.Equipo;

import java.util.List;

public class EquipoService {
    private EquipoDAO dao;

    public EquipoService() {
        this.dao = new EquipoDAO();
    }

    // Método para crear un nuevo equipo, validando que el código no exista
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

    // Método para obtener todos los equipos
    public List<Equipo> obtenerTodos() {
        return dao.obtenerTodos();
    }

    // Método para obtener un equipo por su ID, validando que el ID sea válido
    public Equipo obtenerPorId(int id) {
        if (id <= 0) {
            System.err.println("Error: ID invalido");
            return null;
        }
        return dao.obtenerPorId(id);
    }

    // Método para actualizar un equipo existente, validando ID y código único
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

    // Método para eliminar un equipo por su ID, validando que el ID sea válido
    public boolean eliminarEquipo(int id) {
        if (id <= 0) {
            System.err.println("Error: ID invalido");
            return false;
        }
        return dao.eliminarFisico(id);
    }

    // Método para buscar equipos según un criterio, devolviendo todos si el valor está vacío
    public List<Equipo> buscar(String criterio, String valor) {
        if (valor == null || valor.trim().isEmpty()) {
            return obtenerTodos();
        }
        return dao.buscar(criterio, valor);
    }

    // Método para verificar si un código de equipo ya existe
    public boolean existeCodigo(String codigo) {
        return dao.existeCodigo(codigo);
    }
}
