package com.uniajc.mvn.vista;

import com.uniajc.mvn.controlador.ComponentesEvaluacionController;
import com.uniajc.mvn.controlador.CortesEvaluacionController;
import com.uniajc.mvn.modelo.ComponenteEvaluacion;
import com.uniajc.mvn.modelo.CortesEvaluacion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ComponentesEvaluacionFrame extends JFrame {
    private ComponentesEvaluacionController controller = new ComponentesEvaluacionController();
    private CortesEvaluacionController cortesController = new CortesEvaluacionController();

    private JComboBox<String> cbCorte;
    private JTextField txtNombre; 
    private JSpinner spinnerPorcentaje;
    private JTable tabla;
    private DefaultTableModel modeloTabla;

    public ComponentesEvaluacionFrame(){
        setTitle("Componentes de Evaluación");
        setSize(900,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel form = new JPanel(new GridLayout(3,2,10,10));
        cbCorte = new JComboBox<>();
        txtNombre = new JTextField();
        spinnerPorcentaje = new JSpinner(new SpinnerNumberModel(0.0, 0.0, 100.0, 0.5));

        form.add(new JLabel("Corte Evaluación:")); form.add(cbCorte);
        form.add(new JLabel("Nombre componente:")); form.add(txtNombre);
        form.add(new JLabel("Porcentaje:")); form.add(spinnerPorcentaje);

        JPanel botones = new JPanel();
        JButton btnAgregar = new JButton("Agregar"), btnActualizar = new JButton("Actualizar"), btnEliminar = new JButton("Eliminar"), btnListar = new JButton("Listar");
        botones.add(btnAgregar); botones.add(btnActualizar); botones.add(btnEliminar); botones.add(btnListar);

        modeloTabla = new DefaultTableModel(new Object[]{"ID","Corte","Nombre","Porcentaje"},0);
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
        cbCorte.removeAllItems();
        List<CortesEvaluacion> cortes = cortesController.listarPorCurso(0); // tal vez tengas método listar general; en ese caso úsalo
        for (CortesEvaluacion ce: cortes) cbCorte.addItem(ce.getCorte_evaluacion_id()+" - "+ce.getNombre_corte());
    }

    private void agregar(){
        try{
            int corteId = Integer.parseInt(((String)cbCorte.getSelectedItem()).split(" - ")[0]);
            double porcentaje = ((Number)spinnerPorcentaje.getValue()).doubleValue();
            ComponenteEvaluacion c = new ComponenteEvaluacion(0, corteId, txtNombre.getText(), porcentaje);
            if (controller.crear(c)){ JOptionPane.showMessageDialog(this,"✅ Agregado"); listar(); } else JOptionPane.showMessageDialog(this,"❌ Error");
        } catch(Exception ex){ JOptionPane.showMessageDialog(this,"Error: "+ex.getMessage()); }
    }

    private void actualizar(){ /* similar al agregar */ }
    private void eliminar(){ /* similar */ }

    private void listar(){
        modeloTabla.setRowCount(0);
        // si tienes un método listarPorCorte específico, selecciona un corte o muestra todos
    }
}
