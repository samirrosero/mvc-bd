package com.uniajc.mvn.vista;

import com.uniajc.mvn.controlador.ClasesController;
import com.uniajc.mvn.controlador.CursosController;
import com.uniajc.mvn.modelo.Clases;
import com.uniajc.mvn.modelo.Cursos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Date;
import java.util.List;

public class ClasesFrame extends JFrame {
    private ClasesController controller = new ClasesController();
    private CursosController cursosController = new CursosController();

    private JComboBox<String> cbCurso;
    private JSpinner spinnerFecha;
    private JTextField txtNumeroClase, txtTema, txtDescripcion, txtComentarios;
    private JTable tabla;
    private DefaultTableModel modeloTabla;

    public ClasesFrame(){
        setTitle("Gestión de Clases");
        setSize(900,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel form = new JPanel(new GridLayout(6,2,10,10));
        cbCurso = new JComboBox<>();
        spinnerFecha = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(spinnerFecha,"yyyy-MM-dd");
        spinnerFecha.setEditor(dateEditor);
        txtNumeroClase = new JTextField();
        txtTema = new JTextField();
        txtDescripcion = new JTextField();
        txtComentarios = new JTextField();

        form.add(new JLabel("Curso:")); form.add(cbCurso);
        form.add(new JLabel("Número Clase:")); form.add(txtNumeroClase);
        form.add(new JLabel("Fecha Clase:")); form.add(spinnerFecha);
        form.add(new JLabel("Tema:")); form.add(txtTema);
        form.add(new JLabel("Descripción:")); form.add(txtDescripcion);
        form.add(new JLabel("Comentarios:")); form.add(txtComentarios);

        JPanel botones = new JPanel();
        JButton btnAgregar = new JButton("Agregar"), btnActualizar = new JButton("Actualizar"),
                btnEliminar = new JButton("Eliminar"), btnListar = new JButton("Listar");
        botones.add(btnAgregar); botones.add(btnActualizar); botones.add(btnEliminar); botones.add(btnListar);

        modeloTabla = new DefaultTableModel(new Object[]{"ID","Curso","Número","Fecha","Tema","Descripción","Comentarios"},0);
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
        for (Cursos c: cursos) cbCurso.addItem(c.getCurso_id()+" - "+c.getNombre_curso());
    }

    private void agregar(){
        try {
            int cursoId = Integer.parseInt(((String)cbCurso.getSelectedItem()).split(" - ")[0]);
            int numero = Integer.parseInt(txtNumeroClase.getText());
            java.util.Date utilDate = (java.util.Date) spinnerFecha.getValue();
            Date sqlDate = new Date(utilDate.getTime());
            Clases c = new Clases(0, cursoId, numero, sqlDate, txtTema.getText(), txtDescripcion.getText(), txtComentarios.getText());
            if(controller.crear(c)){ JOptionPane.showMessageDialog(this,"✅ Agregado"); listar(); } else JOptionPane.showMessageDialog(this,"❌ Error");
        } catch(Exception ex){ JOptionPane.showMessageDialog(this,"Error: "+ex.getMessage()); }
    }

    private void actualizar(){ /* similar a agregar; usa fila seleccionada para id */ 
        int fila = tabla.getSelectedRow();
        if(fila==-1){ JOptionPane.showMessageDialog(this,"Selecciona fila"); return;}
        try {
            int id = (int) modeloTabla.getValueAt(fila,0);
            int cursoId = Integer.parseInt(((String)cbCurso.getSelectedItem()).split(" - ")[0]);
            int numero = Integer.parseInt(txtNumeroClase.getText());
            java.util.Date utilDate = (java.util.Date) spinnerFecha.getValue();
            Date sqlDate = new Date(utilDate.getTime());
            Clases c = new Clases(id, cursoId, numero, sqlDate, txtTema.getText(), txtDescripcion.getText(), txtComentarios.getText());
            if(controller.actualizar(c)){ JOptionPane.showMessageDialog(this,"✅ Actualizado"); listar(); } else JOptionPane.showMessageDialog(this,"❌ Error");
        } catch(Exception ex){ JOptionPane.showMessageDialog(this,"Error: "+ex.getMessage()); }
    }

    private void eliminar(){
        int fila = tabla.getSelectedRow();
        if(fila==-1){ JOptionPane.showMessageDialog(this,"Selecciona fila"); return;}
        int id = (int) modeloTabla.getValueAt(fila,0);
        if(controller.eliminar(id)){ JOptionPane.showMessageDialog(this,"🗑️ Eliminado"); listar(); } else JOptionPane.showMessageDialog(this,"❌ Error");
    }

    private void listar(){
        modeloTabla.setRowCount(0);
        List<Clases> lista = controller.listarPorCurso(0); // si quieres listar por curso, podrías pedir input; aquí listaremos todos si tu DAO lo permite
        // si controller no tiene listar general, ajusta a tu DAO
        for(Clases c: lista){
            modeloTabla.addRow(new Object[]{c.getClase_id(), c.getCurso_id(), c.getNumero_clase(), c.getFecha_clase(), c.getTema_clase(), c.getDescripcion_clase(), c.getComentarios_clase()});
        }
    }
}
