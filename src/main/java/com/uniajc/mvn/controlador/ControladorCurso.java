package com.uniajc.mvn.controlador;

import java.util.List;

import com.uniajc.mvn.modelo.Curso;
import com.uniajc.mvn.modelo.CursoDao;
public class ControladorCurso {
    private CursoDao cursoDao;
    public ControladorCurso (){
        cursoDao = new CursoDao();
     }
     public boolean agregarCurso(String curso, int id_profesor, int id_estudiante) {
         Curso cursoObj = new Curso(0, curso, id_profesor, id_estudiante);
         return cursoDao.insertarCurso(cursoObj);
     }
     public List<Curso> listarCursos() {
         return cursoDao.obtenerTodosLosCursos(0, 0);
     }
     
     public boolean actualizarCurso(int id, String curso, int id_profesor, int id_estudiante) {
         Curso cursoObj = new Curso(id, curso, id_profesor, id_estudiante);
         return cursoDao.actualizarCurso(cursoObj);
     }

     public boolean eliminarCurso(int id) {
         return cursoDao.eliminarCurso(id);
     }
}

