package com.uniajc.mvn.controlador;

import com.uniajc.mvn.modelo.Estudiante;
import com.uniajc.mvn.modelo.EstudianteDao;
import java.util.ArrayList;

public class EstudianteController {

    private EstudianteDao estudianteDao;

    public EstudianteController() {
        estudianteDao = new EstudianteDao();
    }

    public boolean agregarEstudiante(String nombre, int edad) {
        Estudiante estudiante = new Estudiante(0, nombre, edad);
        return estudianteDao.insertarEstudiante(estudiante);
    }

    public ArrayList<Estudiante> listarEstudiantes() {
        return estudianteDao.obtenerTodosLosEstudiantes("", new ArrayList<>());
    }

    public boolean actualizarEstudiante(int id, String nombre, int edad) {
        Estudiante estudiante = new Estudiante(id, nombre, edad);
        return estudianteDao.actualizarEstudiante(estudiante);
    }

    public boolean eliminarEstudiante(int id) {
        return estudianteDao.eliminarEstudiante(id);
    }
}
