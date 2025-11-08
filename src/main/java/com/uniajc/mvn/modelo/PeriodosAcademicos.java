package com.uniajc.mvn.modelo;

import java.sql.Date;

public class PeriodosAcademicos {
    private int periodo_academico_id;
    private String nombre_periodo;
    private Date fecha_inicio;
    private Date fecha_fin;

    public PeriodosAcademicos(int periodo_academico_id, String nombre_periodo, Date fecha_inicio, Date fecha_fin){
        this.periodo_academico_id = periodo_academico_id;
        this.nombre_periodo = nombre_periodo;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
    }
    public int getPeriodo_academico_id() {
        return this.periodo_academico_id;
    }
    public void setPeriodo_academico_id(int periodo_academico_id) {
        this.periodo_academico_id = periodo_academico_id;
    }
    public String getNombre_periodo() {
        return this.nombre_periodo;
    }
    public void setNombre_periodo(String nombre_periodo) {
        this.nombre_periodo = nombre_periodo;
    }
    public Date getFecha_inicio() {
        return this.fecha_inicio;
    }
    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }
    public Date getFecha_fin() {
        return this.fecha_fin;
    }
    public void setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
    }
}
