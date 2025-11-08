package com.uniajc.mvn.modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PeriodosAcademicosDao {

    // Crear periodo
    public boolean crearPeriodo(PeriodosAcademicos p) {
        boolean exito = false;
        String sql = "INSERT INTO periodos_academicos (nombre_periodo, fecha_inicio, fecha_fin) VALUES (?, ?, ?)";

        try (Connection conn = ConexionDatabase.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p.getNombre_periodo());
            ps.setDate(2, p.getFecha_inicio());
            ps.setDate(3, p.getFecha_fin());
            exito = ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            System.err.println("Error crearPeriodo: " + ex.getMessage());
        }
        return exito;
    }

    // Listar periodos
    public List<PeriodosAcademicos> listarPeriodos() {
        List<PeriodosAcademicos> lista = new ArrayList<>();
        String sql = "SELECT * FROM periodos_academicos ORDER BY fecha_inicio DESC";

        try (Connection conn = ConexionDatabase.getInstance().getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                PeriodosAcademicos p = new PeriodosAcademicos(
                        rs.getInt("periodo_academico_id"),
                        rs.getString("nombre_periodo"),
                        rs.getDate("fecha_inicio"),
                        rs.getDate("fecha_fin")
                );
                lista.add(p);
            }

        } catch (SQLException ex) {
            System.err.println("Error listarPeriodos: " + ex.getMessage());
        }
        return lista;
    }

    // Actualizar periodo
    public boolean actualizarPeriodo(PeriodosAcademicos p) {
        boolean exito = false;
        String sql = "UPDATE periodos_academicos SET nombre_periodo=?, fecha_inicio=?, fecha_fin=? WHERE periodo_academico_id=?";

        try (Connection conn = ConexionDatabase.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p.getNombre_periodo());
            ps.setDate(2, p.getFecha_inicio());
            ps.setDate(3, p.getFecha_fin());
            ps.setInt(4, p.getPeriodo_academico_id());
            exito = ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            System.err.println("Error actualizarPeriodo: " + ex.getMessage());
        }
        return exito;
    }

    // Eliminar periodo
    public boolean eliminarPeriodo(int periodo_id) {
        boolean exito = false;
        String sql = "DELETE FROM periodos_academicos WHERE periodo_academico_id=?";

        try (Connection conn = ConexionDatabase.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, periodo_id);
            exito = ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            System.err.println("Error eliminarPeriodo: " + ex.getMessage());
        }
        return exito;
    }
}
