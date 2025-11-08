package com.uniajc.mvn.controlador;

import com.uniajc.mvn.modelo.*;

public class ReportesController {
    private final ReportesDao dao = new ReportesDao();

    public ReporteNotaFinal calcularNotaFinal(int estudiante_id, int curso_id) {
        return dao.calcularNotaFinal(estudiante_id, curso_id);
    }

    public ReporteAsistencia calcularAsistencia(int estudiante_id, int curso_id) {
        return dao.calcularAsistencia(estudiante_id, curso_id);
    }
}
