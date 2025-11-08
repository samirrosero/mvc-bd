package com.uniajc.mvn.controlador;

import com.uniajc.mvn.modelo.*;
import java.util.List;

public class PeriodosAcademicosController {
    private final PeriodosAcademicosDao dao = new PeriodosAcademicosDao();

    public boolean crear(PeriodosAcademicos p) { return dao.crearPeriodo(p); }
    public List<PeriodosAcademicos> listar() { return dao.listarPeriodos(); }
    public boolean actualizar(PeriodosAcademicos p) { return dao.actualizarPeriodo(p); }
    public boolean eliminar(int id) { return dao.eliminarPeriodo(id); }
}
