package com.uniajc.mvn.vista;

import com.uniajc.mvn.controlador.DocentesController;
import com.uniajc.mvn.modelo.Docentes;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class DocentesFrame extends JFrame {
    private DocentesController controller = new DocentesController();

    private JTextField txtNombre, txtIdentificacion, txtCorreo, txtTitulo, txtIdiomas, txtCertificaciones;
    private JComboBox<String> cbTipoIdentificacion, cbGenero;
    private JTable tabla;
    private DefaultTableModel modeloTabla;

    public DocentesFrame(){
        setTitle("Gestión de Docentes");
        setSize(900,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel form = new JPanel(new GridLayout(5,4,10,10));
        txtNombre = new JTextField();
        txtIdentificacion = new JTextField();
        cbTipoIdentificacion = new JComboBox<>(new String[]{"C.C.","T.I."});
        cbGenero = new JComboBox<>(new String[]{"Masculino","Femenino"});
        txtCorreo = new JTextField();
        txtTitulo = new JTextField();
        txtIdiomas = new JTextField();
        txtCertificaciones = new JTextField();

        form.add(new JLabel("Nombre:")); form.add(txtNombre);
        form.add(new JLabel("Identificacion:")); form.add(txtIdentificacion);
        form.add(new JLabel("Tipo identificacion:")); form.add(cbTipoIdentificacion);
        form.add(new JLabel("Genero:")); form.add(cbGenero);
        form.add(new JLabel("Correo:")); form.add(txtCorreo);
        form.add(new JLabel("Titulo estudios:")); form.add(txtTitulo);
        form.add(new JLabel("Idiomas:")); form.add(txtIdiomas);
        form.add(new JLabel("Certificaciones:")); form.add(txtCertificaciones);

        JPanel botones = new JPanel();
        JButton btnAgregar = new JButton("Agregar"), btnActualizar = new JButton("Actualizar"),
                btnEliminar = new JButton("Eliminar"), btnListar = new JButton("Listar");
        botones.add(btnAgregar); botones.add(btnActualizar); botones.add(btnEliminar); botones.add(btnListar);

        modeloTabla = new DefaultTableModel(new Object[]{"ID","Nombre","Identificacion","Tipo","Genero","Correo","Titulo","Idiomas","Certificaciones"},0);
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
            Docentes d = new Docentes(ABORT, getName(), getName(), getName(), getName(), getName(), getTitle(), getWarningString(), getName());
            d.setNombre_docente(txtNombre.getText());
            d.setIdentificacion(txtIdentificacion.getText());
            d.setTipo_identificacion(cbTipoIdentificacion.getSelectedItem().toString());
            d.setGenero(cbGenero.getSelectedItem().toString());
            d.setCorreo(txtCorreo.getText());
            d.setTitulo_estudios(txtTitulo.getText());
            d.setIdiomas(txtIdiomas.getText());
            d.setCertificaciones(txtCertificaciones.getText());
            if(controller.crear(d)){ JOptionPane.showMessageDialog(this,"✅ Agregado"); listar(); limpiar(); } else JOptionPane.showMessageDialog(this,"❌ Error");
        } catch(Exception ex){ JOptionPane.showMessageDialog(this,"Error: "+ex.getMessage()); }
    }

    private void actualizar(){ /* similar */ }
    private void eliminar(){
        int fila = tabla.getSelectedRow();
        if(fila==-1){ JOptionPane.showMessageDialog(this,"Selecciona fila"); return;}
        int id = (int) modeloTabla.getValueAt(fila,0);
        if(controller.eliminar(id)){ JOptionPane.showMessageDialog(this,"🗑️ Eliminado"); listar(); } else JOptionPane.showMessageDialog(this,"❌ Error");
    }

    private void listar(){
        modeloTabla.setRowCount(0);
        List<Docentes> lista = controller.listar();
        for(Docentes d: lista){
            modeloTabla.addRow(new Object[]{d.getDocente_id(), d.getNombre_docente(), d.getIdentificacion(), d.getTipo_identificacion(), d.getGenero(), d.getCorreo(), d.getTitulo_estudios(), d.getIdiomas(), d.getCertificaciones()});
        }
    }

    private void limpiar(){
        txtNombre.setText(""); txtIdentificacion.setText(""); txtCorreo.setText(""); txtTitulo.setText(""); txtIdiomas.setText(""); txtCertificaciones.setText("");
    }
}
