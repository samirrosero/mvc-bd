package com.uniajc.mvn.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CursoDao {
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    // INSERTAR CURSO
    public boolean insertarCurso(Curso curso) {
        boolean state = false;
        String sql = "INSERT INTO curso (curso, id_estudiante, id_profesor) VALUES (?, ?, ?)";
        try {
            Connection conn = ConexionDatabase.getInstance().getConnection();
            preparedStatement = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, curso.getNombreCurso()); // nombre del curso
            preparedStatement.setInt(2, curso.getIdEstudiante());
            preparedStatement.setInt(3, curso.getIdProfesor());
            int res = preparedStatement.executeUpdate();
            if (res > 0) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        curso.setIdCurso(generatedKeys.getInt(1));
                    }
                }
                state = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return state;
    }

    // OBTENER TODOS LOS CURSOS
    public ArrayList<Curso> obtenerTodosLosCursos(String filter, ArrayList<String> data) {
        ArrayList<Curso> cursos = new ArrayList<>();
        Curso curso;
        Connection connect = null;

        try {
            connect = ConexionDatabase.getInstance().getConnection();
            if (connect != null) {
                String sql = "";

                switch (filter) {
                    case "curso":
                        sql = "SELECT c.id_curso, c.curso, e.id_estudiante, e.nombre AS nombre_estudiante, " +
                              "p.id_profesor, p.nombre AS nombre_profesor " +
                              "FROM curso c " +
                              "INNER JOIN estudiante e ON c.id_estudiante = e.id_estudiante " +
                              "INNER JOIN profesor p ON c.id_profesor = p.id_profesor " +
                              "WHERE c.curso REGEXP ?";
                        preparedStatement = connect.prepareStatement(sql);
                        preparedStatement.setString(1, data.get(0));
                        break;
                    default:
                        sql = "SELECT c.id_curso, c.curso, e.id_estudiante, e.nombre AS nombre_estudiante, " +
                              "p.id_profesor, p.nombre AS nombre_profesor " +
                              "FROM curso c " +
                              "INNER JOIN estudiante e ON c.id_estudiante = e.id_estudiante " +
                              "INNER JOIN profesor p ON c.id_profesor = p.id_profesor";
                        preparedStatement = connect.prepareStatement(sql);
                        break;
                }

                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    curso = new Curso(0, sql, 0, 0);
                    curso.setIdCurso(resultSet.getInt("id_curso"));
                    curso.setNombreCurso(resultSet.getString("curso"));
                    curso.setIdEstudiante(resultSet.getInt("id_estudiante"));
                    curso.setNombreEstudiante(resultSet.getString("nombre_estudiante"));
                    curso.setIdProfesor(resultSet.getInt("id_profesor"));
                    curso.setNombreProfesor(resultSet.getString("nombre_profesor"));

                    cursos.add(curso);
                }

            } else {
                System.out.println("❌ No se pudo establecer la conexión a la base de datos.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                ConexionDatabase.getInstance().close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        return cursos;
    }

    // ACTUALIZAR CURSO
    public boolean actualizarCurso(Curso curso) {
        boolean state = false;
        String sql = "UPDATE curso SET curso = ?, id_estudiante = ?, id_profesor = ? WHERE id_curso = ?";
        try {
            Connection conn = ConexionDatabase.getInstance().getConnection();
            if (conn != null) {
                preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, curso.getNombreCurso());
                preparedStatement.setInt(2, curso.getIdEstudiante());
                preparedStatement.setInt(3, curso.getIdProfesor());
                preparedStatement.setInt(4, curso.getIdCurso());
                int res = preparedStatement.executeUpdate();
                state = res > 0;
            } else {
                System.out.println("Conexión Fallida");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                ConexionDatabase.getInstance().close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return state;
    }

    // ELIMINAR CURSO
    public boolean eliminarCurso(int id_curso) {
        boolean state = false;
        try {
            Connection conn = ConexionDatabase.getInstance().getConnection();
            if (conn != null) {
                String sql = "DELETE FROM curso WHERE id_curso = ?";
                preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setInt(1, id_curso);
                int res = preparedStatement.executeUpdate();
                state = res > 0;
            } else {
                System.out.println("Conexión Fallida");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                ConexionDatabase.getInstance().close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return state;
    }
}
