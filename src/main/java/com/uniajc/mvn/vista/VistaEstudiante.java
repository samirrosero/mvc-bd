package com.uniajc.mvn.vista;

import com.uniajc.mvn.controlador.EstudianteController;
import com.uniajc.mvn.modelo.Estudiante;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class VistaEstudiante extends JFrame {
    private EstudianteController controller;
    private JTextField txtNombre, txtEdad;
    private JTable tabla;
    private DefaultTableModel modeloTabla;
    private int idSeleccionado = -1; // Guardará el ID del estudiante seleccionado

    public VistaEstudiante() {
        controller = new EstudianteController();
        setTitle("Gestión de Estudiantes");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel superior de formulario
        JPanel panelForm = new JPanel(new GridLayout(2, 2, 5, 5));
        panelForm.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panelForm.add(txtNombre);
        panelForm.add(new JLabel("Edad:"));
        txtEdad = new JTextField();
        panelForm.add(txtEdad);

        // Panel de botones
        JPanel panelBotones = new JPanel();
        JButton btnAgregar = new JButton("Agregar");
        JButton btnActualizar = new JButton("Actualizar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnListar = new JButton("Listar");
        panelBotones.add(btnAgregar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnListar);

        // Tabla
        modeloTabla = new DefaultTableModel(new String[]{"ID", "Nombre", "Edad"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // no editable
            }
        };
        tabla = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tabla);

        // Layout general
        JPanel panelPrincipal = new JPanel ();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        panelPrincipal.add(panelForm);
        panelPrincipal.add(panelBotones);
        panelPrincipal.add(scroll);
        add(panelPrincipal, BorderLayout.CENTER);

        // Eventos
        btnAgregar.addActionListener(e -> agregarEstudiante());
        btnListar.addActionListener(e -> listarEstudiantes());
        btnActualizar.addActionListener(e -> actualizarEstudiante());
        btnEliminar.addActionListener(e -> eliminarEstudiante());

        tabla.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int fila = tabla.getSelectedRow();
                if (fila >= 0) {
                    idSeleccionado = Integer.parseInt(modeloTabla.getValueAt(fila, 0).toString());
                    txtNombre.setText(modeloTabla.getValueAt(fila, 1).toString());
                    txtEdad.setText(modeloTabla.getValueAt(fila, 2).toString());
                }
            }
        });
    }

    private void agregarEstudiante() {
        try {
            String nombre = txtNombre.getText().trim();
            int edad = Integer.parseInt(txtEdad.getText().trim());
            if (nombre.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese un nombre.");
                return;
            }
            if (controller.agregarEstudiante(nombre, edad)) {
                JOptionPane.showMessageDialog(this, "Estudiante agregado correctamente.");
                limpiarCampos();
                listarEstudiantes();
            } else {
                JOptionPane.showMessageDialog(this, "Error al agregar estudiante.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Datos inválidos: " + e.getMessage());
        }
    }

    private void listarEstudiantes() {
        modeloTabla.setRowCount(0);
        ArrayList<Estudiante> lista = controller.listarEstudiantes();
        for (Estudiante est : lista) {
            modeloTabla.addRow(new Object[]{est.getId(), est.getNombre(), est.getEdad()});
        }
    }

    private void actualizarEstudiante() {
        if (idSeleccionado == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un estudiante de la tabla.");
            return;
        }
        try {
            String nombre = txtNombre.getText().trim();
            int edad = Integer.parseInt(txtEdad.getText().trim());
            if (controller.actualizarEstudiante(idSeleccionado, nombre, edad)) {
                JOptionPane.showMessageDialog(this, "Estudiante actualizado.");
                limpiarCampos();
                listarEstudiantes();
                idSeleccionado = -1;
            } else {
                JOptionPane.showMessageDialog(this, "Error al actualizar estudiante.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Datos inválidos.");
        }
    }

    private void eliminarEstudiante() {
        if (idSeleccionado == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un estudiante para eliminar.");
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar este estudiante?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            if (controller.eliminarEstudiante(idSeleccionado)) {
                JOptionPane.showMessageDialog(this, "Estudiante eliminado.");
                limpiarCampos();
                listarEstudiantes();
                idSeleccionado = -1;
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar estudiante.");
            }
        }
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtEdad.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VistaEstudiante().setVisible(true));
    }
}
