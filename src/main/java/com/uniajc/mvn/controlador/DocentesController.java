package com.uniajc.mvn.controlador;

import com.uniajc.mvn.modelo.*;
import java.util.List;

public class DocentesController {
    private final DocentesDao dao = new DocentesDao();

    public boolean crear(Docentes d) { return dao.crearDocente(d); }
    public Docentes obtener(int id) { return dao.obtenerDocente(id); }
    public List<Docentes> listar() { return dao.listarDocentes(); }
    public boolean actualizar(Docentes d) { return dao.actualizarDocente(d); }
    public boolean eliminar(int id) { return dao.eliminarDocente(id); }
}
