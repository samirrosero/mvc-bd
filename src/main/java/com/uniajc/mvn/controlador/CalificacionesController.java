package com.uniajc.mvn.controlador;

import com.uniajc.mvn.modelo.*;
import java.util.List;

public class CalificacionesController {

    private final CalificacionesDao dao = new CalificacionesDao();

    // Registrar una calificación
    public boolean registrar(Calificaciones c) {
        return dao.registrarCalificacion(c);
    }

    // Obtener las calificaciones de un estudiante en un curso
    public Calificaciones obtenerPorEstudiante(int estudiante_id, int curso_id) {
        return dao.obtenerCalificacionesEstudiantes(estudiante_id, curso_id);
    }

    // Listar todas las calificaciones (si tienes el sp_listar_calificaciones)
    public List<Calificaciones> listar() {
        return dao.listarCalificaciones();
    }

    // Actualizar una calificación
    public boolean actualizar(Calificaciones c) {
        return dao.actualizarCalificacion(c);
    }

    // Eliminar calificación por ID
    public boolean eliminar(int id) {
        return dao.eliminarCalificacion(id);
    }
}
