package com.uniajc.mvn.vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.uniajc.mvn.controlador.ControladorProfesor;
import com.uniajc.mvn.modelo.Profesor;

import java.awt.*;
import java.util.List;

public class VistaProfesor extends JFrame {

    private JTextField txtId, txtNombre, txtMateria, txtCorreo;
    private JButton btnAgregar, btnActualizar, btnEliminar, btnBuscar, btnListar;
    private JTable tabla;
    private DefaultTableModel modeloTabla;

    private ControladorProfesor controlador = new ControladorProfesor();

    public VistaProfesor() {
        setTitle("Gestión de Profesores");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        JPanel panelCampos = new JPanel(new GridLayout(5, 2, 10, 10));
        panelCampos.setBorder(BorderFactory.createTitledBorder("Datos del Profesor"));

        panelCampos.add(new JLabel("ID Profesor:"));
        txtId = new JTextField();
        panelCampos.add(txtId);

        panelCampos.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panelCampos.add(txtNombre);

        panelCampos.add(new JLabel("Materia:"));
        txtMateria = new JTextField();
        panelCampos.add(txtMateria);

        panelCampos.add(new JLabel("Correo Electrónico:"));
        txtCorreo = new JTextField();
        panelCampos.add(txtCorreo);

        JPanel panelBotones = new JPanel(new FlowLayout());
        btnAgregar = new JButton("Agregar");
        btnActualizar = new JButton("Actualizar");
        btnEliminar = new JButton("Eliminar");
        btnBuscar = new JButton("Buscar por ID");
        btnListar = new JButton("Listar Todo");

        panelBotones.add(btnAgregar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnBuscar);
        panelBotones.add(btnListar);

        modeloTabla = new DefaultTableModel(new String[]{"ID", "Nombre", "Materia", "Correo"}, 0);
        tabla = new JTable(modeloTabla);
        JScrollPane scrollTabla = new JScrollPane(tabla);

        add(panelCampos, BorderLayout.NORTH);
        add(panelBotones, BorderLayout.CENTER);
        add(scrollTabla, BorderLayout.SOUTH);

        // === Eventos ===
        btnAgregar.addActionListener(e -> agregar());
        btnActualizar.addActionListener(e -> actualizar());
        btnEliminar.addActionListener(e -> eliminar());
        btnBuscar.addActionListener(e -> buscar());
        btnListar.addActionListener(e -> listar());

        setVisible(true);
    }

    private void agregar() {
        try {
            int id = Integer.parseInt(txtId.getText());
            String nombre = txtNombre.getText();
            String materia = txtMateria.getText();
            String correo = txtCorreo.getText();

            Profesor profesor = new Profesor(id, nombre, materia, correo);
            controlador.insertar(profesor);
            JOptionPane.showMessageDialog(this, "Profesor agregado correctamente.");
            listar();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al agregar: " + ex.getMessage());
        }
    }

    private void actualizar() {
        try {
            int id = Integer.parseInt(txtId.getText());
            String nombre = txtNombre.getText();
            String materia = txtMateria.getText();
            String correo = txtCorreo.getText();

            Profesor profesor = new Profesor(id, nombre, materia, correo);
            controlador.actualizar(profesor);
            JOptionPane.showMessageDialog(this, "Profesor actualizado correctamente.");
            listar();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al actualizar: " + ex.getMessage());
        }
    }

    private void eliminar() {
        try {
            int id = Integer.parseInt(txtId.getText());
            controlador.eliminar(id);
            JOptionPane.showMessageDialog(this, "Profesor eliminado correctamente.");
            listar();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al eliminar: " + ex.getMessage());
        }
    }

    private void buscar() {
        try {
            int id = Integer.parseInt(txtId.getText());
            Profesor profesor = controlador.buscarPorId(id);
            if (profesor != null) {
                txtNombre.setText(profesor.getNombre());
                txtMateria.setText(profesor.getMateria());
                txtCorreo.setText(profesor.getCorreoElectronico());
                JOptionPane.showMessageDialog(this, "Profesor encontrado.");
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró profesor con ese ID.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al buscar: " + ex.getMessage());
        }
    }

    private void listar() {
        List<Profesor> profesores = controlador.listarTodos();
        modeloTabla.setRowCount(0);
        for (Profesor p : profesores) {
            modeloTabla.addRow(new Object[]{p.getIdProfesor(), p.getNombre(), p.getMateria(), p.getCorreoElectronico()});
        }
    }

    // Método main para probar esta vista individualmente
    public static void main(String[] args) {
        SwingUtilities.invokeLater(VistaProfesor::new);
    }
}
