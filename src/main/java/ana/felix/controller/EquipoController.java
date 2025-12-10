package ana.felix.controller;

import ana.felix.model.Equipo;
import ana.felix.service.EquipoService;

import java.util.List;

public class EquipoController {
    private EquipoService service;

    public EquipoController() {
        this.service = new EquipoService();
    }

    // Método para crear un nuevo equipo
    public boolean crearEquipo(Equipo equipo) {
        return service.crearEquipo(equipo);
    }

    // Método para obtener todos los equipos
    public List<Equipo> obtenerTodos() {
        return service.obtenerTodos();
    }

    // Método para obtener un equipo por su ID
    public Equipo obtenerPorId(int id) {
        return service.obtenerPorId(id);
    }

    // Método para actualizar un equipo existente
    public boolean actualizarEquipo(Equipo equipo) {
        return service.actualizarEquipo(equipo);
    }

    // Método para eliminar un equipo por su ID
    public boolean eliminarEquipo(int id) {
        return service.eliminarEquipo(id);
    }

    // Método para buscar equipos según un criterio y valor
    public List<Equipo> buscar(String criterio, String valor) {
        return service.buscar(criterio, valor);
    }

    // Método para verificar si un código de equipo ya existe
    public boolean existeCodigo(String codigo) {
        return service.existeCodigo(codigo);
    }
}
