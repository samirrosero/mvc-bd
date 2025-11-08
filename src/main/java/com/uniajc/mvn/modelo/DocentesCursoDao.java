package com.uniajc.mvn.modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DocentesCursoDao {

    // Asignar un docente a un curso
    public boolean asignarDocenteACurso(int docente_id, int curso_id) {
        boolean exito = false;
        String sql = "INSERT INTO docentes_cursos (docente_id, curso_id) VALUES (?, ?)";

        try (Connection conn = ConexionDatabase.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, docente_id);
            ps.setInt(2, curso_id);
            exito = ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            System.err.println("Error asignarDocenteACurso: " + ex.getMessage());
        }
        return exito;
    }

    // Listar cursos asignados a un docente
    public List<Integer> obtenerCursosPorDocente(int docente_id) {
        List<Integer> cursos = new ArrayList<>();
        String sql = "SELECT curso_id FROM docentes_cursos WHERE docente_id = ?";

        try (Connection conn = ConexionDatabase.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, docente_id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                cursos.add(rs.getInt("curso_id"));
            }

        } catch (SQLException ex) {
            System.err.println("Error obtenerCursosPorDocente: " + ex.getMessage());
        }
        return cursos;
    }

    // Listar docentes asignados a un curso
    public List<Integer> obtenerDocentesPorCurso(int curso_id) {
        List<Integer> docentes = new ArrayList<>();
        String sql = "SELECT docente_id FROM docentes_cursos WHERE curso_id = ?";

        try (Connection conn = ConexionDatabase.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, curso_id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                docentes.add(rs.getInt("docente_id"));
            }

        } catch (SQLException ex) {
            System.err.println("Error obtenerDocentesPorCurso: " + ex.getMessage());
        }
        return docentes;
    }

    // Eliminar asignación docente-curso
    public boolean eliminarAsignacion(int docente_id, int curso_id) {
        boolean exito = false;
        String sql = "DELETE FROM docentes_cursos WHERE docente_id=? AND curso_id=?";

        try (Connection conn = ConexionDatabase.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, docente_id);
            ps.setInt(2, curso_id);
            exito = ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            System.err.println("Error eliminarAsignacion: " + ex.getMessage());
        }
        return exito;
    }
}
