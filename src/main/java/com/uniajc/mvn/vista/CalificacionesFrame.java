package com.uniajc.mvn.vista;

import com.uniajc.mvn.controlador.CalificacionesController;
import com.uniajc.mvn.controlador.CursosController;
import com.uniajc.mvn.controlador.EstudiantesController;
import com.uniajc.mvn.modelo.Calificaciones;
import com.uniajc.mvn.modelo.Cursos;
import com.uniajc.mvn.modelo.Estudiantes;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class CalificacionesFrame extends JFrame {

    private CalificacionesController controller = new CalificacionesController();
    private CursosController cursosController = new CursosController();
    private EstudiantesController estudiantesController = new EstudiantesController();

    private JComboBox<String> cbEstudiante, cbCurso, cbComponente; // componente puede llenarse si tiene DAO
    private JSpinner spinnerNota;
    private JTextArea txtComentarios;
    private JTable tabla;
    private DefaultTableModel modeloTabla;

    public CalificacionesFrame() {
        setTitle("Gestión de Calificaciones");
        setSize(900,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel form = new JPanel(new GridLayout(5,2,10,10));
        cbEstudiante = new JComboBox<>();
        cbCurso = new JComboBox<>();
        // componente_evaluacion_id: si no tienes DAO para listar componentes, lo puedes llenar desde ComponentesEvaluacionDao
        cbComponente = new JComboBox<>();
        spinnerNota = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
        txtComentarios = new JTextArea(3,20);

        form.add(new JLabel("Estudiante:")); form.add(cbEstudiante);
        form.add(new JLabel("Curso:")); form.add(cbCurso);
        form.add(new JLabel("Componente evaluacion (id):")); form.add(cbComponente);
        form.add(new JLabel("Nota:")); form.add(spinnerNota);
        form.add(new JLabel("Comentarios:")); form.add(new JScrollPane(txtComentarios));

        JPanel botones = new JPanel();
        JButton btnAgregar = new JButton("Agregar"), btnActualizar = new JButton("Actualizar"),
                btnEliminar = new JButton("Eliminar"), btnListar = new JButton("Listar");
        botones.add(btnAgregar); botones.add(btnActualizar); botones.add(btnEliminar); botones.add(btnListar);

        modeloTabla = new DefaultTableModel(new Object[]{"ID","Estudiante","Componente","Nota","Comentarios"},0);
        tabla = new JTable(modeloTabla);

        add(form, BorderLayout.NORTH);
        add(new JScrollPane(tabla), BorderLayout.CENTER);
        add(botones, BorderLayout.SOUTH);

        // acciones
        btnAgregar.addActionListener(e -> agregar());
        btnActualizar.addActionListener(e -> actualizar());
        btnEliminar.addActionListener(e -> eliminar());
        btnListar.addActionListener(e -> listar());

        cargarCombos();
        listar();
    }

    private void cargarCombos(){
        cbEstudiante.removeAllItems();
        List<Estudiantes> estudiantes = estudiantesController.listar();
        for (Estudiantes est: estudiantes) cbEstudiante.addItem(est.getEstudiante_id()+" - "+est.getNombre());

        cbCurso.removeAllItems();
        List<Cursos> cursos = cursosController.listar();
        for (Cursos c: cursos) cbCurso.addItem(c.getCurso_id()+" - "+c.getNombre_curso());

        // Si tienes ComponentesEvaluacionDao -> llenar cbComponente con "id - nombre"
    }

    private void agregar(){
        try {
            int estudianteId = Integer.parseInt(((String)cbEstudiante.getSelectedItem()).split(" - ")[0]);
            int componenteId = Integer.parseInt(((String)cbComponente.getSelectedItem()).split(" - ")[0]);
            int nota = (int)((Number)spinnerNota.getValue()).intValue();
            Calificaciones c = new Calificaciones(0, estudianteId, componenteId, nota, txtComentarios.getText());
            if (controller.registrar(c)) { JOptionPane.showMessageDialog(this,"✅ Agregado"); listar(); } else JOptionPane.showMessageDialog(this,"❌ Error");
        } catch (Exception ex){ JOptionPane.showMessageDialog(this,"Error: "+ex.getMessage()); }
    }

    private void actualizar(){
        int fila = tabla.getSelectedRow();
        if (fila==-1){ JOptionPane.showMessageDialog(this,"Selecciona fila"); return; }
        try {
            int id = (int) modeloTabla.getValueAt(fila,0);
            int estudianteId = Integer.parseInt(((String)cbEstudiante.getSelectedItem()).split(" - ")[0]);
            int componenteId = Integer.parseInt(((String)cbComponente.getSelectedItem()).split(" - ")[0]);
            int nota = (int)((Number)spinnerNota.getValue()).intValue();
            Calificaciones c = new Calificaciones(id, estudianteId, componenteId, nota, txtComentarios.getText());
            if (controller.actualizar(c)) { JOptionPane.showMessageDialog(this,"✅ Actualizado"); listar(); } else JOptionPane.showMessageDialog(this,"❌ Error");
        } catch (Exception ex){ JOptionPane.showMessageDialog(this,"Error: "+ex.getMessage()); }
    }

    private void eliminar(){
        int fila = tabla.getSelectedRow();
        if (fila==-1){ JOptionPane.showMessageDialog(this,"Selecciona fila"); return; }
        int id = (int) modeloTabla.getValueAt(fila,0);
        if (controller.eliminar(id)) { JOptionPane.showMessageDialog(this,"🗑️ Eliminado"); listar(); } else JOptionPane.showMessageDialog(this,"❌ Error");
    }

    private void listar(){
        modeloTabla.setRowCount(0);
        List<Calificaciones> lista = controller.listar();
        for (Calificaciones c: lista){
            modeloTabla.addRow(new Object[]{c.getCalificacion_id(), c.getEstudiante_id(), c.getComponente_evaluacion_id(), c.getNota(), c.getComentarios_calificacion()});
        }
    }
}
