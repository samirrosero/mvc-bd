package com.uniajc.mvn.controlador;

import com.uniajc.mvn.modelo.*;
import java.util.List;

public class CursosController {
    private final CursosDao dao = new CursosDao();

    public boolean crear(Cursos c) { return dao.crearCurso(c); }
    public Cursos obtener(int id) { return dao.obtenerCurso(id); }
    public List<Cursos> listar() { return dao.listarCursos(); }
    public boolean actualizar(Cursos c) { return dao.actualizarCursos(c); }
    public boolean eliminar(int id) { return dao.eliminarCurso(id); }
}
