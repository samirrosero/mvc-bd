package com.uniajc.mvn.vista;

import java.util.List;

import com.uniajc.mvn.modelo.Curso;
import com.uniajc.mvn.modelo.Estudiante;

public class Vista {
    public void mostrarDetallesEstudiante(List<Estudiante> estudiantes) {
       /*  for (Estudiante estudiante : estudiantes) {
            System.out.println("Detalles del Estudiante:");
            System.out.println("Nombre: " + estudiante.getNombre());
            System.out.println("Edad: " + estudiante.getEdad());
        }*/
        estudiantes.forEach(estudiante -> {
           System.out.println("Imprimiendo desde la vista: ");
        // System.out.println("Nombre: " + estudiante.getNombre() + " - " + "Edad: " + estudiante.getEdad());
             
    });
    }

    public void mostrarDetallesCurso(List<Curso> curso) {
        /*for (Estudiante curso : cursos) {
            System.out.println("Detalles del Curso:");
            System.out.println("Nombre: " + curso.getNombre());
            System.out.println("Edad: " + curso.getEdad());
        }*/
        curso.forEach(cursos -> {
            System.out.println("Imprimiendo desde la vista: ");
         // System.out.println("Nombre: " + estudiante.getNombre() + " - " + "Edad: " + estudiante.getEdad());  
        });        
    }
    

    
}

