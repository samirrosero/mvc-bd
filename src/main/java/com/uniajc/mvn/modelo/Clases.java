package com.uniajc.mvn.modelo;

import java.sql.Date;

public class Clases {
    private int clase_id;
    private int curso_id;
    private int numero_clase;
    private Date fecha_clase;
    private String tema_clase;
    private String descripcion_clase;
    private String comentarios_clase;

    public Clases(int clase_id, int curso_id, int numero_clase, Date fecha_clase, String tema_clase, String descripcion_clase, String comentarios_clase){
        this.clase_id = clase_id;
        this.curso_id = curso_id;
        this.numero_clase = numero_clase;
        this.fecha_clase = fecha_clase;
        this.tema_clase = tema_clase;
        this.descripcion_clase = descripcion_clase;
        this.comentarios_clase = comentarios_clase;
    }
    public int getClase_id() {
        return this.clase_id;
    }
    public void setClase_id(int clase_id) {
        this.clase_id = clase_id;
    }
    public int getCurso_id() {
        return this.curso_id;
    }
    public void setCurso_id(int curso_id) {
        this.curso_id = curso_id;
    }
    public int getNumero_clase() {
        return this.numero_clase;
    }
    public void setNumero_clase(int numero_clase) {
        this.numero_clase = numero_clase;
    }
    public Date getFecha_clase() {
        return this.fecha_clase;
    }
    public void setFecha_clase(Date fecha_clase) {
        this.fecha_clase = fecha_clase;
    }

    public String getTema_clase() {
        return this.tema_clase;
    }
    public void setTema_clase(String tema_clase) {
        this.tema_clase = tema_clase;
    }
    public String getDescripcion_clase() {
        return this.descripcion_clase;
    }
    public void setDescripcion_clase(String descripcion_clase) {
        this.descripcion_clase = descripcion_clase;
    }
    public String getComentarios_clase() {
        return this.comentarios_clase;
    }
    public void setComentarios_clase(String comentarios_clase) {
        this.comentarios_clase = comentarios_clase;
    }
}
