package com.uniajc.mvn.controlador;

import java.util.ArrayList;
import java.util.List;
import com.uniajc.mvn.vista.VistaEstudiante;
import com.uniajc.mvn.modelo.Estudiante;

public class ControladorEstudiante {
    private List<Estudiante> estudiantes;
    private VistaEstudiante vista;

    public ControladorEstudiante(Estudiante modelo, VistaEstudiante vista) {
        this.vista = vista;
        this.estudiantes = new ArrayList<Estudiante>();

    }

    public void actualizarVista() {
        vista.mostrarDetallesEstudiante(estudiantes);
    }

    public void agregarEstudiante(Estudiante estudiante) {
        this.estudiantes.add(estudiante);
    }

    public List<Estudiante> listarTodosLosEstudiantes() {
        return Estudiante.obtenerTodosLosEstudiantes();
    }

    public void actualizarEstudiante(Estudiante estudiante) {
        Estudiante.actualizarEstudiante(estudiante);
    }

    public void eliminarEstudiante(int id_estudiante) {
        Estudiante.eliminarEstudiante(id_estudiante);
    }

}
