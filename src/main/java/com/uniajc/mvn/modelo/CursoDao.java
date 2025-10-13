package com.uniajc.mvn.modelo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CursoDao {
    public boolean insertarCurso(Curso curso) {
        // Lógica para insertar un curso en la base de datos
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        boolean exito = false;
        try {
            conn = ConexionDatabase.getInstance().getConnection();
            String sql = "INSERT INTO curso (curso, id_profesor, id_estudiante) VALUES (?, ?, ?)";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, curso.getCurso());
            preparedStatement.setInt(2, curso.getIdProfesor());
            preparedStatement.setInt(3, curso.getIdEstudiante());
          
            exito = preparedStatement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
    } finally{
        try{ if (preparedStatement != null) preparedStatement.close(); } catch(Exception e){}
        try{ if (conn != null) conn.close(); } catch(Exception e){}
        }
        return exito;
    }
    public List<Curso> obtenerTodosLosCursos(int idProfesor, int idEstudiante) {
        // Lógica para obtener todos los cursos de la base de datos
        List<Curso> cursos = new ArrayList<>();
        try(Connection conn = ConexionDatabase.getInstance().getConnection()) {
            String sql = "SELECT * FROM curso WHERE id_profesor = ? AND id_estudiante = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, idProfesor);
            preparedStatement.setInt(2, idEstudiante);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Curso curso = new Curso(
                    resultSet.getInt("id_curso"),
                    resultSet.getString("curso"),
                    resultSet.getInt("id_profesor"),
                    resultSet.getInt("id_estudiante")
                );
                cursos.add(curso);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cursos;
       
    }
    public boolean actualizarCurso(Curso curso) {
        // Lógica para actualizar un curso en la base de datos
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        boolean exito = false;
        try {
            conn = ConexionDatabase.getInstance().getConnection();
            String sql = "UPDATE curso SET curso = ?, id_profesor = ?, id_estudiante = ? WHERE id_curso = ?";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, curso.getCurso());
            preparedStatement.setInt(2, curso.getIdProfesor());
            preparedStatement.setInt(3, curso.getIdEstudiante());
            preparedStatement.setInt(4, curso.getIdCurso());
            exito = preparedStatement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
    } finally{
        try{ if (preparedStatement != null) preparedStatement.close(); } catch(Exception e){}
        try{ if (conn != null) conn.close(); } catch(Exception e){}
        }
        return exito;
    }
    public boolean eliminarCurso(int idCurso) {
        // Lógica para eliminar un curso de la base de datos
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        boolean exito = false;
        try {
            conn = ConexionDatabase.getInstance().getConnection();
            String sql = "DELETE FROM curso WHERE id_curso = ?";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, idCurso);
            exito = preparedStatement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
    } finally{
        try{ if (preparedStatement != null) preparedStatement.close(); } catch(Exception e){}
        try{ if (conn != null) conn.close(); } catch(Exception e){}
        }
        return exito;
    }

}
