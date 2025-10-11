package com.uniajc.mvn.vista;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import com.uniajc.mvn.modelo.Curso;
import com.uniajc.mvn.controlador.ControladorCurso;

public class VistaCurso extends JFrame {
    private JTextField txtIdCurso, txtCurso, txtIdProfesor, txtIdEstudiante;
    private JTable tabla;
    private ControladorCurso controlador;

    public VistaCurso() {
        setTitle("Gestión de Cursos");
        setSize(700, 450);
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

        add(panelForm, BorderLayout.NORTH);

        tabla = new JTable();
        add(new JScrollPane(tabla), BorderLayout.CENTER);

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

        controlador = new ControladorCurso(new Curso(0, "", 0, 0), this);

        btnAgregar.addActionListener(e -> {
            Curso curso = new Curso(
                Integer.parseInt(txtIdCurso.getText()),
                txtCurso.getText(),
                Integer.parseInt(txtIdProfesor.getText()),
                Integer.parseInt(txtIdEstudiante.getText())
            );
            controlador.agregarCurso(curso);
            JOptionPane.showMessageDialog(this, "Curso agregado correctamente");
        });

        btnActualizar.addActionListener(e -> {
            Curso curso = new Curso(
                Integer.parseInt(txtIdCurso.getText()),
                txtCurso.getText(),
                Integer.parseInt(txtIdProfesor.getText()),
                Integer.parseInt(txtIdEstudiante.getText())
            );
            controlador.actualizarCurso(curso);
            JOptionPane.showMessageDialog(this, "Curso actualizado correctamente");
        });

        btnEliminar.addActionListener(e -> {
            controlador.eliminarCurso(Integer.parseInt(txtIdCurso.getText()));
            JOptionPane.showMessageDialog(this, "Curso eliminado correctamente");
        });

        btnListar.addActionListener(e -> mostrarDetallesCurso(controlador.listarTodosLosCursos()));

        setVisible(true);
    }

    public void mostrarDetallesCurso(List<Curso> lista) {
        String[] columnas = {"ID Curso", "Nombre Curso", "ID Profesor", "ID Estudiante"};
        Object[][] datos = new Object[lista.size()][4];
        for (int i = 0; i < lista.size(); i++) {
            datos[i][0] = lista.get(i).getIdCurso();
            datos[i][1] = lista.get(i).getCurso();
            datos[i][2] = lista.get(i).getIdProfesor();
            datos[i][3] = lista.get(i).getIdEstudiante();
        }
        tabla.setModel(new javax.swing.table.DefaultTableModel(datos, columnas));
    }
}