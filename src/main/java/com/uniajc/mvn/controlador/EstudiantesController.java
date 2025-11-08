package com.uniajc.mvn.controlador;

import com.uniajc.mvn.modelo.*;
import java.util.List;

public class EstudiantesController {
    private final EstudiantesDao dao = new EstudiantesDao();

    public boolean crear(Estudiantes e) { return dao.crearEstudiante(e); }
    public List<Estudiantes> listar() { return dao.listarEstudiantes(); }
    public boolean actualizar(Estudiantes e) { return dao.actualizarEstudiante(e); }
    public boolean eliminar(int id) { return dao.eliminarEstudiante(id); }
}
