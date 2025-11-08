package com.uniajc.mvn.modelo;


public class Docentes{
    private int docente_id;
    private String nombre_docente;
    private String identificacion;
    private String tipo_identificacion;
    private String genero;
    private String correo;
    private String titulo_estudios;
    private String idiomas;
    private String certificaciones;
    
    public Docentes(int docente_id, String nombre_docente, String identificacion, String tipo_identificacion, 
    String genero, String correo, String titulo_estudios, String idiomas, String certificaciones) {
        this.docente_id = docente_id;
        this.nombre_docente = nombre_docente;
        this.identificacion = identificacion;
        this.tipo_identificacion = tipo_identificacion;
        this.genero = genero;
        this.correo = correo;
        this.titulo_estudios = titulo_estudios;
        this.idiomas = idiomas;
        this.certificaciones = certificaciones;
    }
    public int getDocente_id() {
        return this.docente_id;
    }
    public void setDocente_id(int docente_id) {
        this.docente_id = docente_id;
    }
    public String getNombre_docente() {
        return this.nombre_docente;
    }
    public void setNombre_docente(String nombre_docente) {
        this.nombre_docente = nombre_docente;
    }
    public String getIdentificacion() {
        return this.identificacion;
    }
    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }
    public String getTipo_identificacion() {
        return this.tipo_identificacion;
    }
    public void setTipo_identificacion(String tipo_identificacion) {
        this.tipo_identificacion = tipo_identificacion;
    }
    public String getGenero() {
        return this.genero;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }
    public String getCorreo() {
        return this.correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public String getTitulo_estudios() {
        return this.titulo_estudios;
    }
    public void setTitulo_estudios(String titulo_estudios) {
        this.titulo_estudios = titulo_estudios;
    }
    public String getIdiomas() {
        return this.idiomas;
    }
    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }
    public String getCertificaciones() {
        return this.certificaciones;
    }
    public void setCertificaciones(String certificaciones) {
        this.certificaciones = certificaciones;
    }
    
}