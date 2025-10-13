package com.uniajc.mvn.modelo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProfesorDao {
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public boolean insertarProfesor(Profesor profesor) {
        // Lógica para insertar un profesor en la base de datos
        boolean exito = false;
        String sql = "INSERT INTO profesor (nombre, materia, correo_electronico) VALUES (?, ?, ?)";
        try {
            Connection conn = ConexionDatabase.getInstance().getConnection();
            preparedStatement = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, profesor.getNombre());
            preparedStatement.setString(2, profesor.getMateria());
            preparedStatement.setString(3, profesor.getCorreoElectronico());
            int res = preparedStatement.executeUpdate();
            if (res > 0) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        profesor.setId(generatedKeys.getInt(1));
                    }
                }
                exito = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exito;
    }

    public ArrayList<Profesor> obtenerTodosLosProfesores(String filter, ArrayList<String> data) {
        ArrayList<Profesor> profesores = new ArrayList<>();
        Profesor profesor;
        Connection connect = null;
        try {
            connect = ConexionDatabase.getInstance().getConnection();
            if (connect != null) {
                String sql = " ";
                switch (filter) {
                    case "nombre":
                        sql = "SELECT * FROM profesor WHERE nombre REGEXP ?";
                        preparedStatement = connect.prepareStatement(sql);
                        preparedStatement.setString(1, data.get(0));
                        break;
                    case "materia":
                        sql = "SELECT * FROM profesor WHERE materia REGEXP ?";
                        preparedStatement = connect.prepareStatement(sql);
                        preparedStatement.setString(1, data.get(0));
                        break;
                    default:
                        sql = "SELECT * FROM profesor where 1";
                        preparedStatement = connect.prepareStatement(sql);
                        break;
                }
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    profesor = new Profesor(
                            resultSet.getInt("id_profesor"),
                            resultSet.getString("nombre"),
                            resultSet.getString("materia"),
                            resultSet.getString("correo_electronico")
                    );
                    profesores.add(profesor);
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
        return profesores;
    }
    public boolean actualizarProfesor(Profesor profesor) {
        boolean state = false;
        String sql = "UPDATE profesor SET nombre = ?, materia = ?, correo_electronico = ? WHERE id_profesor = ?";
        try {
            Connection conn = ConexionDatabase.getInstance().getConnection();
            if (conn != null) {
                preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, profesor.getNombre());
                preparedStatement.setString(2, profesor.getMateria());
                preparedStatement.setString(3, profesor.getCorreoElectronico());
                preparedStatement.setInt(4, profesor.getId());
                int res = preparedStatement.executeUpdate();
                if (res > 0) {
                    state = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                ConexionDatabase.getInstance().close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return state;
    }
    public boolean eliminarProfesor(int id) {
        boolean state = false;
        String sql = "DELETE FROM profesor WHERE id_profesor = ?";
        try {
            Connection conn = ConexionDatabase.getInstance().getConnection();
            if (conn != null) {
                preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setInt(1, id);
                int res = preparedStatement.executeUpdate();
                if (res > 0) {
                    state = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
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


