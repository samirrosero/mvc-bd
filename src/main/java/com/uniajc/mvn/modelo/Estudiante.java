package com.uniajc.mvn.modelo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;
import java.sql.ResultSet;

public class Estudiante {
    private int id_estudiante;
    private String nombre;
    private int edad;

    public Estudiante(int id_estudiante, String nombre, int edad) {
        this.id_estudiante = id_estudiante;
        this.nombre = nombre;
        this.edad = edad;
    }

    public int getId() {
        return this.id_estudiante;
    }

    public void setId(int id_estudiante) {
        this.id_estudiante = id_estudiante;
    }

     public Estudiante(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return this.edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
 public static void insertarEstudiante(Estudiante estudiante) {

    String sql = "INSERT INTO estudiante (nombre, edad) VALUES (?, ?)";

    try {
      Connection conexion = ConexionDatabase.getConnection();

      PreparedStatement preparedStatement = conexion.prepareStatement(sql);

      preparedStatement.setString(1, estudiante.getNombre());
      preparedStatement.setInt(2, estudiante.getEdad());

      // Ejecutar la sentencias SQL INSERT, UPDATE o DELETE
      preparedStatement.executeUpdate();
    } catch (Exception e) {
      System.out.println("Error al insertar el estudiante: " + e.getMessage());
      e.printStackTrace();
    }

  }

  public static List<Estudiante> obtenerTodosLosEstudiantes() {

    List<Estudiante> estudiantes = new ArrayList<>();

    String sql = "SELECT nombre, edad FROM estudiante";

    try {
      Connection conexion = ConexionDatabase.getConnection();

      Statement statement = conexion.createStatement();

      // Ejecutar la sentencias SQL SELECT
      ResultSet resultSet = statement.executeQuery(sql);

      while (resultSet.next()) {
        String nombre = resultSet.getString("nombre");
        int edad = resultSet.getInt("edad");
        Estudiante estudiante = new Estudiante(nombre, edad);
        estudiantes.add(estudiante);
      }

    } catch (Exception e) {
      System.out.println("Error al insertar el estudiante: " + e.getMessage());
      e.printStackTrace();
    }

    return estudiantes;
  }

  public static void actualizarEstudiante(Estudiante estudiante) {
    String sql = "UPDATE estudiante SET nombre = ?, edad = ? WHERE id = ?";

    try {
      Connection conexion = ConexionDatabase.getConnection();

      PreparedStatement preparedStatement = conexion.prepareStatement(sql);
      preparedStatement.setString(1, estudiante.getNombre());
      preparedStatement.setInt(2, estudiante.getEdad());
      preparedStatement.setInt(3, estudiante.getId());

      // Ejecutar la sentencias SQL INSERT, UPDATE o DELETE
      preparedStatement.executeUpdate();
    } catch (Exception e) {
      System.out.println("Error al actualizar el estudiante: " + e.getMessage());
      e.printStackTrace();
    }
  }

  public static void eliminarEstudiante(int id_estudiante) {
    String sql = "DELETE FROM estudiante WHERE id = ?";

    try {
      Connection conexion = ConexionDatabase.getConnection();

      PreparedStatement preparedStatement = conexion.prepareStatement(sql);
      preparedStatement.setInt(1, id_estudiante);

      // Ejecutar la sentencias SQL INSERT, UPDATE o DELETE
      preparedStatement.executeUpdate();
    } catch (Exception e) {
      System.out.println("Error al eliminar el estudiante: " + e.getMessage());
      e.printStackTrace();
    }
  }

}