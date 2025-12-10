package vallegrade.edu.pe.controller;

import vallegrade.edu.pe.service.EquipoService;
import vallegrade.edu.pe.model.Equipo;
import java.util.List;

public class EquipoController {
    private EquipoService service;

    public EquipoController() {
        this.service = new EquipoService();
    }

    public boolean crearEquipo(Equipo equipo) {
        return service.crearEquipo(equipo);
    }

    public List<Equipo> obtenerTodos() {
        return service.obtenerTodos();
    }

    public Equipo obtenerPorId(int id) {
        return service.obtenerPorId(id);
    }

    public boolean actualizarEquipo(Equipo equipo) {
        return service.actualizarEquipo(equipo);
    }

    public boolean eliminarEquipo(int id) {
        return service.eliminarEquipo(id);
    }

    public List<Equipo> buscar(String criterio, String valor) {
        return service.buscar(criterio, valor);
    }

    public boolean existeCodigo(String codigo) {
        return service.existeCodigo(codigo);
    }
}
