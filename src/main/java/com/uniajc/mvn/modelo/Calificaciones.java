package com.uniajc.mvn.modelo;

public class Calificaciones {
    private int calificacion_id;
    private int estudiante_id;
    private int componente_evaluacion_id;
    private int nota;
    private String comentarios_calificacion;

    public Calificaciones(int calificacion_id, int estudiante_id, int componente_evaluacion_id, int nota, String comentarios_calificacion){
        this.calificacion_id = calificacion_id;
        this.estudiante_id = estudiante_id;
        this.componente_evaluacion_id = componente_evaluacion_id;
        this.nota = nota;
        this.comentarios_calificacion = comentarios_calificacion;
    }
    public int getCalificacion_id() {
        return this.calificacion_id;
    }
    public void setCalificacion_id(int calificacion_id) {
        this.calificacion_id = calificacion_id;
    }
    public int getEstudiante_id() {
        return this.estudiante_id;
    }
    public void setEstudiante_id(int estudiante_id) {
        this.estudiante_id = estudiante_id;
    }
    public int getComponente_evaluacion_id() {
        return this.componente_evaluacion_id;
    }
    public void setComponente_evaluacion_id(int componente_evaluacion_id) {
        this.componente_evaluacion_id = componente_evaluacion_id;
    }
    public int getNota() {
        return this.nota;
    }
    public void setNota(int nota) {
        this.nota = nota;
    }
    public String getComentarios_calificacion() {
        return this.comentarios_calificacion;
    } 
    public void setComentarios_calificacion(String comentarios_calificacion) {
        this.comentarios_calificacion = comentarios_calificacion;
    }
}