package com.uniajc.mvn.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.File;
import java.io.IOException;

public class ConexionDatabase {

    public static Connection getConnection() throws SQLException {
        Properties properties = new Properties();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            properties.load(new FileInputStream(new File("config.properties")));

            String url = properties.getProperty("URL");
            String user = properties.getProperty("USER");
            String password = properties.getProperty("PASSWORD");

            System.out.println("Intentando conectar...");
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Conexión exitosa a la base de datos");
            return conn;

        } catch (IOException e) {
            System.out.println("Error al leer archivo de configuración: " + e.getMessage());
            throw new SQLException("Error al leer archivo de configuración", e);
        } catch (ClassNotFoundException e) {
            System.out.println("Error al cargar el driver MySQL: " + e.getMessage());
            throw new SQLException("Error al cargar el driver", e);
        }
    }
}
