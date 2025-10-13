package com.uniajc.mvn.controlador;
import com.uniajc.mvn.modelo.ProfesorDao;
import java.util.ArrayList;
import com.uniajc.mvn.modelo.Profesor;

public class ControladorProfesor {
   private ProfesorDao profesorDao;
   public ControladorProfesor (){
       profesorDao = new ProfesorDao();
    }
    public boolean agregarProfesor(String nombre, String materia, String correo_electronico) {
        Profesor profesor = new Profesor(0, nombre, materia, correo_electronico);
        return profesorDao.insertarProfesor(profesor);
    }
    public ArrayList<Profesor> listarProfesores() {
        return profesorDao.obtenerTodosLosProfesores("", new ArrayList<>());
    }

    public boolean actualizarProfesor(int id, String nombre, String materia, String correo_electronico) {
        Profesor profesor = new Profesor(id, nombre, materia, correo_electronico);
        return profesorDao.actualizarProfesor(profesor);
    }

    public boolean eliminarProfesor(int id) {
        return profesorDao.eliminarProfesor(id);
    }
}