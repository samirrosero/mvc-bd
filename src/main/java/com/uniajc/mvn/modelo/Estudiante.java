package com.uniajc.mvn.modelo;


public class Estudiante {
    private int id_estudiante;
    private String nombre;
    private int edad;

    public Estudiante(int id_estudiante, String nombre, int edad) {
        this.id_estudiante = id_estudiante;
        this.nombre = nombre;
        this.edad = edad;
    }

    public int getId() {
        return this.id_estudiante;
    }

    public void setId(int id_estudiante) {
        this.id_estudiante = id_estudiante;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return this.edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
}