package com.uniajc.mvn.vista;

import com.uniajc.mvn.controlador.CortesEvaluacionController;
import com.uniajc.mvn.controlador.CursosController;
import com.uniajc.mvn.modelo.CortesEvaluacion;
import com.uniajc.mvn.modelo.Cursos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class CortesEvaluacionFrame extends JFrame {
    private CortesEvaluacionController controller = new CortesEvaluacionController();
    private CursosController cursosController = new CursosController();

    private JComboBox<String> cbCurso;
    private JTextField txtNombreCorte;
    private JSpinner spinnerPorcentaje;
    private JTextArea txtComentarios;
    private JTable tabla;
    private DefaultTableModel modeloTabla;

    public CortesEvaluacionFrame(){
        setTitle("Cortes de Evaluación");
        setSize(900,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel form = new JPanel(new GridLayout(4,2,10,10));
        cbCurso = new JComboBox<>();
        txtNombreCorte = new JTextField();
        spinnerPorcentaje = new JSpinner(new SpinnerNumberModel(0.0,0.0,100.0,0.5));
        txtComentarios = new JTextArea(3,20);

        form.add(new JLabel("Curso:")); form.add(cbCurso);
        form.add(new JLabel("Nombre corte:")); form.add(txtNombreCorte);
        form.add(new JLabel("Porcentaje:")); form.add(spinnerPorcentaje);
        form.add(new JLabel("Comentarios:")); form.add(new JScrollPane(txtComentarios));

        JPanel botones = new JPanel();
        JButton btnAgregar = new JButton("Agregar"), btnActualizar = new JButton("Actualizar"),
                btnEliminar = new JButton("Eliminar"), btnListar = new JButton("Listar");
        botones.add(btnAgregar); botones.add(btnActualizar); botones.add(btnEliminar); botones.add(btnListar);

        modeloTabla = new DefaultTableModel(new Object[]{"ID","Curso","Nombre","Porcentaje","Comentarios"},0);
        tabla = new JTable(modeloTabla);

        add(form, BorderLayout.NORTH); add(new JScrollPane(tabla), BorderLayout.CENTER); add(botones, BorderLayout.SOUTH);

        btnAgregar.addActionListener(e -> agregar());
        btnActualizar.addActionListener(e -> actualizar());
        btnEliminar.addActionListener(e -> eliminar());
        btnListar.addActionListener(e -> listar());

        cargarCombos();
        listar();
    }

    private void cargarCombos(){
        cbCurso.removeAllItems();
        List<Cursos> cursos = cursosController.listar();
        for(Cursos c: cursos) cbCurso.addItem(c.getCurso_id()+" - "+c.getNombre_curso());
    }

    private void agregar(){
        try {
            int cursoId = Integer.parseInt(((String)cbCurso.getSelectedItem()).split(" - ")[0]);
            double porcentaje = ((Number)spinnerPorcentaje.getValue()).doubleValue();
            CortesEvaluacion ce = new CortesEvaluacion(0, cursoId, 0, txtNombreCorte.getText(), porcentaje, txtComentarios.getText());
            if(controller.crear(ce)){ JOptionPane.showMessageDialog(this,"✅ Agregado"); listar(); } else JOptionPane.showMessageDialog(this,"❌ Error");
        } catch(Exception ex){ JOptionPane.showMessageDialog(this,"Error: "+ex.getMessage()); }
    }

    private void actualizar(){ /* similar */ }
    private void eliminar(){ /* similar */ }

    private void listar(){
        modeloTabla.setRowCount(0);
        // Si controller tiene listarPorCurso, pide curso o lista todos
    }
}
