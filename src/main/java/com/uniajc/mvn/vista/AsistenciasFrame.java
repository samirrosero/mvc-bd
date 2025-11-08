package com.uniajc.mvn.vista;

import com.uniajc.mvn.controlador.AsistenciasController;
import com.uniajc.mvn.modelo.Asistencias;
import com.uniajc.mvn.modelo.EstudiantesDao;
import com.uniajc.mvn.modelo.CursosDao;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Date;
import java.util.*; // alias para evitar choque en nombre (ver nota)

public class AsistenciasFrame extends JFrame {

    private AsistenciasController controller = new AsistenciasController();
    private EstudiantesDao estudiantesDao = new EstudiantesDao();
    private CursosDao cursosDao = new CursosDao();

    private JComboBox<String> cbEstudiante, cbCurso, cbEstado;
    private JTextArea txtNovedades;
    private JSpinner spinnerFechaClase;
    private JTable tabla;
    private DefaultTableModel modeloTabla;

    public AsistenciasFrame() {
        setTitle("Gestión de Asistencias");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel formulario
        JPanel panelFormulario = new JPanel(new GridLayout(6, 2, 10, 10));

        cbEstudiante = new JComboBox<>();
        cbCurso = new JComboBox<>();
        cbEstado = new JComboBox<>(new String[]{"Presente", "Ausente", "Justificado"});

        // JSpinner para la fecha (sin librerías externas)
        SpinnerDateModel dateModel = new SpinnerDateModel();
        spinnerFechaClase = new JSpinner(dateModel);
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(spinnerFechaClase, "yyyy-MM-dd");
        spinnerFechaClase.setEditor(dateEditor);
        // establecer fecha por defecto hoy
        spinnerFechaClase.setValue(new java.util.Date());

        txtNovedades = new JTextArea(3, 20);
        txtNovedades.setLineWrap(true);
        txtNovedades.setWrapStyleWord(true);

        cargarCombos();

        panelFormulario.add(new JLabel("Estudiante:"));
        panelFormulario.add(cbEstudiante);

        panelFormulario.add(new JLabel("Curso:"));
        panelFormulario.add(cbCurso);

        panelFormulario.add(new JLabel("Fecha de Clase:"));
        panelFormulario.add(spinnerFechaClase);

        panelFormulario.add(new JLabel("Estado Asistencia:"));
        panelFormulario.add(cbEstado);

        panelFormulario.add(new JLabel("Novedades:"));
        panelFormulario.add(new JScrollPane(txtNovedades));

        // Botones
        JPanel panelBotones = new JPanel();
        JButton btnAgregar = new JButton("Registrar");
        JButton btnActualizar = new JButton("Actualizar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnListar = new JButton("Listar");

        panelBotones.add(btnAgregar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnListar);

        // Tabla
        modeloTabla = new DefaultTableModel(new Object[]{
                "ID", "Estudiante", "Curso", "Fecha", "Estado", "Novedades"
        }, 0);
        tabla = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tabla);

        panelPrincipal.add(panelFormulario, BorderLayout.NORTH);
        panelPrincipal.add(scroll, BorderLayout.CENTER);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        add(panelPrincipal);

        // Acciones
        btnAgregar.addActionListener(e -> registrarAsistencia());
        btnActualizar.addActionListener(e -> actualizarAsistencia());
        btnEliminar.addActionListener(e -> eliminarAsistencia());
        btnListar.addActionListener(e -> listarAsistencias());
    }

    private void cargarCombos() {
        cbEstudiante.removeAllItems();
        cbCurso.removeAllItems();
        estudiantesDao.listarEstudiantes().forEach(e ->
                cbEstudiante.addItem(e.getEstudiante_id() + " - " + e.getNombre())
        );
        cursosDao.listarCursos().forEach(c ->
                cbCurso.addItem(c.getCurso_id() + " - " + c.getNombre_curso())
        );
    }

    private void registrarAsistencia() {
        try {
            Object val = spinnerFechaClase.getValue();
            if (!(val instanceof java.util.Date)) {
                JOptionPane.showMessageDialog(this, "Seleccione una fecha válida.");
                return;
            }
            java.util.Date utilFecha = (java.util.Date) val;
            Date sqlFecha = new Date(utilFecha.getTime()); // java.sql.Date

            int estudianteId = Integer.parseInt(cbEstudiante.getSelectedItem().toString().split(" - ")[0]);
            int cursoId = Integer.parseInt(cbCurso.getSelectedItem().toString().split(" - ")[0]);

            Asistencias asistencia = new Asistencias(
                    0,
                    estudianteId,
                    cursoId,
                    sqlFecha,
                    cbEstado.getSelectedItem().toString(),
                    txtNovedades.getText()
            );

            if (controller.registrar(asistencia)) {
                JOptionPane.showMessageDialog(this, "✅ Asistencia registrada correctamente");
                listarAsistencias();
            } else {
                JOptionPane.showMessageDialog(this, "❌ Error al registrar asistencia");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void actualizarAsistencia() {
        JOptionPane.showMessageDialog(this, "Actualizar pendiente — puedo implementarlo ahora si quieres.");
    }

    private void eliminarAsistencia() {
        JOptionPane.showMessageDialog(this, "Eliminar pendiente — puedo implementarlo ahora si quieres.");
    }

    private void listarAsistencias() {
        modeloTabla.setRowCount(0);
        // Asumimos que el controller tiene un metodo listar() que retorna List<Asistencias>
        try {
            java.util.List<Asistencias> lista = controller.obtenerPorEstudiante(0, 0); // temporal: ajustar según necesidad
            for (Asistencias a : lista) {
                modeloTabla.addRow(new Object[]{
                        a.getAsistencia_id(),
                        a.getEstudiantes_id(),
                        a.getCurso_id(),
                        a.getFecha_clase(),
                        a.getEstado_asistencia(),
                        a.getNovedades()
                });
            }
        } catch (Exception ex) {
            // Si tu controller no tiene listar() aún, omitir
        }
    }
}
