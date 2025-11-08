package com.uniajc.mvn.controlador;

import com.uniajc.mvn.modelo.*;
import java.util.List;

public class CortesEvaluacionController {
    private final CortesEvaluacionDao dao = new CortesEvaluacionDao();

    public boolean crear(CortesEvaluacion c) { return dao.crearCorte(c); }
    public List<CortesEvaluacion> listarPorCurso(int curso_id) { return dao.listarCortesPorCurso(curso_id); }
    public boolean actualizar(CortesEvaluacion c) { return dao.actualizarCorte(c); }
    public boolean eliminar(int id) { return dao.eliminarCorte(id); }
}
