package com.uniajc.mvn.modelo;

public class ComponenteEvaluacion {
    private int componente_evaluacion_id;
    private int corte_evaluacion_id;
    private String nombre_componente;
    private Double porcentaje;

    public ComponenteEvaluacion (int componente_evaluacion_id, int corte_evaluacion_id, String nombre_componente, Double porcentaje){
        this.componente_evaluacion_id = componente_evaluacion_id;
        this.corte_evaluacion_id = corte_evaluacion_id;
        this.nombre_componente = nombre_componente;
        this.porcentaje = porcentaje;
        }
    public int getComponente_evaluacion_id() {
        return this.componente_evaluacion_id;   
    }
    public void setComponente_evaluacion_id(int componente_evaluacion_id) {
        this.componente_evaluacion_id = componente_evaluacion_id;
    }
    public int getCorte_evaluacion_id() {
        return this.corte_evaluacion_id;
    }
    public void setCorte_evaluacion_id(int corte_evaluacion_id) {
        this.corte_evaluacion_id = corte_evaluacion_id;
    }
    public String getNombre_componente() {
        return this.nombre_componente;
    }
    public void setNombre_componente(String nombre_componente) {
        this.nombre_componente = nombre_componente;
    }
    public Double getPorcentaje() {
        return this.porcentaje;
    } 
    public void setPorcentaje(Double porcentaje) {
        this.porcentaje = porcentaje;
    }
}

