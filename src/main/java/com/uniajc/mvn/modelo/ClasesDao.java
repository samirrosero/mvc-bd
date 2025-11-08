package com.uniajc.mvn.modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClasesDao {

    // Crear una clase
    public boolean crearClase(Clases c) {
        boolean exito = false;
        String sql = "INSERT INTO clases (curso_id, numero_clase, fecha_clase, tema_clase, descripcion_clase, comentarios_clase) "
                   + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexionDatabase.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, c.getCurso_id());
            ps.setInt(2, c.getNumero_clase());
            ps.setDate(3, c.getFecha_clase());
            ps.setString(4, c.getTema_clase());
            ps.setString(5, c.getDescripcion_clase());
            ps.setString(6, c.getComentarios_clase());

            exito = ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            System.err.println("Error crearClase: " + ex.getMessage());
        }
        return exito;
    }

    // Listar clases por curso
    public List<Clases> listarClasesPorCurso(int curso_id) {
        List<Clases> lista = new ArrayList<>();
        String sql = "SELECT * FROM clases WHERE curso_id = ? ORDER BY numero_clase ASC";

        try (Connection conn = ConexionDatabase.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, curso_id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Clases c = new Clases(
                        rs.getInt("clase_id"),
                        rs.getInt("curso_id"),
                        rs.getInt("numero_clase"),
                        rs.getDate("fecha_clase"),
                        rs.getString("tema_clase"),
                        rs.getString("descripcion_clase"),
                        rs.getString("comentarios_clase")
                );
                lista.add(c);
            }

        } catch (SQLException ex) {
            System.err.println("Error listarClasesPorCurso: " + ex.getMessage());
        }
        return lista;
    }

    // Actualizar clase
    public boolean actualizarClase(Clases c) {
        boolean exito = false;
        String sql = "UPDATE clases SET numero_clase=?, fecha_clase=?, tema_clase=?, descripcion_clase=?, comentarios_clase=? "
                   + "WHERE clase_id=?";

        try (Connection conn = ConexionDatabase.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, c.getNumero_clase());
            ps.setDate(2, c.getFecha_clase());
            ps.setString(3, c.getTema_clase());
            ps.setString(4, c.getDescripcion_clase());
            ps.setString(5, c.getComentarios_clase());
            ps.setInt(6, c.getClase_id());

            exito = ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            System.err.println("Error actualizarClase: " + ex.getMessage());
        }
        return exito;
    }

    // Eliminar clase
    public boolean eliminarClase(int clase_id) {
        boolean exito = false;
        String sql = "DELETE FROM clases WHERE clase_id=?";

        try (Connection conn = ConexionDatabase.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, clase_id);
            exito = ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            System.err.println("Error eliminarClase: " + ex.getMessage());
        }
        return exito;
    }
}
