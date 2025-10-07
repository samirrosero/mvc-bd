package com.uniajc.mvn;

import java.util.Scanner;
import com.uniajc.mvn.controlador.ControladorEstudiante;
import com.uniajc.mvn.modelo.Estudiante;
import com.uniajc.mvn.vista.Vista;

public class Main {
 public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        Estudiante modelo = new Estudiante("", 0);
        Vista vista = new Vista();
        ControladorEstudiante controlador = new ControladorEstudiante(modelo, vista);

        System.out.print("Ingrese el nombre del estudiante: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese la edad del estudiante: ");
        int edad = scanner.nextInt();

        Estudiante estudiante = new Estudiante(nombre, edad);
        controlador.agregarEstudiante(estudiante);
        controlador.actualizarVista();
        scanner.close();
    }
}