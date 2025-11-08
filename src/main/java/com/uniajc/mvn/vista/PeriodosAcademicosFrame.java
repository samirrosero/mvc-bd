package com.uniajc.mvn.vista;

import com.uniajc.mvn.controlador.PeriodosAcademicosController;
import com.uniajc.mvn.modelo.PeriodosAcademicos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Date;
import java.util.List;

public class PeriodosAcademicosFrame extends JFrame {
    private PeriodosAcademicosController controller = new PeriodosAcademicosController();

    private JTextField txtNombre;
    private JSpinner spinnerInicio, spinnerFin;
    private JTable tabla;
    private DefaultTableModel modeloTabla;

    public PeriodosAcademicosFrame(){
        setTitle("Periodos Académicos");
        setSize(800,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel form = new JPanel(new GridLayout(3,2,10,10));
        txtNombre = new JTextField();
        spinnerInicio = new JSpinner(new SpinnerDateModel());
        spinnerFin = new JSpinner(new SpinnerDateModel());
        spinnerInicio.setEditor(new JSpinner.DateEditor(spinnerInicio,"yyyy-MM-dd"));
        spinnerFin.setEditor(new JSpinner.DateEditor(spinnerFin,"yyyy-MM-dd"));

        form.add(new JLabel("Nombre periodo:")); form.add(txtNombre);
        form.add(new JLabel("Fecha inicio:")); form.add(spinnerInicio);
        form.add(new JLabel("Fecha fin:")); form.add(spinnerFin);

        JPanel botones = new JPanel();
        JButton btnAgregar = new JButton("Agregar"), btnActualizar = new JButton("Actualizar"),
                btnEliminar = new JButton("Eliminar"), btnListar = new JButton("Listar");
        botones.add(btnAgregar); botones.add(btnActualizar); botones.add(btnEliminar); botones.add(btnListar);

        modeloTabla = new DefaultTableModel(new Object[]{"ID","Nombre","Inicio","Fin"},0);
        tabla = new JTable(modeloTabla);

        add(form, BorderLayout.NORTH); add(new JScrollPane(tabla), BorderLayout.CENTER); add(botones, BorderLayout.SOUTH);

        btnAgregar.addActionListener(e -> agregar());
        btnActualizar.addActionListener(e -> actualizar());
        btnEliminar.addActionListener(e -> eliminar());
        btnListar.addActionListener(e -> listar());

        listar();
    }

    private void agregar(){
        try {
            java.util.Date uInicio = (java.util.Date) spinnerInicio.getValue();
            java.util.Date uFin = (java.util.Date) spinnerFin.getValue();
            PeriodosAcademicos p = new PeriodosAcademicos(0, txtNombre.getText(), new Date(uInicio.getTime()), new Date(uFin.getTime()));
            if (controller.crear(p)){ JOptionPane.showMessageDialog(this,"✅ Agregado"); listar(); } else JOptionPane.showMessageDialog(this,"❌ Error");
        } catch(Exception ex){ JOptionPane.showMessageDialog(this,"Error: "+ex.getMessage()); }
    }

    private void actualizar(){ /* similar */ }
    private void eliminar(){ /* similar */ }

    private void listar(){
        modeloTabla.setRowCount(0);
        List<PeriodosAcademicos> lista = controller.listar();
        for(PeriodosAcademicos p: lista) modeloTabla.addRow(new Object[]{p.getPeriodo_academico_id(), p.getNombre_periodo(), p.getFecha_inicio(), p.getFecha_fin()});
    }
}
