package com.uniajc.mvn.modelo;

public class Curso {
    private int id_curso;
    private String curso;
    private int id_profesor;
    private int id_estudiante;

    public Curso(int id_curso, String curso, int id_profesor, int id_estudiante) {
        this.id_curso = id_curso;
        this.curso = curso;
        this.id_profesor = id_profesor;
        this.id_estudiante = id_estudiante;
    }
    public int getIdCurso() {
        return this.id_curso;
    }   
    public void setIdCurso(int id_curso) {
        this.id_curso = id_curso;
    }
    public String getCurso() {
        return this.curso;
    }
    public void setCurso(String curso) {
        this.curso = curso;
    }
    public int getIdProfesor() {
        return this.id_profesor;
    }
    public void setIdProfesor(int id_profesor) {
        this.id_profesor = id_profesor;
    }
    public int getIdEstudiante() {
        return this.id_estudiante;
    }
    public void setIdEstudiante(int id_estudiante) {
        this.id_estudiante = id_estudiante;
    }
    
}
