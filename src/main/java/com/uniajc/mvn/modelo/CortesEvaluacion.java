package com.uniajc.mvn.modelo;

public class CortesEvaluacion {
    private int corte_evaluacion_id;
    private int curso_id;
    private int periodo_academico_id;
    private String nombre_corte;
    private Double porcentaje;
    private String comentarios_cortes;

    public CortesEvaluacion(int corte_evaluacion_id, int curso_id, int periodo_academico_id, String nombre_corte, Double porcentaje, String comentarios_cortes){
        this.corte_evaluacion_id = corte_evaluacion_id;
        this.curso_id = curso_id;
        this.periodo_academico_id = periodo_academico_id;
        this.nombre_corte = nombre_corte;
        this.porcentaje = porcentaje;
        this.comentarios_cortes = comentarios_cortes;
    }
    public int getCorte_evaluacion_id() {
        return this.corte_evaluacion_id;
    }
    public void setCorte_evaluacion_id(int corte_evaluacion_id) {
        this.corte_evaluacion_id = corte_evaluacion_id;
    }
    public int getCurso_id() {
        return this.curso_id;
    }
    public void setCurso_id(int curso_id) {
        this.curso_id = curso_id;
    }
    public int getPeriodo_academico_id() {
        return this.periodo_academico_id;
    }
    public void setPeriodo_academico_id(int periodo_academico_id) {
        this.periodo_academico_id = periodo_academico_id;
    }
    public String getNombre_corte() {
        return this.nombre_corte;
    }
    public void setNombre_corte(String nombre_corte) {
        this.nombre_corte = nombre_corte;
    }
    public Double getPorcentaje() {
        return this.porcentaje;
    }
    public void setPorcentaje(Double porcentaje) {
        this.porcentaje = porcentaje;
    }
    public String getComentarios_cortes() {
        return this.comentarios_cortes;
    }
    public void setComentarios_cortes(String comentarios_cortes) {
        this.comentarios_cortes = comentarios_cortes;
    }
}
