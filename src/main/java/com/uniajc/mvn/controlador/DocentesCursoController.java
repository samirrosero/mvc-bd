package com.uniajc.mvn.controlador;

import com.uniajc.mvn.modelo.*;
import java.util.List;

public class DocentesCursoController {
    private final DocentesCursoDao dao = new DocentesCursoDao();

    public boolean asignar(int docente_id, int curso_id) { return dao.asignarDocenteACurso(docente_id, curso_id); }
    public List<Integer> obtenerCursosPorDocente(int docente_id) { return dao.obtenerCursosPorDocente(docente_id); }
    public List<Integer> obtenerDocentesPorCurso(int curso_id) { return dao.obtenerDocentesPorCurso(curso_id); }
    public boolean eliminarAsignacion(int docente_id, int curso_id) { return dao.eliminarAsignacion(docente_id, curso_id); }
}
