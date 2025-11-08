package com.uniajc.mvn.modelo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AsistenciasDao {

    // Registrar asistencia
    public boolean registrarAsistencia(Asistencias a) {
        boolean exito = false;
        String sql = "{CALL sp_registrar_asistencia(?, ?, ?, ?, ?)}";

        try (Connection conn = ConexionDatabase.getInstance().getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {

            cs.setInt(1, a.getEstudiantes_id());
            cs.setInt(2, a.getCurso_id());
            cs.setDate(3, a.getFecha_clase());
            cs.setString(4, a.getEstado_asistencia());
            cs.setString(5, a.getNovedades());

            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                System.out.println(rs.getString("resultado"));
                exito = rs.getString("resultado").startsWith("Éxito");
            }

        } catch (SQLException ex) {
            System.err.println("Error registrarAsistencia: " + ex.getMessage());
        }
        return exito;
    }

    // Obtener asistencias de un estudiante en un curso
    public List<Asistencias> obtenerAsistenciasEstudiante(int estudiante_id, int curso_id) {
        List<Asistencias> lista = new ArrayList<>();
        String sql = "{CALL sp_obtener_asistencias_estudiante(?, ?)}";

        try (Connection conn = ConexionDatabase.getInstance().getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {

            cs.setInt(1, estudiante_id);
            cs.setInt(2, curso_id);
            ResultSet rs = cs.executeQuery();

            while (rs.next()) {
                Asistencias a = new Asistencias(
                        rs.getInt("asistencia_id"),
                        estudiante_id,
                        curso_id,
                        rs.getDate("fecha_clase"),
                        rs.getString("estado_asistencia"),
                        rs.getString("novedades")
                );
                lista.add(a);
            }

        } catch (SQLException ex) {
            System.err.println("Error obtenerAsistenciasEstudiante: " + ex.getMessage());
        }
        return lista;
    }

    // Actualizar asistencia
    public boolean actualizarAsistencia(Asistencias a) {
        boolean exito = false;
        String sql = "{CALL sp_actualizar_asistencia(?, ?, ?)}";

        try (Connection conn = ConexionDatabase.getInstance().getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {

            cs.setInt(1, a.getAsistencia_id());
            cs.setString(2, a.getEstado_asistencia());
            cs.setString(3, a.getNovedades());

            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                System.out.println(rs.getString("resultado"));
                exito = rs.getString("resultado").startsWith("Éxito");
            }

        } catch (SQLException ex) {
            System.err.println("Error actualizarAsistencia: " + ex.getMessage());
        }
        return exito;
    }

    // Eliminar asistencia
    public boolean eliminarAsistencia(int asistencia_id) {
        boolean exito = false;
        String sql = "{CALL sp_eliminar_asistencia(?)}";

        try (Connection conn = ConexionDatabase.getInstance().getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {

            cs.setInt(1, asistencia_id);
            ResultSet rs = cs.executeQuery();

            if (rs.next()) {
                System.out.println(rs.getString("resultado"));
                exito = rs.getString("resultado").startsWith("Éxito");
            }

        } catch (SQLException ex) {
            System.err.println("Error eliminarAsistencia: " + ex.getMessage());
        }
        return exito;
    }
}
