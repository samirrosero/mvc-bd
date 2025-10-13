<<<<<<< HEAD
package com.uniajc.mvn.vista;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import com.uniajc.mvn.modelo.Profesor;
import com.uniajc.mvn.controlador.ControladorProfesor;

public class VistaProfesor extends JFrame {
    private JTextField txtId, txtNombre, txtMateria, txtCorreoElectronico;
    private JTable tabla;
    private ControladorProfesor controlador;

    public VistaProfesor() {
        setTitle("Gestión de Profesores");
        setSize(650, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JLabel titulo = new JLabel("Gestión de Profesores", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(titulo, BorderLayout.NORTH);

        JPanel panelForm = new JPanel(new GridLayout(4, 2, 10, 10));
        panelForm.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));

        txtId = new JTextField();
        txtNombre = new JTextField();
        txtMateria = new JTextField();
        txtCorreoElectronico = new JTextField();

        panelForm.add(new JLabel("ID Profesor:"));
        panelForm.add(txtId);
        panelForm.add(new JLabel("Nombre:"));
        panelForm.add(txtNombre);
        panelForm.add(new JLabel("Materia:"));
        panelForm.add(txtMateria);
        panelForm.add(new JLabel("Correo Electronico"));
        panelForm.add(txtCorreoElectronico);

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

        controlador = new ControladorProfesor(new Profesor(0, "", "", ""), this);

        btnAgregar.addActionListener(e -> {
            String nombre = txtNombre.getText();
            String materia = txtMateria.getText();
            String correo = txtCorreoElectronico.getText();
            Profesor prof = new Profesor(0, nombre, materia, correo);
            Profesor.insertarProfesor(prof);
            controlador.agregarProfesor(prof);
            JOptionPane.showMessageDialog(this, "Profesor agregado correctamente");
        });

        btnActualizar.addActionListener(e -> {
            int id = Integer.parseInt(txtId.getText());
            String nombre = txtNombre.getText();
            String materia = txtMateria.getText();
            String correo = txtCorreoElectronico.getText();
            Profesor prof = new Profesor(id, nombre, materia, correo);
            controlador.actualizarProfesor(prof);
            JOptionPane.showMessageDialog(this, "Profesor actualizado correctamente");
        });

        btnEliminar.addActionListener(e -> {
            int id = Integer.parseInt(txtId.getText());
            controlador.eliminarProfesor(id);
            JOptionPane.showMessageDialog(this, "Profesor eliminado correctamente");
        });

        btnListar.addActionListener(e -> mostrarDetallesProfesor(controlador.listarTodosLosProfesores()));

        setVisible(true);
    }

    public void mostrarDetallesProfesor(List<Profesor> lista) {
        String[] columnas = {"ID","Nombre", "Materia", "Correo Electronico"};
        Object[][] datos = new Object[lista.size()][4];
        for (int i = 0; i < lista.size(); i++) {
            datos[i][0] = lista.get(i).getId();
            datos[i][1] = lista.get(i).getNombre();
            datos[i][2] = lista.get(i).getMateria();
            datos[i][3] = lista.get(i).getCorreoElectronico();
        }
        tabla.setModel(new javax.swing.table.DefaultTableModel(datos, columnas));
    }
}
=======
>>>>>>> 159fcaad578f75ea199ca3b3495bdc7189db1f40
