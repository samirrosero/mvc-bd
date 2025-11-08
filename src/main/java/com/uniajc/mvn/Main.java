package com.uniajc.mvn;

import java.sql.Connection;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import com.uniajc.mvn.modelo.ConexionDatabase;
import com.uniajc.mvn.vista.MenuPrincipalFrame;

public class Main {
    public static void main(String[] args) {
        try {
            Connection conexion = ConexionDatabase.getInstance().getConnection();
            if (conexion != null) {
                System.out.println("✅ Conexión exitosa a la base de datos.");
                SwingUtilities.invokeLater(() -> new MenuPrincipalFrame().setVisible(true));
            } else {
                JOptionPane.showMessageDialog(null, "❌ No se pudo establecer conexión con la base de datos.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos: " + e.getMessage());
        }
    }
}
