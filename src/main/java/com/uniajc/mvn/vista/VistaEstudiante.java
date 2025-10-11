package com.uniajc.mvn.vista;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import com.uniajc.mvn.modelo.Estudiante;
import com.uniajc.mvn.controlador.ControladorEstudiante;

public class VistaEstudiante extends JFrame {
    private JTextField txtNombre, txtEdad, txtId;
    private JTable tabla;
    private ControladorEstudiante controlador;

    public VistaEstudiante() {
        setTitle("Gestión de Estudiantes");
        setSize(650, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JLabel titulo = new JLabel("Gestión de Estudiantes", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(titulo, BorderLayout.NORTH);

        // Panel de formulario
        JPanel panelForm = new JPanel(new GridLayout(3, 2, 10, 10));
        panelForm.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));

        txtId = new JTextField();
        txtNombre = new JTextField();
        txtEdad = new JTextField();

        panelForm.add(new JLabel("ID Estudiante:"));
        panelForm.add(txtId);
        panelForm.add(new JLabel("Nombre:"));
        panelForm.add(txtNombre);
        panelForm.add(new JLabel("Edad:"));
        panelForm.add(txtEdad);

        add(panelForm, BorderLayout.NORTH);

        // Tabla
        tabla = new JTable();
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        // Botones
        JPanel panelBotones = new JPanel(new FlowLayout());
        JButton btnAgregar = new JButton("Agregar");
        JButton btnActualizar = new JButton("Actualizar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnListar = new JButton("Listar");

        panelBotones.add(btnAgregar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnListar);
        add(panelBotones, BorderLayout.SOUTH);

        controlador = new ControladorEstudiante(new Estudiante(0, "", 0), this);

        // Eventos
        btnAgregar.addActionListener(e -> {
            String nombre = txtNombre.getText();
            int edad = Integer.parseInt(txtEdad.getText());
            Estudiante est = new Estudiante(0, nombre, edad);
             Estudiante.insertarEstudiante(est);
            controlador.agregarEstudiante(est);
            JOptionPane.showMessageDialog(this, "Estudiante agregado correctamente");
        });

        btnActualizar.addActionListener(e -> {
            int id = Integer.parseInt(txtId.getText());
            String nombre = txtNombre.getText();
            int edad = Integer.parseInt(txtEdad.getText());
            Estudiante est = new Estudiante(id, nombre, edad);
            controlador.actualizarEstudiante(est);
            JOptionPane.showMessageDialog(this, "Estudiante actualizado correctamente");
        });

        btnEliminar.addActionListener(e -> {
            int id = Integer.parseInt(txtId.getText());
            controlador.eliminarEstudiante(id);
            JOptionPane.showMessageDialog(this, "Estudiante eliminado correctamente");
        });

        btnListar.addActionListener(e -> mostrarDetallesEstudiante(controlador.listarTodosLosEstudiantes()));

        setVisible(true);
    }

    public void mostrarDetallesEstudiante(List<Estudiante> lista) {
        String[] columnas = {"ID", "Nombre", "Edad"};
        Object[][] datos = new Object[lista.size()][3];
        for (int i = 0; i < lista.size(); i++) {
            datos[i][0] = lista.get(i).getId();
            datos[i][1] = lista.get(i).getNombre();
            datos[i][2] = lista.get(i).getEdad();
        }
        tabla.setModel(new javax.swing.table.DefaultTableModel(datos, columnas));
    }
}