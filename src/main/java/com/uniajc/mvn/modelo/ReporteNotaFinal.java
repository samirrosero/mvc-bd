package com.uniajc.mvn.modelo;

public class ReporteNotaFinal {
    private String estudiante;
    private String nombre_curso;
    private double nota_final;

    public ReporteNotaFinal(String estudiante, String nombre_curso, double nota_final) {
        this.estudiante = estudiante;
        this.nombre_curso = nombre_curso;
        this.nota_final = nota_final;
    }

    // Getters
    public String getEstudiante() { return estudiante; }
    public String getNombre_curso() { return nombre_curso; }
    public double getNota_final() { return nota_final; }

    // Setters (opcional, si vas a modificarlos)
    public void setEstudiante(String estudiante) { this.estudiante = estudiante; }
    public void setNombre_curso(String nombre_curso) { this.nombre_curso = nombre_curso; }
    public void setNota_final(double nota_final) { this.nota_final = nota_final; }

    @Override
    public String toString() {
        return String.format("Estudiante: %s | Curso: %s | Nota Final: %.2f",
                estudiante, nombre_curso, nota_final);
    }
}
