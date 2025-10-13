package com.uniajc.mvn.controlador;

import java.util.ArrayList;
import java.util.List;
import com.uniajc.mvn.modelo.Curso;
import com.uniajc.mvn.modelo.CursoDao;

public class ControladorCurso {
    private final CursoDao cursoDao;

    public ControladorCurso() {
        cursoDao = new CursoDao();
    }

    public boolean agregarCurso(String nombreCurso, int idProfesor, int idEstudiante) {
        Curso curso = new Curso(0, nombreCurso, idEstudiante, idProfesor);
        return cursoDao.insertarCurso(curso);
    }

    public List<Curso> listarCursos() {
        return cursoDao.obtenerTodosLosCursos("", new ArrayList<>());
    }

    public boolean actualizarCurso(int id, String nombreCurso, int idProfesor, int idEstudiante) {
        Curso curso = new Curso(id, nombreCurso, idEstudiante, idProfesor);
        return cursoDao.actualizarCurso(curso);
    }

    public boolean eliminarCurso(int id) {
        return cursoDao.eliminarCurso(id);
    }
}
