package com.uniajc.mvn.modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ComponenteEvaluacionDao {

    // Crear componente de evaluación
    public boolean crearComponente(ComponenteEvaluacion c) {
        boolean exito = false;
        String sql = "INSERT INTO componentes_evaluacion (corte_evaluacion_id, nombre_componente, porcentaje) VALUES (?, ?, ?)";

        try (Connection conn = ConexionDatabase.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, c.getCorte_evaluacion_id());
            ps.setString(2, c.getNombre_componente());
            ps.setDouble(3, c.getPorcentaje());

            exito = ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            System.err.println("Error crearComponente: " + ex.getMessage());
        }
        return exito;
    }

    // Listar componentes por corte
    public List<ComponenteEvaluacion> listarComponentesPorCorte(int corte_id) {
        List<ComponenteEvaluacion> lista = new ArrayList<>();
        String sql = "SELECT * FROM componentes_evaluacion WHERE corte_evaluacion_id = ? ORDER BY componente_evaluacion_id ASC";

        try (Connection conn = ConexionDatabase.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, corte_id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ComponenteEvaluacion c = new ComponenteEvaluacion(
                        rs.getInt("componente_evaluacion_id"),
                        rs.getInt("corte_evaluacion_id"),
                        rs.getString("nombre_componente"),
                        rs.getDouble("porcentaje")
                );
                lista.add(c);
            }

        } catch (SQLException ex) {
            System.err.println("Error listarComponentesPorCorte: " + ex.getMessage());
        }
        return lista;
    }

    // Actualizar componente
    public boolean actualizarComponente(ComponenteEvaluacion c) {
        boolean exito = false;
        String sql = "UPDATE componentes_evaluacion SET nombre_componente=?, porcentaje=? WHERE componente_evaluacion_id=?";

        try (Connection conn = ConexionDatabase.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, c.getNombre_componente());
            ps.setDouble(2, c.getPorcentaje());
            ps.setInt(3, c.getComponente_evaluacion_id());
            exito = ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            System.err.println("Error actualizarComponente: " + ex.getMessage());
        }
        return exito;
    }

    // Eliminar componente
    public boolean eliminarComponente(int componente_id) {
        boolean exito = false;
        String sql = "DELETE FROM componentes_evaluacion WHERE componente_evaluacion_id=?";

        try (Connection conn = ConexionDatabase.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, componente_id);
            exito = ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            System.err.println("Error eliminarComponente: " + ex.getMessage());
        }
        return exito;
    }
}
