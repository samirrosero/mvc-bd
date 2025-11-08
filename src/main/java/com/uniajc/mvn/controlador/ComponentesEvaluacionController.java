package com.uniajc.mvn.controlador;

import com.uniajc.mvn.modelo.*;
import java.util.List;

public class ComponentesEvaluacionController {
    private final ComponenteEvaluacionDao dao = new ComponenteEvaluacionDao();

    public boolean crear(ComponenteEvaluacion c) { return dao.crearComponente(c); }
    public List<ComponenteEvaluacion> listarPorCorte(int corte_id) { return dao.listarComponentesPorCorte(corte_id); }
    public boolean actualizar(ComponenteEvaluacion c) { return dao.actualizarComponente(c); }
    public boolean eliminar(int id) { return dao.eliminarComponente(id); }
}
