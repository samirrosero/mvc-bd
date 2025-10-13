package com.uniajc.mvn.controlador;

import java.util.ArrayList;
import java.util.List;
import com.uniajc.mvn.modelo.Profesor;
import com.uniajc.mvn.vista.VistaProfesor;

public class ControladorProfesor {
    private List<Profesor> profesores;
    private VistaProfesor vista;

    public ControladorProfesor(Profesor modelo, VistaProfesor vista) {
        this.vista = vista;
        this.profesores = new ArrayList<Profesor>();
    }


    public void actualizarVista() {
        vista.mostrarDetallesProfesor(profesores);
    }

    public void agregarProfesor(Profesor profesor) {
        this.profesores.add(profesor);
    }
    public List<Profesor> listarTodosLosProfesores() {
        return Profesor.obtenerTodosLosProfesores();
    }
    public void actualizarProfesor(Profesor profesor) {
        Profesor.actualizarProfesor(profesor);
    }
    public void eliminarProfesor(int id_profesor) {
        Profesor.eliminarProfesor(id_profesor);
    }
    
}