package com.uniajc.mvn.controlador;

import java.util.ArrayList;
import java.util.List;

import com.uniajc.mvn.modelo.Curso;
import com.uniajc.mvn.vista.VistaCurso;

public class ControladorCurso {
    private List<Curso> curso;
    public VistaCurso vista;

    public ControladorCurso(Curso modelo, VistaCurso vista) {
        this.vista = vista;
        this.curso = new ArrayList<Curso>();

    }
    public void actualizarVista() {
        vista.mostrarDetallesCurso(curso);
    }
    public void agregarCurso(Curso curso) {
        this.curso.add(curso);
    }

    public List<Curso> listarTodosLosCursos() {
        return Curso.obtenerTodosLosCursos();
    }

    public void actualizarCurso(Curso curso) {
        Curso.actualizarCurso(curso);
    }
    public void eliminarCurso(int id_curso) {
        Curso.eliminarCurso(id_curso);
    }

}
