package com.uniajc.mvn.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class EstudianteDao {
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public boolean insertarEstudiante(Estudiante estudiante) {
        boolean state = false;
        String sql = "INSERT INTO estudiante (nombre, edad) VALUES (?, ?)";
        try {
            Connection conn = ConexionDatabase.getInstance().getConnection();
            preparedStatement = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, estudiante.getNombre());
            preparedStatement.setInt(2, estudiante.getEdad());
            int res = preparedStatement.executeUpdate();
            if (res > 0) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        estudiante.setId(generatedKeys.getInt(1));
                    }
                }
                state = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return state;

    }

    public ArrayList<Estudiante> obtenerTodosLosEstudiantes(String filter, ArrayList<String> data) {
        ArrayList<Estudiante> estudiantes = new ArrayList<>();
        Estudiante estudiante;
        Connection connect = null;
        try {
            connect = ConexionDatabase.getInstance().getConnection();
            if (connect != null) {
                String sql = " ";
                switch (filter) {
                    case "nombre":
                        sql = "SELECT * FROM estudiante WHERE nombre REGEXP ?";
                        preparedStatement = connect.prepareStatement(sql);
                        preparedStatement.setString(1, data.get(0));
                        break;
                    case "edad":
                        sql = "SELECT * FROM estudiante WHERE edad = ?";
                        preparedStatement = connect.prepareStatement(sql);
                        preparedStatement.setInt(1, Integer.parseInt(data.get(0)));
                        break;
                    default:
                        sql = "SELECT * FROM estudiante where 1";
                        preparedStatement = connect.prepareStatement(sql);
                        break;
                }
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    estudiante = new Estudiante(0, sql, 0);
                    estudiante.setId(resultSet.getInt("id_estudiante"));
                    estudiante.setNombre(resultSet.getString("nombre"));
                    estudiante.setEdad(resultSet.getInt("edad"));

                    estudiantes.add(estudiante);
                }
            } else {
                System.out.println("No se pudo establecer la conexión a la base de datos.");
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
        return estudiantes;
    }

    public boolean actualizarEstudiante(Estudiante estudiante) {
        boolean state = false;
        String sql = "UPDATE estudiante SET nombre = ?, edad = ? WHERE id_estudiante = ?";
        try {
            Connection conn = ConexionDatabase.getInstance().getConnection();
            if (conn != null) {
                preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, estudiante.getNombre());
                preparedStatement.setInt(2, estudiante.getEdad());
                preparedStatement.setInt(3, estudiante.getId());
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

    public boolean eliminarEstudiante(int id_estudiante) {
        boolean state = false;

        try {
            Connection conn = ConexionDatabase.getInstance().getConnection();
            if (conn != null) {
                String sql = "DELETE FROM estudiante WHERE id_estudiante = ?";
                preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setInt(1, id_estudiante);
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