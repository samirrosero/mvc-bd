package com.uniajc.mvn.controlador;

import com.uniajc.mvn.modelo.*;
import java.util.List;

public class ClasesController {
    private final ClasesDao dao = new ClasesDao();

    public boolean crear(Clases c) { return dao.crearClase(c); }
    public List<Clases> listarPorCurso(int curso_id) { return dao.listarClasesPorCurso(curso_id); }
    public boolean actualizar(Clases c) { return dao.actualizarClase(c); }
    public boolean eliminar(int id) { return dao.eliminarClase(id); }
}
