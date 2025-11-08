package com.uniajc.mvn.modelo;

public class ReporteAsistencia {
    private String estudiante;
    private String nombre_curso;
    private int total_clases;
    private int clases_presentes;
    private int tardanzas;
    private int ausencias;
    private double porcentaje_asistencia;

    public ReporteAsistencia(String estudiante, String nombre_curso, int total_clases, int clases_presentes,
                             int tardanzas, int ausencias, double porcentaje_asistencia) {
        this.estudiante = estudiante;
        this.nombre_curso = nombre_curso;
        this.total_clases = total_clases;
        this.clases_presentes = clases_presentes;
        this.tardanzas = tardanzas;
        this.ausencias = ausencias;
        this.porcentaje_asistencia = porcentaje_asistencia;
    }

    // Getters
    public String getEstudiante() { return estudiante; }
    public String getNombre_curso() { return nombre_curso; }
    public int getTotal_clases() { return total_clases; }
    public int getClases_presentes() { return clases_presentes; }
    public int getTardanzas() { return tardanzas; }
    public int getAusencias() { return ausencias; }
    public double getPorcentaje_asistencia() { return porcentaje_asistencia; }

    // Setters (opcional)
    public void setEstudiante(String estudiante) { this.estudiante = estudiante; }
    public void setNombre_curso(String nombre_curso) { this.nombre_curso = nombre_curso; }
    public void setTotal_clases(int total_clases) { this.total_clases = total_clases; }
    public void setClases_presentes(int clases_presentes) { this.clases_presentes = clases_presentes; }
    public void setTardanzas(int tardanzas) { this.tardanzas = tardanzas; }
    public void setAusencias(int ausencias) { this.ausencias = ausencias; }
    public void setPorcentaje_asistencia(double porcentaje_asistencia) { this.porcentaje_asistencia = porcentaje_asistencia; }

    @Override
    public String toString() {
        return String.format(
                "Estudiante: %s | Curso: %s | Asistencia: %.2f%% (Presentes: %d, Tardanzas: %d, Ausencias: %d, Total clases: %d)",
                estudiante, nombre_curso, porcentaje_asistencia, clases_presentes, tardanzas, ausencias, total_clases
        );
    }
}
