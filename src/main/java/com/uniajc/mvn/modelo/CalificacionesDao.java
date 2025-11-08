package com.uniajc.mvn.modelo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CalificacionesDao {
    // registrar calificacion
    public boolean registrarCalificacion(Calificaciones c) {
        boolean exito = false;
        String sql = "{CALL sp_registrar_calificacion(?, ?, ?, ?)}";

        try (Connection conn = ConexionDatabase.getInstance().getConnection();
                CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, c.getEstudiante_id());
            cs.setInt(2, c.getComponente_evaluacion_id());
            cs.setInt(3, c.getNota());
            cs.setString(4, c.getComentarios_calificacion());
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                System.out.println(rs.getString("resultado"));
                exito = rs.getString("resultado").startsWith("Éxito");
            }
        } catch (SQLException ex) {
            System.err.println("Error registrarCalificacion: " + ex.getMessage());
        }
        return exito;
    }

    // obtener calificaciones
    public Calificaciones obtenerCalificacionesEstudiantes(int estudiante_id, int curso_id ) {
        Calificaciones c = null;
        String sql = "{CALL sp_obtener_calificaciones_estudiante(?) }";
        try (Connection conn = ConexionDatabase.getInstance().getConnection();
                CallableStatement cs = conn.prepareCall(sql)) {

            cs.setInt(1, estudiante_id);
            cs.setInt(1,curso_id);      
               ResultSet rs = cs.executeQuery();

            if (rs.next()) {
                c = new Calificaciones(
                        rs.getInt("calificacion_id"),
                        rs.getInt("estudiante_id"),
                        rs.getInt("componente_evaluacion_id"),
                        rs.getInt("nota"),
                        rs.getString("comentarios_calificacion"));
            }

        } catch (SQLException ex) {
            System.err.println("Error obtenerCalificacion: " + ex.getMessage());
        }
        return c;
    }

    // listar calificaciones
    public List<Calificaciones> listarCalificaciones() {
        List<Calificaciones> lista = new ArrayList<>();
        String sql = "{CALL sp_listar_calificaciones()}";

        try (Connection conn = ConexionDatabase.getInstance().getConnection();
                CallableStatement cs = conn.prepareCall(sql);
                ResultSet rs = cs.executeQuery()) {

            while (rs.next()) {
                Calificaciones c = new Calificaciones(
                        rs.getInt("calificacion_id"),
                        rs.getInt("estudiante_id"),
                        rs.getInt("componente_evaluacion_id"),
                        rs.getInt("nota"),
                        rs.getString("comentarios_calificacion"));
                lista.add(c);
            }
        } catch (SQLException ex) {
            System.err.println("Error listarCalificaciones: " + ex.getMessage());
        }
        return lista;
    }

    // actualizar calificacion
    public boolean actualizarCalificacion(Calificaciones c) {
        boolean exito = false;
        String sql = "{CALL sp_actualizar_calificacion(?, ?, ?, ?, ?)}";

        try (Connection conn = ConexionDatabase.getInstance().getConnection();
                CallableStatement cs = conn.prepareCall(sql)) {

            cs.setInt(1, c.getCalificacion_id());
            cs.setInt(2, c.getEstudiante_id());
            cs.setInt(3, c.getComponente_evaluacion_id());
            cs.setInt(4, c.getNota());
            cs.setString(5, c.getComentarios_calificacion());

            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                System.out.println(rs.getString("resultado"));
                exito = rs.getString("resultado").startsWith("Éxito");
                }
            } catch (SQLException ex) {
                System.err.println("Error actualizarCalificacion: " + ex.getMessage());
            }
            return exito;
    }

    // eliminar
    public boolean eliminarCalificacion(int id) {
        boolean exito = false;
        String sql = "{CALL sp_eliminar_calificacion(?)}";
        try (Connection conn = ConexionDatabase.getInstance().getConnection();
                CallableStatement cs = conn.prepareCall(sql)) {

            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                System.out.println(rs.getString("resultado"));
                exito = rs.getString("resultado").startsWith("Éxito");
            }

        } catch (SQLException ex) {
            System.err.println("Error eliminarCalificacion: " + ex.getMessage());
        }
        return exito;
    }

}
