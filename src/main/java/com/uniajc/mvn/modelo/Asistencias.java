package com.uniajc.mvn.modelo;

import java.sql.Date;

public class Asistencias {
    private int asistencia_id;
    private int estudiantes_id;
    private int curso_id;
    private Date fecha_clase;
    private String estado_asistencia;
    private String novedades;
    
    public Asistencias (int asistencia_id, int estudiante_id, int curso_id, Date fecha_clase, String estado_asistencia, String novedades){

        this.asistencia_id = asistencia_id;
        this.estudiantes_id = estudiante_id;
        this.curso_id = curso_id;
        this.fecha_clase = fecha_clase;
        this.estado_asistencia = estado_asistencia;
        this.novedades = novedades;
    }
    
        public int getAsistencia_id() {
        return this.asistencia_id;
    }
    public void setAsistencia_id(int asistencia_id) {
        this.asistencia_id = asistencia_id;
    }
    public int getEstudiantes_id() {
        return this.estudiantes_id;
    }
    public void setEstudiantes_id(int estudiantes_id) {
        this.estudiantes_id = estudiantes_id;
    }
    public int getCurso_id() {
        return this.curso_id;
    }
    public void setCurso_id(int curso_id) {
        this.curso_id = curso_id;
    }
    public Date getFecha_clase() {
        return this.fecha_clase;
    }
    public void setFecha_clase(Date fecha_clase) {
        this.fecha_clase = fecha_clase;
    }
    public String getEstado_asistencia() {
        return this.estado_asistencia;
    }
    public void setEstado_asistencia(String estado_asistencia) {
        this.estado_asistencia = estado_asistencia;
    }
    public String getNovedades() {
        return this.novedades;
    }
    public void setNovedades(String novedades) {
        this.novedades = novedades;
    }

}