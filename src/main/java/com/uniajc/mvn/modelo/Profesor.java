package com.uniajc.mvn.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Statement;
import java.util.List;

public class Profesor {
    private int idProfesor;
    private String nombre;
    private String materia;
    private String correo_electronico;

    public Profesor(int idProfesor, String nombre, String materia, String correo_electronico) {
        this.idProfesor = idProfesor;
        this.nombre = nombre;
        this.materia = materia;
        this.correo_electronico = correo_electronico;
    }

    public int getIdProfesor() {
        return this.idProfesor;
    }

    public void setIdProfesor(int idProfesor) {
        this.idProfesor = idProfesor;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMateria() {
        return this.materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }
    public String getCorreoElectronico() {
        return this.correo_electronico;
    }
    public void setCorreoElectronico(String correo_electronico) {
        this.correo_electronico = correo_electronico;
    }

    // CRUD
    public static void insertarProfesor(Profesor profesor) {
        String sql = "INSERT INTO profesor (id_profesor, nombre, materia, correo_electronico) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexionDatabase.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, profesor.getIdProfesor());
            ps.setString(2, profesor.getNombre());
            ps.setString(3, profesor.getMateria());
            ps.setString(4, profesor.getCorreoElectronico());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error al insertar profesor: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static List<Profesor> obtenerTodosLosProfesores() {
        List<Profesor> lista = new ArrayList<>();
        String sql = "SELECT * FROM profesor";
        try (Connection conn = ConexionDatabase.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Profesor p = new Profesor(
                        rs.getInt("id_profesor"), 
                        rs.getString("nombre"),
                        rs.getString("materia"),
                        rs.getString("correo_electronico")
                );
                lista.add(p);
            }
        } catch (Exception e) {
            System.out.println("Error al obtener profesores: " + e.getMessage());
        }
        return lista;
    }


    public static void actualizarProfesor(Profesor profesor) {
        String sql = "UPDATE profesor SET nombre = ?, materia = ?, correo_electronico = ? WHERE id_profesor = ?";
        try (Connection conn = ConexionDatabase.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, profesor.getNombre());
            ps.setString(2, profesor.getMateria());
            ps.setString(3, profesor.getCorreoElectronico());
            ps.setInt(4, profesor.getIdProfesor());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error al actualizar profesor: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void eliminarProfesor(int idProfesor) {
        String sql = "DELETE FROM profesor WHERE id_profesor = ?";
        try (Connection conn = ConexionDatabase.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idProfesor);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error al eliminar profesor: " + e.getMessage());
            e.printStackTrace();
        }
    }

}