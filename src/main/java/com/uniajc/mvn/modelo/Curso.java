package com.uniajc.mvn.modelo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Curso {
    private int id_curso;
    private String curso;
    private int id_profesor;
    private int id_estudiante;

    public Curso(int id_curso, String curso, int id_profesor, int id_estudiante) {
        this.id_curso = id_curso;
        this.curso = curso;
        this.id_profesor = id_profesor;
        this.id_estudiante = id_estudiante;
    }
    public int getIdCurso() {
        return this.id_curso;
    }   
    public void setIdCurso(int id_curso) {
        this.id_curso = id_curso;
    }
    public String getCurso() {
        return this.curso;
    }
    public void setCurso(String curso) {
        this.curso = curso;
    }
    public int getIdProfesor() {
        return this.id_profesor;
    }
    public void setIdProfesor(int id_profesor) {
        this.id_profesor = id_profesor;
    }
    public int getIdEstudiante() {
        return this.id_estudiante;
    }
    public void setIdEstudiante(int id_estudiante) {
        this.id_estudiante = id_estudiante;
    }

    public static void insertarCurso(Curso curso) {

        String sql = "INSERT INTO curso (id_curso, curso, id_profesor, id_estudiante) VALUES (?, ?, ?, ?)";
    
        try {
          Connection conexion = ConexionDatabase.getConnection();
    
          PreparedStatement preparedStatement = conexion.prepareStatement(sql);
    
          preparedStatement.setInt(1, curso.getIdCurso());
          preparedStatement.setString(2, curso.getCurso());
          preparedStatement.setInt(3, curso.getIdProfesor());
          preparedStatement.setInt(4, curso.getIdEstudiante());
    
          // Ejecutar la sentencias SQL INSERT, UPDATE o DELETE
          preparedStatement.executeUpdate();
        } catch (Exception e) {
          System.out.println("Error al insertar el curso: " + e.getMessage());
        }
    }

    public static List<Curso> obtenerTodosLosCursos() {
        List<Curso> cursos = new ArrayList<>();
        String sql = "SELECT * FROM curso";

        try {
            Connection conexion = ConexionDatabase.getConnection();
            PreparedStatement preparedStatement = conexion.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id_curso = resultSet.getInt("id_curso");
                String curso = resultSet.getString("curso");
                int id_profesor = resultSet.getInt("id_profesor");
                int id_estudiante = resultSet.getInt("id_estudiante");
                Curso cursoObj = new Curso(id_curso, curso, id_profesor, id_estudiante);
                cursos.add(cursoObj);
            }
        } catch (Exception e) {
            System.out.println("Error al obtener los cursos: " + e.getMessage());
        }

        return cursos;
    }

    public static void actualizarCurso(Curso curso) {
        String sql = "UPDATE curso SET curso = ?, id_profesor = ?, id_estudiante = ? WHERE id_curso = ?";

        try {
            Connection conexion = ConexionDatabase.getConnection();

            PreparedStatement preparedStatement = conexion.prepareStatement(sql);
            preparedStatement.setString(1, curso.getCurso());
            preparedStatement.setInt(2, curso.getIdProfesor());
            preparedStatement.setInt(3, curso.getIdEstudiante());
            preparedStatement.setInt(4, curso.getIdCurso());

            // Ejecutar la sentencias SQL INSERT, UPDATE o DELETE
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error al actualizar el curso: " + e.getMessage());
        }
    }

    public static void eliminarCurso(int id_curso) {
        String sql = "DELETE FROM curso WHERE id_curso = ?";

        try {
            Connection conexion = ConexionDatabase.getConnection();

            PreparedStatement preparedStatement = conexion.prepareStatement(sql);
            preparedStatement.setInt(1, id_curso);

            // Ejecutar la sentencias SQL INSERT, UPDATE o DELETE
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error al eliminar el curso: " + e.getMessage());
        }
    }



}
