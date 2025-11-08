package com.uniajc.mvn.modelo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DocentesDao {
    // crear//
    public boolean crearDocente(Docentes d){
        boolean exito = false;
        String sql = "{CALL sp_crear_docente(?, ?, ?, ?, ?, ?, ?, ?, ?)}";

        try (Connection conn = ConexionDatabase.getInstance().getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {

                d.setIdentificacion(d.getIdentificacion());
                d.setNombre_docente(d.getNombre_docente());
                d.setCorreo(d.getCorreo());
                d.setGenero(d.getGenero());
                d.setTitulo_estudios(d.getTitulo_estudios());
                d.setIdiomas(d.getIdiomas());
                d.setCertificaciones(d.getCertificaciones());

                ResultSet rs = cs.executeQuery();
                if (rs.next()) {
                    System.out.println(rs.getString("resultado"));
                    exito = rs.getString("resultado").startsWith("Éxito");
                }

        } catch (SQLException ex) {
            System.err.println("Error crearDocente: " + ex.getMessage());
        }
        return exito;
    }

    // obtener por id//
    public Docentes obtenerDocente(int id) {
        Docentes d= null;
        String sql = "{CALL sp_obtener_docente(?)}";

        try (Connection conn = ConexionDatabase.getInstance().getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {

                cs.setInt(1, id);
                ResultSet rs = cs.executeQuery();

                if (rs.next()) {
                    d = new Docentes(
                        rs.getInt("docente_id"),
                        rs.getString("nombre_docente"),
                        rs.getString("identificacion"),
                        rs.getString("tipo_identificacion"),
                        rs.getString("genero"),
                        rs.getString("correo"),
                        rs.getString("titulo_estudios"),
                        rs.getString("idiomas"),
                        rs.getString("certificaciones")
                    );
                }

        } catch (SQLException ex) {
            System.err.println("Error obtenerDocente: " + ex.getMessage());
        }
        return d;
    }

    // listar todos
        public List<Docentes>listarDocentes() {
            List<Docentes> lista = new ArrayList<>();
            String sql = "{CALL sp_listar_docentes()}";

            try (Connection conn = ConexionDatabase.getInstance().getConnection();
                 CallableStatement cs = conn.prepareCall(sql);
                 ResultSet rs = cs.executeQuery()) {

                while (rs.next()) {
                    Docentes d= new Docentes(
                        rs.getInt("docente_id"),
                        rs.getString("nombre_docente"),
                        rs.getString("identificacion"),
                        rs.getString("tipo_identificacion"),
                        rs.getString("genero"),
                        rs.getString("correo"),
                        rs.getString("titulo_estudios"),
                        rs.getString("idiomas"),
                        rs.getString("certificaciones")
                    );
                    lista.add(d);
                }

        } catch (SQLException ex) {
            System.err.println("Error listarDocentes: " + ex.getMessage());
        }
        return lista;
    }
    // actualizar
 public boolean actualizarDocente (Docentes d){
        boolean exito = false;
        String sql = "{CALL sp_actualizar_docente(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";

        try (Connection conn = ConexionDatabase.getInstance().getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {

            cs.setInt(1, d.getDocente_id());
            cs.setString(2, d.getIdentificacion());
            cs.setString(3, d.getNombre_docente());
            cs.setString(4, d.getTipo_identificacion());
            cs.setString(5, d.getGenero());
            cs.setString(6, d.getCorreo());
            cs.setString(7, d.getTitulo_estudios());
            cs.setString(8, d.getIdiomas());
            cs.setString(9, d.getCertificaciones());

            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                System.out.println(rs.getString("resultado"));
                exito = rs.getString("resultado").startsWith("Éxito");
            }

        } catch (SQLException ex) {
            System.err.println("Error actualizarDocente: " + ex.getMessage());
        }
        return exito;
    }

    // eliminar
    public boolean eliminarDocente(int id) {
        boolean exito = false;
        String sql = "{CALL sp_eliminar_docente(?)}";

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
            System.err.println("Error eliminarDocente: " + ex.getMessage());
        }
        return exito;
    }

}