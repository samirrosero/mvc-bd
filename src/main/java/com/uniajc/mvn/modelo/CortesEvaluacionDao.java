package com.uniajc.mvn.modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CortesEvaluacionDao {

    // Crear corte
    public boolean crearCorte(CortesEvaluacion c) {
        boolean exito = false;
        String sql = "INSERT INTO cortes_evaluacion (curso_id, periodo_academico_id, nombre_corte, porcentaje, comentarios_corte) "
                   + "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConexionDatabase.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, c.getCurso_id());
            ps.setInt(2, c.getPeriodo_academico_id());
            ps.setString(3, c.getNombre_corte());
            ps.setDouble(4, c.getPorcentaje());
            ps.setString(5, c.getComentarios_cortes());

            exito = ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            System.err.println("Error crearCorte: " + ex.getMessage());
        }
        return exito;
    }

    // Listar cortes por curso
    public List<CortesEvaluacion> listarCortesPorCurso(int curso_id) {
        List<CortesEvaluacion> lista = new ArrayList<>();
        String sql = "SELECT * FROM cortes_evaluacion WHERE curso_id = ? ORDER BY corte_evaluacion_id ASC";

        try (Connection conn = ConexionDatabase.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, curso_id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                CortesEvaluacion c = new CortesEvaluacion(
                        rs.getInt("corte_evaluacion_id"),
                        rs.getInt("curso_id"),
                        rs.getInt("periodo_academico_id"),
                        rs.getString("nombre_corte"),
                        rs.getDouble("porcentaje"),
                        rs.getString("comentarios_corte")
                );
                lista.add(c);
            }

        } catch (SQLException ex) {
            System.err.println("Error listarCortesPorCurso: " + ex.getMessage());
        }
        return lista;
    }

    // Actualizar corte
    public boolean actualizarCorte(CortesEvaluacion c) {
        boolean exito = false;
        String sql = "UPDATE cortes_evaluacion SET nombre_corte=?, porcentaje=?, comentarios_corte=? WHERE corte_evaluacion_id=?";

        try (Connection conn = ConexionDatabase.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, c.getNombre_corte());
            ps.setDouble(2, c.getPorcentaje());
            ps.setString(3, c.getComentarios_cortes());
            ps.setInt(4, c.getCorte_evaluacion_id());

            exito = ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            System.err.println("Error actualizarCorte: " + ex.getMessage());
        }
        return exito;
    }

    // Eliminar corte
    public boolean eliminarCorte(int corte_id) {
        boolean exito = false;
        String sql = "DELETE FROM cortes_evaluacion WHERE corte_evaluacion_id=?";

        try (Connection conn = ConexionDatabase.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, corte_id);
            exito = ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            System.err.println("Error eliminarCorte: " + ex.getMessage());
        }
        return exito;
    }
}
