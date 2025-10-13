package com.uniajc.mvn.vista;

import com.uniajc.mvn.modelo.Curso;
import com.uniajc.mvn.controlador.ControladorCurso;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class VistaCurso extends JFrame {
    private ControladorCurso controlador;
    private JTextField txtIdCurso, txtCurso, txtIdProfesor, txtIdEstudiante;
    private JTable tabla;
    private DefaultTableModel modeloTabla;
    private int idSeleccionado = -1; // Guardará el ID del curso seleccionado

    public VistaCurso() {
        controlador = new ControladorCurso();
        setTitle("Gestión de Cursos");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JLabel titulo = new JLabel("Gestión de Cursos", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(titulo, BorderLayout.NORTH);

        JPanel panelForm = new JPanel(new GridLayout(4, 2, 10, 10));
        panelForm.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));

        txtIdCurso = new JTextField();
        txtCurso = new JTextField();
        txtIdProfesor = new JTextField();
        txtIdEstudiante = new JTextField();

        panelForm.add(new JLabel("ID Curso:"));
        panelForm.add(txtIdCurso);
        panelForm.add(new JLabel("Nombre Curso:"));
        panelForm.add(txtCurso);
        panelForm.add(new JLabel("ID Profesor:"));
        panelForm.add(txtIdProfesor);
        panelForm.add(new JLabel("ID Estudiante:"));
        panelForm.add(txtIdEstudiante);

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
        modeloTabla = new DefaultTableModel(new String[] { "ID", "Curso", "ID Profesor" }, 0) {
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
        btnAgregar.addActionListener(e -> agregarCurso());
        btnListar.addActionListener(e -> listarCursos());
        btnActualizar.addActionListener(e -> actualizarCurso());
        btnEliminar.addActionListener(e -> eliminarCurso());
        tabla.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int fila = tabla.getSelectedRow();
                if (fila >= 0) {
                    idSeleccionado = Integer.parseInt(modeloTabla.getValueAt(fila, 0).toString());
                    txtIdCurso.setText(modeloTabla.getValueAt(fila, 0).toString());
                    txtCurso.setText(modeloTabla.getValueAt(fila, 1).toString());
                    txtIdProfesor.setText(modeloTabla.getValueAt(fila, 2).toString());
                    // txtIdEstudiante.setText(modeloTabla.getValueAt(fila, 3).toString());
                }
            }
        });

    } // hola
    private void agregarCurso() {
        try {
            String curso = txtCurso.getText().trim();
            int id_profesor = Integer.parseInt(txtIdProfesor.getText().trim());
            int id_estudiante = Integer.parseInt(txtIdEstudiante.getText().trim());
            if (curso.isEmpty()) {
                JOptionPane.showMessageDialog(this, "El nombre del curso no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            boolean exito = controlador.agregarCurso(curso, id_profesor, id_estudiante);
            if (exito) {
                JOptionPane.showMessageDialog(this, "Curso agregado exitosamente.");
                limpiarCampos();
                listarCursos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al agregar el curso.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID Profesor y ID Estudiante deben ser números válidos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void listarCursos() {
        modeloTabla.setRowCount(0); // Limpiar tabla
        ArrayList<Curso> cursos = (ArrayList<Curso>) controlador.listarCursos();
        for (Curso curso : cursos) {
            modeloTabla.addRow(new Object[] { curso.getIdCurso(), curso.getCurso(), curso.getIdProfesor(), curso.getIdEstudiante() });
        }
    }

    private void actualizarCurso() {
        if (idSeleccionado == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un curso para actualizar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            String curso = txtCurso.getText().trim();
            int id_profesor = Integer.parseInt(txtIdProfesor.getText().trim());
            int id_estudiante = Integer.parseInt(txtIdEstudiante.getText().trim());
            if (curso.isEmpty()) {
                JOptionPane.showMessageDialog(this, "El nombre del curso no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            boolean exito = controlador.actualizarCurso(idSeleccionado, curso, id_profesor, id_estudiante);
            if (exito) {
                JOptionPane.showMessageDialog(this, "Curso actualizado exitosamente.");
                limpiarCampos();
                listarCursos();
                idSeleccionado = -1; // Resetear selección
            } else {
                JOptionPane.showMessageDialog(this, "Error al actualizar el curso.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID Profesor y ID Estudiante deben ser números válidos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }   
    private void eliminarCurso() {
        if (idSeleccionado == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un curso para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar el curso seleccionado?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.YES_OPTION) {
            boolean exito = controlador.eliminarCurso(idSeleccionado);
            if (exito) {
                JOptionPane.showMessageDialog(this, "Curso eliminado exitosamente.");
                limpiarCampos();
                listarCursos();
                idSeleccionado = -1; // Resetear selección
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar el curso.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    private void limpiarCampos() {
        txtIdCurso.setText("");
        txtCurso.setText("");
        txtIdProfesor.setText("");
        txtIdEstudiante.setText("");
    }
}