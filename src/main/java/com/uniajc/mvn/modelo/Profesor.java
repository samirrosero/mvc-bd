package com.uniajc.mvn.modelo;

public class Profesor {
    private int id;
    private String nombre;
    private String materia;
    private String correo_electronico;

    public Profesor(int id, String nombre, String materia, String correo_electronico) {
        this.id = id;
        this.nombre = nombre;
        this.materia = materia;
        this.correo_electronico = correo_electronico;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMateria() {
        return this.materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }
    public String getCorreoElectronico() {
        return this.correo_electronico;
    }
    public void setCorreoElectronico(String correo_electronico) {
        this.correo_electronico = correo_electronico;
    }
}
