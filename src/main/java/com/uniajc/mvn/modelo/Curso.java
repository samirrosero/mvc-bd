package com.uniajc.mvn.modelo;

public class Curso {
    private int idCurso;
    private String nombreCurso;
    private int idEstudiante;
    private int idProfesor;
    private String nombreProfesor;
    private String materiaProfesor;
    private String nombreEstudiante;

    public Curso(int idCurso, String nombreCurso, int idEstudiante, int idProfesor) {
        this.idCurso = idCurso;
        this.nombreCurso = nombreCurso;
        this.idEstudiante = idEstudiante;
        this.idProfesor = idProfesor;
    }

    public int getIdCurso() { return idCurso; }
    public void setIdCurso(int idCurso) { this.idCurso = idCurso; }

    public String getNombreCurso() { return nombreCurso; }
    public void setNombreCurso(String nombreCurso) { this.nombreCurso = nombreCurso; }

    public int getIdEstudiante() { return idEstudiante; }
    public void setIdEstudiante(int idEstudiante) { this.idEstudiante = idEstudiante; }

    public int getIdProfesor() { return idProfesor; }
    public void setIdProfesor(int idProfesor) { this.idProfesor = idProfesor; }

    public String getNombreProfesor() { return nombreProfesor; }
    public void setNombreProfesor(String nombreProfesor) { this.nombreProfesor = nombreProfesor; }

    public String getMateriaProfesor() { return materiaProfesor; }
    public void setMateriaProfesor(String materiaProfesor) { this.materiaProfesor = materiaProfesor; }

    public String getNombreEstudiante() { return nombreEstudiante; }
    public void setNombreEstudiante(String nombreEstudiante) { this.nombreEstudiante = nombreEstudiante; }

    @Override
    public String toString() {
        return nombreCurso;
    }
}
