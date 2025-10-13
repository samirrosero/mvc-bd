package com.uniajc.mvn.vista;

import com.uniajc.mvn.modelo.*;
import com.uniajc.mvn.controlador.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class VistaCurso extends JFrame {
    private final ControladorCurso controladorCurso;
    private final ControladorProfesor controladorProfesor;
    private final EstudianteController controladorEstudiante;

    private JTextField txtCurso;
    private JComboBox<Profesor> comboProfesor;
    private JComboBox<Estudiante> comboEstudiante;
    private JTable tabla;
    private DefaultTableModel modeloTabla;
    private int idSeleccionado = -1;

    public VistaCurso() {
        controladorCurso = new ControladorCurso();
        controladorProfesor = new ControladorProfesor();
        controladorEstudiante = new EstudianteController();

        setTitle("Gestión de Cursos");
        setSize(750, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JLabel titulo = new JLabel("Gestión de Cursos", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        add(titulo, BorderLayout.NORTH);

        // --- FORMULARIO ---
        JPanel panelForm = new JPanel(new GridLayout(3, 2, 10, 10));
        panelForm.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));

        txtCurso = new JTextField();
        comboProfesor = new JComboBox<>();
        comboEstudiante = new JComboBox<>();

        panelForm.add(new JLabel("Nombre del Curso:"));
        panelForm.add(txtCurso);
        panelForm.add(new JLabel("Profesor (ID - Nombre - Materia):"));
        panelForm.add(comboProfesor);
        panelForm.add(new JLabel("Estudiante (ID - Nombre):"));
        panelForm.add(comboEstudiante);

        // --- BOTONES ---
        JPanel panelBotones = new JPanel();
        JButton btnAgregar = new JButton("Agregar");
        JButton btnActualizar = new JButton("Actualizar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnListar = new JButton("Listar");
        panelBotones.add(btnAgregar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnListar);

        // --- TABLA ---
        modeloTabla = new DefaultTableModel(new String[]{"ID", "Curso", "Profesor", "Estudiante"}, 0);
        tabla = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tabla);

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        panelPrincipal.add(panelForm);
        panelPrincipal.add(panelBotones);
        panelPrincipal.add(scroll);
        add(panelPrincipal, BorderLayout.CENTER);

        // --- EVENTOS ---
        cargarProfesores();
        cargarEstudiantes();
        btnAgregar.addActionListener(e -> agregarCurso());
        btnListar.addActionListener(e -> listarCursos());
        btnActualizar.addActionListener(e -> actualizarCurso());
        btnEliminar.addActionListener(e -> eliminarCurso());

        tabla.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int fila = tabla.getSelectedRow();
                if (fila >= 0) {
                    idSeleccionado = (int) modeloTabla.getValueAt(fila, 0);
                    txtCurso.setText(modeloTabla.getValueAt(fila, 1).toString());
                }
            }
        });
    }

    private void cargarProfesores() {
        comboProfesor.removeAllItems();
        ArrayList<Profesor> lista = controladorProfesor.listarProfesores();
        for (Profesor p : lista) comboProfesor.addItem(p);
    }

    private void cargarEstudiantes() {
        comboEstudiante.removeAllItems();
        ArrayList<Estudiante> lista = controladorEstudiante.listarEstudiantes();
        for (Estudiante e : lista) comboEstudiante.addItem(e);
    }

    private void agregarCurso() {
        String nombreCurso = txtCurso.getText().trim();
        Profesor profesor = (Profesor) comboProfesor.getSelectedItem();
        Estudiante estudiante = (Estudiante) comboEstudiante.getSelectedItem();

        if (nombreCurso.isEmpty() || profesor == null || estudiante == null) {
            JOptionPane.showMessageDialog(this, "Complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean exito = controladorCurso.agregarCurso(nombreCurso, profesor.getId(), estudiante.getId());
        if (exito) {
            JOptionPane.showMessageDialog(this, "Curso agregado correctamente.");
            limpiarCampos();
            listarCursos();
        } else {
            JOptionPane.showMessageDialog(this, "Error al agregar el curso.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void listarCursos() {
        modeloTabla.setRowCount(0);
        ArrayList<Curso> cursos = (ArrayList<Curso>) controladorCurso.listarCursos();
        for (Curso c : cursos) {
            modeloTabla.addRow(new Object[]{
                c.getIdCurso(),
                c.getNombreCurso(),
                c.getNombreProfesor() + " (" + c.getMateriaProfesor() + ")",
                c.getNombreEstudiante()
            });
        }
    }

    private void actualizarCurso() {
        if (idSeleccionado == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un curso para actualizar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String nombreCurso = txtCurso.getText().trim();
        Profesor profesor = (Profesor) comboProfesor.getSelectedItem();
        Estudiante estudiante = (Estudiante) comboEstudiante.getSelectedItem();

        boolean exito = controladorCurso.actualizarCurso(idSeleccionado, nombreCurso, profesor.getId(), estudiante.getId());
        if (exito) {
            JOptionPane.showMessageDialog(this, "Curso actualizado correctamente.");
            limpiarCampos();
            listarCursos();
            idSeleccionado = -1;
        } else {
            JOptionPane.showMessageDialog(this, "Error al actualizar curso.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarCurso() {
        if (idSeleccionado == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un curso para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "¿Desea eliminar el curso?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            boolean exito = controladorCurso.eliminarCurso(idSeleccionado);
            if (exito) {
                JOptionPane.showMessageDialog(this, "Curso eliminado correctamente.");
                listarCursos();
                idSeleccionado = -1;
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar curso.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void limpiarCampos() {
        txtCurso.setText("");
        comboProfesor.setSelectedIndex(-1);
        comboEstudiante.setSelectedIndex(-1);
    }
}
