package com.uniajc.mvn.vista;

import com.uniajc.mvn.controlador.CursosController;
import com.uniajc.mvn.modelo.Cursos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class CursosFrame extends JFrame {

    private CursosController controller = new CursosController();

    private JTextField txtNombre;
    private JComboBox<String> cbPeriodo, cbDocente;
    private JTextArea txtDescripcion;
    private JTable tabla;
    private DefaultTableModel modeloTabla;

    public CursosFrame() {
        setTitle("Gestión de Cursos");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel panelFormulario = new JPanel(new GridLayout(5, 2, 10, 10));

        txtNombre = new JTextField();
        cbPeriodo = new JComboBox<>(new String[]{"2025-1", "2025-2", "2026-1", "2026-2"});
        cbDocente = new JComboBox<>(new String[]{"Seleccione...", "Docente 1", "Docente 2", "Docente 3"});
        txtDescripcion = new JTextArea(3, 20);
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setWrapStyleWord(true);

        panelFormulario.add(new JLabel("Nombre del curso:"));
        panelFormulario.add(txtNombre);

        panelFormulario.add(new JLabel("Periodo académico:"));
        panelFormulario.add(cbPeriodo);

        panelFormulario.add(new JLabel("Docente:"));
        panelFormulario.add(cbDocente);

        panelFormulario.add(new JLabel("Descripción:"));
        panelFormulario.add(new JScrollPane(txtDescripcion));

        // Botones
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
        modeloTabla = new DefaultTableModel(new Object[]{
                "ID", "Nombre", "Periodo", "Docente", "Descripción"
        }, 0);
        tabla = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tabla);

        panelPrincipal.add(panelFormulario, BorderLayout.NORTH);
        panelPrincipal.add(scroll, BorderLayout.CENTER);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        add(panelPrincipal);

        // Acciones
        btnAgregar.addActionListener(e -> agregarCurso());
        btnActualizar.addActionListener(e -> actualizarCurso());
        btnEliminar.addActionListener(e -> eliminarCurso());
        btnListar.addActionListener(e -> listarCursos());

        listarCursos();
    }

    private void agregarCurso() {
        try {
            Cursos c = new Cursos(CROSSHAIR_CURSOR, getWarningString(), ALLBITS, ABORT, getName());
            c.setNombre_curso(txtNombre.getText());
            c.setPeriodo_academico_id(cbPeriodo.getSelectedIndex() + 1); // ejemplo
            c.setDocente_id(cbDocente.getSelectedIndex());
            c.setDescripcion_curso(txtDescripcion.getText());

            if (controller.crear(c)) {
                JOptionPane.showMessageDialog(this, "✅ Curso agregado correctamente");
                listarCursos();
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "❌ Error al agregar curso");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void actualizarCurso() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un curso de la tabla.");
            return;
        }

        try {
            int id = (int) modeloTabla.getValueAt(fila, 0);
            Cursos c = new Cursos(
                    id,
                    txtNombre.getText(),
                    cbPeriodo.getSelectedIndex() + 1,
                    cbDocente.getSelectedIndex(),
                    txtDescripcion.getText()
            );

            if (controller.actualizar(c)) {
                JOptionPane.showMessageDialog(this, "✅ Curso actualizado correctamente");
                listarCursos();
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "❌ Error al actualizar curso");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void eliminarCurso() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un curso de la tabla.");
            return;
        }

        int id = (int) modeloTabla.getValueAt(fila, 0);
        int confirm = JOptionPane.showConfirmDialog(this, "¿Eliminar curso?", "Confirmar", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            if (controller.eliminar(id)) {
                JOptionPane.showMessageDialog(this, "🗑️ Curso eliminado correctamente");
                listarCursos();
            } else {
                JOptionPane.showMessageDialog(this, "❌ Error al eliminar curso");
            }
        }
    }

    private void listarCursos() {
        modeloTabla.setRowCount(0);
        List<Cursos> lista = controller.listar();
        for (Cursos c : lista) {
            modeloTabla.addRow(new Object[]{
                    c.getCurso_id(),
                    c.getNombre_curso(),
                    c.getPeriodo_academico_id(),
                    c.getDocente_id(),
                    c.getDescripcion_curso()
            });
        }
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        cbPeriodo.setSelectedIndex(0);
        cbDocente.setSelectedIndex(0);
        txtDescripcion.setText("");
    }
}
