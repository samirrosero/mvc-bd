package com.uniajc.mvn.modelo;

public class Cursos {
    private int curso_id;
    private String nombre_curso;
    private int periodo_academico_id;
    private int docente_id;
    private String descripcion_curso;

    public Cursos(int curso_id, String nombre_curso, int periodo_academico_id, int docente_id, String descripcion_curso){
        this.curso_id = curso_id;
        this.nombre_curso = nombre_curso;
        this.periodo_academico_id = periodo_academico_id;
        this.docente_id = docente_id;
        this.descripcion_curso = descripcion_curso;
    }
    public int getCurso_id() {
        return this.curso_id;
    }
    public void setCurso_id(int curso_id) {
        this.curso_id = curso_id;
    }
    public String getNombre_curso() {
        return this.nombre_curso;
    }
    public void setNombre_curso(String nombre_curso) {
        this.nombre_curso = nombre_curso;
    }
    public int getPeriodo_academico_id() {
        return this.periodo_academico_id;
    }
    public void setPeriodo_academico_id(int periodo_academico_id) {
        this.periodo_academico_id = periodo_academico_id;
    }
    public int getDocente_id() {
        return this.docente_id;
    }
    public void setDocente_id(int docente_id) {
        this.docente_id = docente_id;
    }   
    public String getDescripcion_curso() {
        return this.descripcion_curso;
    }
    public void setDescripcion_curso(String descripcion_curso) {
        this.descripcion_curso = descripcion_curso;
    }
    
}
