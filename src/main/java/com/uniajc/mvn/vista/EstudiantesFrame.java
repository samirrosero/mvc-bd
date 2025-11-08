package com.uniajc.mvn.vista;

import com.uniajc.mvn.controlador.EstudiantesController;
import com.uniajc.mvn.modelo.Estudiantes;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class EstudiantesFrame extends JFrame {

    private EstudiantesController controller = new EstudiantesController();

    private JTextField txtIdentificacion, txtNombre, txtCorreoInst, txtCorreoPers,
            txtTelefono, txtComentarios;
    private JCheckBox chkVocero;
    private JComboBox<String> cmbTipoDocumento, cmbGenero;
    private JTable tabla;
    private DefaultTableModel modeloTabla;

    public EstudiantesFrame() {
        setTitle("Gestión de Estudiantes");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // ---------- PANEL FORMULARIO ----------
        JPanel panelFormulario = new JPanel(new GridLayout(5, 4, 10, 10));

        txtIdentificacion = new JTextField();
        txtNombre = new JTextField();
        txtCorreoInst = new JTextField();
        txtCorreoPers = new JTextField();
        txtTelefono = new JTextField();
        txtComentarios = new JTextField();
        chkVocero = new JCheckBox("¿Es vocero?");
        cmbTipoDocumento = new JComboBox<>(new String[]{"C.C.", "T.I."});
        cmbGenero = new JComboBox<>(new String[]{"Masculino", "Femenino"});

        panelFormulario.add(new JLabel("Identificación:"));
        panelFormulario.add(txtIdentificacion);
        panelFormulario.add(new JLabel("Nombre completo:"));
        panelFormulario.add(txtNombre);

        panelFormulario.add(new JLabel("Correo institucional:"));
        panelFormulario.add(txtCorreoInst);
        panelFormulario.add(new JLabel("Correo personal:"));
        panelFormulario.add(txtCorreoPers);

        panelFormulario.add(new JLabel("Teléfono:"));
        panelFormulario.add(txtTelefono);
        panelFormulario.add(new JLabel("Tipo documento:"));
        panelFormulario.add(cmbTipoDocumento);

        panelFormulario.add(new JLabel("Género:"));
        panelFormulario.add(cmbGenero);
        panelFormulario.add(new JLabel("Comentarios:"));
        panelFormulario.add(txtComentarios);

        panelFormulario.add(new JLabel("Vocero:"));
        panelFormulario.add(chkVocero);

        // ---------- PANEL BOTONES ----------
        JPanel panelBotones = new JPanel();
        JButton btnAgregar = new JButton("Agregar");
        JButton btnActualizar = new JButton("Actualizar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnListar = new JButton("Listar");

        panelBotones.add(btnAgregar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnListar);

        // ---------- TABLA ----------
        modeloTabla = new DefaultTableModel(new Object[]{
                "ID", "Identificación", "Nombre", "Correo Inst.", "Correo Pers.",
                "Teléfono", "Vocero", "Tipo Doc.", "Género", "Comentarios"
        }, 0);
        tabla = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tabla);

        panelPrincipal.add(panelFormulario, BorderLayout.NORTH);
        panelPrincipal.add(scroll, BorderLayout.CENTER);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);
        add(panelPrincipal);

        // ---------- EVENTOS ----------
        btnAgregar.addActionListener(e -> agregarEstudiante());
        btnActualizar.addActionListener(e -> actualizarEstudiante());
        btnEliminar.addActionListener(e -> eliminarEstudiante());
        btnListar.addActionListener(e -> listarEstudiantes());

        listarEstudiantes();

        tabla.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tabla.getSelectedRow() != -1) {
                int fila = tabla.getSelectedRow();
                txtIdentificacion.setText(modeloTabla.getValueAt(fila, 1).toString());
                txtNombre.setText(modeloTabla.getValueAt(fila, 2).toString());
                txtCorreoInst.setText(modeloTabla.getValueAt(fila, 3).toString());
                txtCorreoPers.setText(modeloTabla.getValueAt(fila, 4).toString());
                txtTelefono.setText(modeloTabla.getValueAt(fila, 5).toString());
                chkVocero.setSelected(modeloTabla.getValueAt(fila, 6).toString().equals("Sí"));
                cmbTipoDocumento.setSelectedItem(modeloTabla.getValueAt(fila, 7).toString());
                cmbGenero.setSelectedItem(modeloTabla.getValueAt(fila, 8).toString());
                txtComentarios.setText(modeloTabla.getValueAt(fila, 9).toString());
            }
        });
    }

    // ---------- MÉTODOS DE ACCIÓN ----------
    private void agregarEstudiante() {
        try {
            Estudiantes e = new Estudiantes(
                    0,
                    txtIdentificacion.getText(),
                    txtNombre.getText(),
                    txtCorreoInst.getText(),
                    txtCorreoPers.getText(),
                    txtTelefono.getText(),
                    chkVocero.isSelected(),
                    txtComentarios.getText(),
                    cmbTipoDocumento.getSelectedItem().toString(),
                    cmbGenero.getSelectedItem().toString()
            );

            if (controller.crear(e)) {
                JOptionPane.showMessageDialog(this, "✅ Estudiante agregado correctamente");
                limpiarCampos();
                listarEstudiantes();
            } else {
                JOptionPane.showMessageDialog(this, "❌ Error al agregar estudiante");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void actualizarEstudiante() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un estudiante de la tabla.");
            return;
        }

        try {
            int id = (int) modeloTabla.getValueAt(fila, 0);
            Estudiantes e = new Estudiantes(
                    id,
                    txtIdentificacion.getText(),
                    txtNombre.getText(),
                    txtCorreoInst.getText(),
                    txtCorreoPers.getText(),
                    txtTelefono.getText(),
                    chkVocero.isSelected(),
                    txtComentarios.getText(),
                    cmbTipoDocumento.getSelectedItem().toString(),
                    cmbGenero.getSelectedItem().toString()
            );

            if (controller.actualizar(e)) {
                JOptionPane.showMessageDialog(this, "✅ Estudiante actualizado correctamente");
                limpiarCampos();
                listarEstudiantes();
            } else {
                JOptionPane.showMessageDialog(this, "❌ Error al actualizar estudiante");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void eliminarEstudiante() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un estudiante de la tabla.");
            return;
        }

        int id = (int) modeloTabla.getValueAt(fila, 0);
        int confirm = JOptionPane.showConfirmDialog(this, "¿Eliminar estudiante?", "Confirmar", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            if (controller.eliminar(id)) {
                JOptionPane.showMessageDialog(this, "🗑️ Estudiante eliminado correctamente");
                listarEstudiantes();
            } else {
                JOptionPane.showMessageDialog(this, "❌ Error al eliminar estudiante");
            }
        }
    }

    private void listarEstudiantes() {
        modeloTabla.setRowCount(0);
        List<Estudiantes> lista = controller.listar();
        for (Estudiantes e : lista) {
            modeloTabla.addRow(new Object[]{
                    e.getEstudiante_id(),
                    e.getIdentificacion(),
                    e.getNombre(),
                    e.getCorreo_institucional(),
                    e.getCorreo_personal(),
                    e.getTelefono(),
                    e.getEs_vocero() != null && e.getEs_vocero() ? "Sí" : "No",
                    e.getTipo_documento(),
                    e.getGenero(),
                    e.getComentarios()
            });
        }
    }

    private void limpiarCampos() {
        txtIdentificacion.setText("");
        txtNombre.setText("");
        txtCorreoInst.setText("");
        txtCorreoPers.setText("");
        txtTelefono.setText("");
        txtComentarios.setText("");
        cmbTipoDocumento.setSelectedIndex(0);
        cmbGenero.setSelectedIndex(0);
        chkVocero.setSelected(false);
    }
}
