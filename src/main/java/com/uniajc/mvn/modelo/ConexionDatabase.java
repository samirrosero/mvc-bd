package com.uniajc.mvn.modelo;

// conexion a base de datos
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class ConexionDatabase {
    private static Connection connection;

    public static Connection getConnection() {
        System.out.println("intentando conectar...");
        if (connection == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");

                // variables de conexion
                String url = "";
                String user = "";
                String password = "";

                // ESTABLECER LA CONEXION
                connection = DriverManager.getConnection(url, user, password);
                System.out.println("Conexion exitosa a la base de datos");
            } catch (ClassNotFoundException e) {
                System.out.println("Error al cargar el driver de MySQL: " + e.getMessage());
            } catch (SQLException e) {
                System.out.println("Error al conectar a la base de datos: " + e.getMessage());
            }
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Conexion cerrada");
                } catch (SQLException e) {
                    System.out.println("Error al cerrar la conexion: " + e.getMessage());
                }
            }
        }
    }

