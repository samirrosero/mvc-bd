package com.uniajc.mvn.vista;

import com.uniajc.mvn.controlador.DocentesCursoController;
import com.uniajc.mvn.controlador.DocentesController;
import com.uniajc.mvn.controlador.CursosController;
import com.uniajc.mvn.modelo.Docentes;
import com.uniajc.mvn.modelo.Cursos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class DocentesCursosFrame extends JFrame {
    private DocentesCursoController controller = new DocentesCursoController();
    private DocentesController docentesController = new DocentesController();
    private CursosController cursosController = new CursosController();

    private JComboBox<String> cbDocente, cbCurso;
    private JTable tabla;
    private DefaultTableModel modeloTabla;

    public DocentesCursosFrame(){
        setTitle("Asignar Docentes a Cursos");
        setSize(700,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel form = new JPanel(new GridLayout(2,2,10,10));
        cbDocente = new JComboBox<>(); cbCurso = new JComboBox<>();
        form.add(new JLabel("Docente:")); form.add(cbDocente);
        form.add(new JLabel("Curso:")); form.add(cbCurso);

        JPanel botones = new JPanel();
        JButton btnAsignar = new JButton("Asignar"), btnEliminar = new JButton("Eliminar"), btnListar = new JButton("Listar");
        botones.add(btnAsignar); botones.add(btnEliminar); botones.add(btnListar);

        modeloTabla = new DefaultTableModel(new Object[]{"Docente ID","Docente","Curso ID","Curso"},0);
        tabla = new JTable(modeloTabla);

        add(form, BorderLayout.NORTH); add(new JScrollPane(tabla), BorderLayout.CENTER); add(botones, BorderLayout.SOUTH);

        btnAsignar.addActionListener(e -> asignar());
        btnEliminar.addActionListener(e -> eliminar());
        btnListar.addActionListener(e -> listar());

        cargarCombos();
        listar();
    }

    private void cargarCombos(){
        cbDocente.removeAllItems();
        List<Docentes> docentes = docentesController.listar();
        for(Docentes d: docentes) cbDocente.addItem(d.getDocente_id()+" - "+d.getNombre_docente());

        cbCurso.removeAllItems();
        List<Cursos> cursos = new CursosController().listar();
        for(Cursos c: cursos) cbCurso.addItem(c.getCurso_id()+" - "+c.getNombre_curso());
    }

    private void asignar(){
        try {
            int docenteId = Integer.parseInt(((String)cbDocente.getSelectedItem()).split(" - ")[0]);
            int cursoId = Integer.parseInt(((String)cbCurso.getSelectedItem()).split(" - ")[0]);
            if (controller.asignar(docenteId, cursoId)){ JOptionPane.showMessageDialog(this,"✅ Asignado"); listar(); } else JOptionPane.showMessageDialog(this,"❌ Error");
        } catch(Exception ex){ JOptionPane.showMessageDialog(this,"Error: "+ex.getMessage()); }
    }

    private void eliminar(){
        int fila = tabla.getSelectedRow();
        if(fila==-1){ JOptionPane.showMessageDialog(this,"Selecciona fila"); return; }
        int docenteId = (int) modeloTabla.getValueAt(fila,0);
        int cursoId = (int) modeloTabla.getValueAt(fila,2);
        if (controller.eliminarAsignacion(docenteId, cursoId)){ JOptionPane.showMessageDialog(this,"🗑️ Eliminado"); listar(); } else JOptionPane.showMessageDialog(this,"❌ Error");
    }

    private void listar(){
        modeloTabla.setRowCount(0);
        // Si quieres mostrar todas las asignaciones, implementa en DAO un método para listarlas
    }
}
