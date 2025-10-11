package com.uniajc.mvn.vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPrincipalFrame extends JFrame {

    public MenuPrincipalFrame() {
        setTitle("Sistema Académico - Menú Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null); // Centrar ventana
        setResizable(false);

        // Panel principal
        JPanel panel = new JPanel();
        panel.setBackground(new Color(245, 245, 250));
        panel.setLayout(new BorderLayout());

        // Título
        JLabel titulo = new JLabel("Menú Principal", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titulo.setForeground(new Color(60, 60, 80));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        // Panel central con botones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(3, 1, 15, 15));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));
        panelBotones.setBackground(new Color(245, 245, 250));

        // Botones principales
        JButton btnEstudiantes = crearBoton("Gestionar Estudiantes", new Color(100, 149, 237));
        JButton btnProfesores = crearBoton("Gestionar Profesores", new Color(255, 182, 85));
        JButton btnCursos = crearBoton("Gestionar Cursos", new Color(144, 238, 144));

        // Acciones de botones
        btnEstudiantes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new VistaEstudiante().setVisible(true);
            }
        });

        btnProfesores.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new VistaProfesor().setVisible(true);
            }
        });

        btnCursos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new VistaCurso().setVisible(true);
            }
        });

        // Añadir botones al panel
        panelBotones.add(btnEstudiantes);
        panelBotones.add(btnProfesores);
        panelBotones.add(btnCursos);

        // Agregar al panel principal
        panel.add(titulo, BorderLayout.NORTH);
        panel.add(panelBotones, BorderLayout.CENTER);

        add(panel);
    }

    private JButton crearBoton(String texto, Color color) {
        JButton boton = new JButton(texto);
        boton.setBackground(color);
        boton.setForeground(Color.WHITE);
        boton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 2, true));

        // efecto hover
        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boton.setBackground(color.darker());
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                boton.setBackground(color);
            }
        });

        return boton;
    }
}

