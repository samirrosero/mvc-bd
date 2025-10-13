package com.uniajc.mvn.vista;

import com.uniajc.mvn.modelo.Profesor;
import com.uniajc.mvn.controlador.ControladorProfesor;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class VistaProfesor extends JFrame {
    private ControladorProfesor controlador;
    private JTextField txtNombre, txtMateria, txtCorreoElectronico;
    private JTable tabla;
    private DefaultTableModel modeloTabla;
    private int idSeleccionado = -1; // Guardará el ID del profesor seleccionado

    public VistaProfesor() {
        controlador = new ControladorProfesor();
        setTitle("Gestión de Profesores");
        setSize(650, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Panel de formulario
        JPanel panelForm = new JPanel(new GridLayout(4, 2, 5, 5));
        panelForm.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panelForm.add(txtNombre);
        panelForm.add(new JLabel("Materia:"));
        txtMateria = new JTextField();
        panelForm.add(txtMateria);
        panelForm.add(new JLabel("Correo Electrónico:"));
        txtCorreoElectronico = new JTextField();
        panelForm.add(txtCorreoElectronico);
        // panel de botones
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
        modeloTabla = new DefaultTableModel(new String[]{"ID", "Nombre", "Materia", "Correo Electrónico"}, 0) {
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
        // Eventos de botones
        btnAgregar.addActionListener(e -> agregarProfesor());
        btnActualizar.addActionListener(e -> actualizarProfesor());
        btnEliminar.addActionListener(e -> eliminarProfesor());
        btnListar.addActionListener(e -> listarProfesores());
        // Evento de selección en la tabla
        tabla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = tabla.getSelectedRow();
                if (fila >= 0) {
                    idSeleccionado = Integer.parseInt(modeloTabla.getValueAt(fila, 0).toString());
                    txtNombre.setText(modeloTabla.getValueAt(fila, 1).toString());
                    txtMateria.setText(modeloTabla.getValueAt(fila, 2).toString());
                    txtCorreoElectronico.setText(modeloTabla.getValueAt(fila, 3).toString());
                }
            }
        });

    }
    private void agregarProfesor() {
        try {
            String nombre = txtNombre.getText();
            String materia = txtMateria.getText();
            String correoElectronico = txtCorreoElectronico.getText();
            if (nombre.isEmpty() || materia.isEmpty() || correoElectronico.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            boolean exito = controlador.agregarProfesor(nombre, materia, correoElectronico);
            if (exito) {
                JOptionPane.showMessageDialog(this, "Profesor agregado exitosamente.");
                limpiarCampos();
                listarProfesores();
            } else {
                JOptionPane.showMessageDialog(this, "Error al agregar el profesor.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void listarProfesores() {
        modeloTabla.setRowCount(0); // Limpiar tabla
        ArrayList<Profesor> profesores = controlador.listarProfesores();
        for (Profesor profesor : profesores) {
            modeloTabla.addRow(new Object[]{
                    profesor.getId(),
                    profesor.getNombre(),
                    profesor.getMateria(),
                    profesor.getCorreoElectronico()
            });
        }

    }
    private void actualizarProfesor() {
        if (idSeleccionado == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un profesor de la tabla.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            String nombre = txtNombre.getText();
            String materia = txtMateria.getText();
            String correoElectronico = txtCorreoElectronico.getText();
            if (controlador.actualizarProfesor(idSeleccionado, nombre, materia, correoElectronico)){
                JOptionPane.showMessageDialog(this, "Profesor actualizado exitosamente.");
                limpiarCampos();
                listarProfesores();
                idSeleccionado = -1;
            } else {
                JOptionPane.showMessageDialog(this, "Error al actualizar el profesor.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Datos invalidos.");
        }
    }
    private void eliminarProfesor() {
        if (idSeleccionado == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un profesor de la tabla.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar este profesor?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.YES_OPTION) {
            if (controlador.eliminarProfesor(idSeleccionado)) {
                JOptionPane.showMessageDialog(this, "Profesor eliminado exitosamente.");
                limpiarCampos();
                listarProfesores();
                idSeleccionado = -1;
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar el profesor.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    private void limpiarCampos() {
        txtNombre.setText("");
        txtMateria.setText("");
        txtCorreoElectronico.setText("");
    }

}
