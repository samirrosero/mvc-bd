package com.uniajc.mvn.vista;

import com.uniajc.mvn.controlador.ReportesController;
import com.uniajc.mvn.controlador.CursosController;
import com.uniajc.mvn.controlador.EstudiantesController;
import com.uniajc.mvn.modelo.ReporteAsistencia;
import com.uniajc.mvn.modelo.Cursos;
import com.uniajc.mvn.modelo.Estudiantes;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ReporteAsistenciaFrame extends JFrame {
    private ReportesController controller = new ReportesController();
    private CursosController cursosController = new CursosController();
    private EstudiantesController estudiantesController = new EstudiantesController();

    private JComboBox<String> cbEstudiante, cbCurso;
    private JTextArea txtResultado;

    public ReporteAsistenciaFrame(){
        setTitle("Reporte de Asistencia");
        setSize(700,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel top = new JPanel(new GridLayout(2,2,10,10));
        cbEstudiante = new JComboBox<>();
        cbCurso = new JComboBox<>();
        top.add(new JLabel("Estudiante:")); top.add(cbEstudiante);
        top.add(new JLabel("Curso:")); top.add(cbCurso);

        JButton btnGenerar = new JButton("Generar reporte");
        txtResultado = new JTextArea();
        txtResultado.setEditable(false);

        add(top, BorderLayout.NORTH);
        add(btnGenerar, BorderLayout.CENTER);
        add(new JScrollPane(txtResultado), BorderLayout.SOUTH);

        cargarCombos();
        btnGenerar.addActionListener(e -> generar());
    }

    private void cargarCombos(){
        cbEstudiante.removeAllItems();
        List<Estudiantes> students = new EstudiantesController().listar();
        for (Estudiantes s: students) cbEstudiante.addItem(s.getEstudiante_id()+" - "+s.getNombre());
        cbCurso.removeAllItems();
        List<Cursos> cursos = new CursosController().listar();
        for (Cursos c: cursos) cbCurso.addItem(c.getCurso_id()+" - "+c.getNombre_curso());
    }

    private void generar(){
        try {
            int estudianteId = Integer.parseInt(((String)cbEstudiante.getSelectedItem()).split(" - ")[0]);
            int cursoId = Integer.parseInt(((String)cbCurso.getSelectedItem()).split(" - ")[0]);
            ReporteAsistencia r = controller.calcularAsistencia(estudianteId, cursoId);
            if (r==null){ txtResultado.setText("No hay datos."); return; }
            StringBuilder sb = new StringBuilder();
            sb.append("Estudiante: ").append(r.getEstudiante()).append("\n");
            sb.append("Curso: ").append(r.getNombre_curso()).append("\n");
            sb.append("Total clases: ").append(r.getTotal_clases()).append("\n");
            sb.append("Presentes: ").append(r.getClases_presentes()).append("\n");
            sb.append("Tardanzas: ").append(r.getTardanzas()).append("\n");
            sb.append("Ausencias: ").append(r.getAusencias()).append("\n");
            sb.append("Porcentaje asistencia: ").append(String.format("%.2f%%", r.getPorcentaje_asistencia()));
            txtResultado.setText(sb.toString());
        } catch(Exception ex){ JOptionPane.showMessageDialog(this,"Error: "+ex.getMessage()); }
    }
}
