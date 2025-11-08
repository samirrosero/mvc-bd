package com.uniajc.mvn.controlador;

import com.uniajc.mvn.modelo.*;
import java.util.List;

public class AsistenciasController {
    private final AsistenciasDao dao = new AsistenciasDao();

    public boolean registrar(Asistencias a) { 
        return dao.registrarAsistencia(a);
     }
    public List<Asistencias> obtenerPorEstudiante(int estudiante_id, int curso_id) { 
        return dao.obtenerAsistenciasEstudiante(estudiante_id, curso_id); 
    }
    public boolean actualizar(Asistencias a) { return dao.actualizarAsistencia(a); }
    public boolean eliminar(int id) { return dao.eliminarAsistencia(id); }
}
    