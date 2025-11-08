package com.uniajc.mvn.modelo;

import java.sql.*;

public class ReportesDao {

    // Calcular nota final de un estudiante en un curso
    public ReporteNotaFinal calcularNotaFinal(int estudiante_id, int curso_id) {
        ReporteNotaFinal resultado = null;
        String sql = "{CALL sp_calcular_nota_final(?, ?)}";

        try (Connection conn = ConexionDatabase.getInstance().getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {

            cs.setInt(1, estudiante_id);
            cs.setInt(2, curso_id);

            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                resultado = new ReporteNotaFinal(
                        rs.getString("estudiante"),
                        rs.getString("nombre_curso"),
                        rs.getDouble("nota_final")
                );
            }

        } catch (SQLException ex) {
            System.err.println("Error calcularNotaFinal: " + ex.getMessage());
        }
        return resultado;
    }

    // Calcular porcentaje de asistencia de un estudiante en un curso
    public ReporteAsistencia calcularAsistencia(int estudiante_id, int curso_id) {
        ReporteAsistencia resultado = null;
        String sql = "{CALL sp_calcular_asistencia(?, ?)}";

        try (Connection conn = ConexionDatabase.getInstance().getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {

            cs.setInt(1, estudiante_id);
            cs.setInt(2, curso_id);

            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                resultado = new ReporteAsistencia(
                        rs.getString("estudiante"),
                        rs.getString("nombre_curso"),
                        rs.getInt("total_clases"),
                        rs.getInt("clases_presentes"),
                        rs.getInt("tardanzas"),
                        rs.getInt("ausencias"),
                        rs.getDouble("porcentaje_asistencia")
                );
            }

        } catch (SQLException ex) {
            System.err.println("Error calcularAsistencia: " + ex.getMessage());
        }
        return resultado;
    }
}
