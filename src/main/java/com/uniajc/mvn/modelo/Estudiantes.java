package com.uniajc.mvn.modelo;


public class Estudiantes {
    private int estudiante_id;
    private String identificacion;
    private String nombre;
    private String correo_institucional;
    private String correo_personal;
    private String telefono;
    private Boolean es_vocero;
    private String comentarios;
    private String tipo_documento;
    private String genero;

    public Estudiantes(int estudiante_id, String identificacion, String nombre, 
    String correo_institucional, String correo_personal, 
    String telefono, Boolean es_vocero, String comentarios, String tipo_documento, String genero) {
        this.estudiante_id = estudiante_id;
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.correo_institucional = correo_institucional;
        this.correo_personal = correo_personal;
        this.telefono = telefono;
        this.es_vocero = es_vocero;
        this.comentarios = comentarios;
        this.tipo_documento = tipo_documento;
        this.genero = genero;
    }
    public int getEstudiante_id() {
        return this.estudiante_id;
    }
    public void setEstudiante_id(int estudiante_id) {
        this.estudiante_id = estudiante_id;
    }
    public String getIdentificacion() {
        return this.identificacion;
    }
    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return this.nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getCorreo_institucional() {
        return this.correo_institucional;
    }
    public void setCorreo_institucional(String correo_institucional) {
        this.correo_institucional = correo_institucional;
    }
    public String getCorreo_personal() {
        return this.correo_personal;
    }
    public void setCorreo_personal(String correo_personal) {
        this.correo_personal = correo_personal;
    }
    public String getTelefono() {
        return this.telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public Boolean getEs_vocero() {
        return this.es_vocero;
    }
    public void setEs_vocero(Boolean es_vocero) {
        this.es_vocero = es_vocero;
    }
    public String getComentarios() {
        return this.comentarios;
    }
    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }
    public String getTipo_documento() {
        return this.tipo_documento;
    }
    public void setTipo_documento(String tipo_documento) {
        this.tipo_documento = tipo_documento;
    }
    public String getGenero() {
        return this.genero;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }

}