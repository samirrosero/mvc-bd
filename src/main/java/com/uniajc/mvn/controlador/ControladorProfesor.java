package com.uniajc.mvn.controlador;

import java.util.List;
import com.uniajc.mvn.modelo.Profesor;

public class ControladorProfesor {
      public void insertar(Profesor profesor) {
        Profesor.insertarProfesor(profesor);
    }

    public void actualizar(Profesor profesor) {
        Profesor.actualizarProfesor(profesor);
    }

    public void eliminar(int idProfesor) {
        Profesor.eliminarProfesor(idProfesor);
    }

    public Profesor buscarPorId(int idProfesor) {
        return Profesor.buscarPorId(idProfesor);
    }

    public List<Profesor> listarTodos() {
        return Profesor.obtenerTodosLosProfesores();
    }
}