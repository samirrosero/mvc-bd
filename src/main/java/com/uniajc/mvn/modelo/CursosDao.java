package com.uniajc.mvn.modelo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CursosDao {
    // crear
    public boolean crearCurso(Cursos c) {
        boolean exito = false;
        String sql = "{CALL sp_crear_curso(?, ?, ?, ?, ?)}";

        try (Connection conn = ConexionDatabase.getInstance().getConnection();
                CallableStatement cs = conn.prepareCall(sql)) {
            cs.setString(1, c.getNombre_curso());
            cs.setInt(2, c.getPeriodo_academico_id());
            cs.setInt(3, c.getDocente_id());
            cs.setString(4, c.getDescripcion_curso());
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                System.out.println(rs.getString("resultado"));
                exito = rs.getString("resultado").startsWith("Éxito");
            }

        } catch (SQLException ex) {
            System.err.println("Error crearCurso: " + ex.getMessage());
        }
        return exito;
    }

    // obtener
    public Cursos obtenerCurso(int id) {
        Cursos c = null;
        String sql = "{CALL sp_obtener_curso(?)}";

        try (Connection conn = ConexionDatabase.getInstance().getConnection();
                CallableStatement cs = conn.prepareCall(sql)) {

            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();

            if (rs.next()) {
                c = new Cursos(
                        rs.getInt("curso_id"),
                        rs.getString("nombre_curso"),
                        rs.getInt("periodo_academico_id"),
                        rs.getInt("docente_id"),
                        rs.getString("descripcion_curso"));
            }

        } catch (SQLException ex) {
            System.err.println("Error obtenerCurso: " + ex.getMessage());
        }
        return c;
    }

    // listar
    public List<Cursos> listarCursos() {
        List<Cursos> lista = new ArrayList<>();
        String sql = "{CALL sp_listar_cursos()}";

        try (Connection conn = ConexionDatabase.getInstance().getConnection();
                CallableStatement cs = conn.prepareCall(sql);
                ResultSet rs = cs.executeQuery()) {

            while (rs.next()) {
                Cursos c = new Cursos(
                        rs.getInt("curso_id"),
                        rs.getString("nombre_curso"),
                        rs.getInt("periodo_academico"),
                        rs.getInt("docente_id"),
                        rs.getString("descripcion_curso"));
                lista.add(c);
            }
        } catch (SQLException ex) {
            System.err.println("Error listarCursos: " + ex.getMessage());
        }
        return lista;
    }

    // listar cursos por periodos
    public List<Cursos> listarCursosPorPeriodo(int periodo_academico_id) {
        List<Cursos> lista = new ArrayList<>();
        String sql = "{CALL sp_listar_cursos_por_periodo(?)}";

        try (Connection conn = ConexionDatabase.getInstance().getConnection();
                CallableStatement cs = conn.prepareCall(sql)) {

            cs.setInt(1, periodo_academico_id);
            ResultSet rs = cs.executeQuery();

            while (rs.next()) {
                Cursos c = new Cursos(
                        rs.getInt("curso_id"),
                        rs.getString("nombre_curso"),
                        rs.getInt("periodo_academico_id"),
                        rs.getInt("docente_id"),
                        rs.getString("descripcion_curso"));
                lista.add(c);
            }
        } catch (SQLException ex) {
            System.err.println("Error listarCursos: " + ex.getMessage());
        }
        return lista;
    }

    // ACTUALIZAR
    public boolean actualizarCursos (Cursos c){
        boolean exito = false;
        String sql = "{CALL sp_actualizar_curso(?, ?, ?, ?, ?)}";

        try (Connection conn = ConexionDatabase.getInstance().getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {

            cs.setInt(1, c.getCurso_id());
            cs.setString(2, c.getNombre_curso());
            cs.setInt(3, c.getPeriodo_academico_id());
            cs.setInt(4, c.getDocente_id());
            cs.setString(5, c.getDescripcion_curso());

            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                System.out.println(rs.getString("resultado"));
                exito = rs.getString("resultado").startsWith("Éxito");
            }

        } catch (SQLException ex) {
            System.err.println("Error actualizarCurso: " + ex.getMessage());
        }
        return exito;
    }

    // ELIMINAR
    public boolean eliminarCurso(int id) {
        boolean exito = false;
        String sql = "{CALL sp_eliminar_curso(?)}";

        try (Connection conn = ConexionDatabase.getInstance().getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {

            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                System.out.println(rs.getString("resultado"));
                exito = rs.getString("resultado").startsWith("Éxito");
 }

        }
        catch (SQLException ex) {
            System.err.println("Error eliminarCurso: " + ex.getMessage());
        }
        return exito;
        }
}
