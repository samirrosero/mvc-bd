package com.uniajc.mvn.vista;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import com.uniajc.mvn.modelo.Profesor;
import com.uniajc.mvn.controlador.ControladorProfesor;

public class VistaProfesor extends JFrame {
    private JTextField txtId, txtNombre, txtEspecialidad;
    private JTable tabla;
    private ControladorProfesor controlador;

    public VistaProfesor() {
        setTitle("Gestión de Profesores");
        setSize(650, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JLabel titulo = new JLabel("Gestión de Profesores", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(titulo, BorderLayout.NORTH);

        JPanel panelForm = new JPanel(new GridLayout(3, 2, 10, 10));
        panelForm.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));

        txtId = new JTextField();
        txtNombre = new JTextField();
        txtEspecialidad = new JTextField();

        panelForm.add(new JLabel("ID Profesor:"));
        panelForm.add(txtId);
        panelForm.add(new JLabel("Nombre:"));
        panelForm.add(txtNombre);
        panelForm.add(new JLabel("Especialidad:"));
        panelForm.add(txtEspecialidad);

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

        controlador = new ControladorProfesor();

        btnAgregar.addActionListener(e -> {
            Profesor prof = new Profesor(ABORT, getTitle(), getWarningString(), getName());
            controlador.insertar(prof);
            JOptionPane.showMessageDialog(this, "Profesor agregado correctamente");
        });

        btnActualizar.addActionListener(e -> {
            Profesor prof = new Profesor(ABORT, getTitle(), getWarningString(), getName());
            controlador.actualizar(prof);
            JOptionPane.showMessageDialog(this, "Profesor actualizado correctamente");
        });

        btnEliminar.addActionListener(e -> {
            controlador.eliminar(Integer.parseInt(txtId.getText()));
            JOptionPane.showMessageDialog(this, "Profesor eliminado correctamente");
        });

        btnListar.addActionListener(e -> mostrarProfesores(controlador.listarTodos()));

        setVisible(true);
    }

    public void mostrarProfesores(List<Profesor> lista) {
        String[] columnas = {"ID", "Nombre", "Especialidad"};
        Object[][] datos = new Object[lista.size()][3];
        for (int i = 0; i < lista.size(); i++) {
            datos[i][0] = lista.get(i).getIdProfesor();
            datos[i][1] = lista.get(i).getNombre();
            datos[i][2] = lista.get(i).getMateria();}
        tabla.setModel(new javax.swing.table.DefaultTableModel(datos, columnas));
    }
}
