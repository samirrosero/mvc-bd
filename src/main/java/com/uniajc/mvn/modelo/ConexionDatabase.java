package com.uniajc.mvn.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.FileInputStream;

public class ConexionDatabase {

    private static ConexionDatabase instance;
    private Connection connection;

    private ConexionDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Properties props = new Properties();
            props.load(new FileInputStream("config.properties"));

            String url = props.getProperty("URL");
            String user = props.getProperty("USER");
            String password = props.getProperty("PASSWORD");

            connection = DriverManager.getConnection(url, user, password);
            System.out.println("✅ Conexión establecida correctamente a la base de datos.");
        } catch (Exception e) {
            System.err.println("❌ Error al conectar con la base de datos: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static synchronized ConexionDatabase getInstance() {
        if (instance == null) {
            instance = new ConexionDatabase();
        }
        return instance;
    }

    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                System.out.println("⚠️ Conexión cerrada, reintentando...");
                instance = new ConexionDatabase();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("🔌 Conexión cerrada correctamente.");
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al cerrar la conexión: " + e.getMessage());
        }
    }
}
