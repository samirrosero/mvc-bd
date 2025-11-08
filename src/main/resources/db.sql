-- MySQL Workbench Forward Engineering
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema control_academico
-- -----------------------------------------------------
CREATE DATABASE IF NOT EXISTS control_academico
  DEFAULT CHARACTER SET utf8mb4
  COLLATE utf8mb4_0900_ai_ci;
USE control_academico;

-- -----------------------------------------------------
-- Table `asistencias`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS asistencias (
  asistencia_id INT NOT NULL AUTO_INCREMENT,
  estudiante_id INT NULL DEFAULT NULL,
  curso_id INT NULL DEFAULT NULL,
  fecha_clase DATE NULL DEFAULT NULL,
  estado_asistencia ENUM('presente', 'ausente', 'tardanza') NULL DEFAULT NULL,
  novedades TEXT NULL DEFAULT NULL,
  PRIMARY KEY (asistencia_id)
) ENGINE = InnoDB AUTO_INCREMENT = 5;

-- -----------------------------------------------------
-- Table `calificaciones`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS calificaciones (
  calificacion_id INT NOT NULL AUTO_INCREMENT,
  estudiante_id INT NULL DEFAULT NULL,
  componente_evaluacion_id INT NULL DEFAULT NULL,
  nota DECIMAL(3,2) NOT NULL,
  comentarios_calificacion TEXT NULL DEFAULT NULL,
  PRIMARY KEY (calificacion_id)
) ENGINE = InnoDB AUTO_INCREMENT = 12;

-- -----------------------------------------------------
-- Table `clases`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS clases (
  clase_id INT NOT NULL AUTO_INCREMENT,
  curso_id INT NULL DEFAULT NULL,
  numero_clase INT NULL DEFAULT NULL,
  fecha_clase DATE NULL DEFAULT NULL,
  tema_clase VARCHAR(255) NULL DEFAULT NULL,
  descripcion_clase TEXT NULL DEFAULT NULL,
  comentarios_clase TEXT NULL DEFAULT NULL,
  PRIMARY KEY (clase_id)
) ENGINE = InnoDB AUTO_INCREMENT = 8;

-- -----------------------------------------------------
-- Table `componentes_evaluacion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS componentes_evaluacion (
  componente_evaluacion_id INT NOT NULL AUTO_INCREMENT,
  corte_evaluacion_id INT NULL DEFAULT NULL,
  nombre_componente VARCHAR(255) NOT NULL,
  porcentaje DECIMAL(5,2) NOT NULL,
  PRIMARY KEY (componente_evaluacion_id)
) ENGINE = InnoDB AUTO_INCREMENT = 22;

-- -----------------------------------------------------
-- Table `cortes_evaluacion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS cortes_evaluacion (
  corte_evaluacion_id INT NOT NULL AUTO_INCREMENT,
  curso_id INT NULL DEFAULT NULL,
  periodo_academico_id INT NULL DEFAULT NULL,
  nombre_corte VARCHAR(255) NOT NULL,
  porcentaje DECIMAL(5,2) NOT NULL,
  comentarios_corte TEXT NULL DEFAULT NULL,
  PRIMARY KEY (corte_evaluacion_id)
) ENGINE = InnoDB AUTO_INCREMENT = 10;

-- -----------------------------------------------------
-- Table `cursos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS cursos (
  curso_id INT NOT NULL AUTO_INCREMENT,
  nombre_curso VARCHAR(255) NOT NULL,
  periodo_academico_id INT NULL DEFAULT NULL,
  docente_id INT NULL DEFAULT NULL,
  descripcion_curso TEXT NULL DEFAULT NULL,
  PRIMARY KEY (curso_id)
) ENGINE = InnoDB AUTO_INCREMENT = 4;

-- -----------------------------------------------------
-- Table `docentes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS docentes (
  docente_id INT NOT NULL AUTO_INCREMENT,
  nombre_docente VARCHAR(255) NOT NULL,
  identificacion VARCHAR(20) NULL DEFAULT NULL,
  tipo_identificacion VARCHAR(20) NULL DEFAULT NULL,
  genero VARCHAR(20) NULL DEFAULT NULL,
  correo VARCHAR(255) NULL DEFAULT NULL,
  titulo_estudios VARCHAR(255) NULL DEFAULT NULL,
  idiomas VARCHAR(255) NULL DEFAULT NULL,
  certificaciones TEXT NULL DEFAULT NULL,
  PRIMARY KEY (docente_id),
  UNIQUE INDEX identificacion (identificacion ASC),
  UNIQUE INDEX correo (correo ASC)
) ENGINE = InnoDB AUTO_INCREMENT = 3;

-- -----------------------------------------------------
-- Table `docentes_cursos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS docentes_cursos (
  docente_id INT NOT NULL,
  curso_id INT NOT NULL,
  PRIMARY KEY (docente_id, curso_id)
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `estudiantes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS estudiantes (
  estudiante_id INT NOT NULL AUTO_INCREMENT,
  identificacion VARCHAR(20) NULL DEFAULT NULL,
  nombre VARCHAR(255) NOT NULL,
  correo_institucional VARCHAR(255) NULL DEFAULT NULL,
  correo_personal VARCHAR(255) NULL DEFAULT NULL,
  telefono VARCHAR(20) NULL DEFAULT NULL,
  es_vocero TINYINT(1) NULL DEFAULT '0',
  comentarios TEXT NULL DEFAULT NULL,
  tipo_documento VARCHAR(20) NULL DEFAULT NULL,
  genero VARCHAR(20) NULL DEFAULT NULL,
  PRIMARY KEY (estudiante_id),
  UNIQUE INDEX identificacion (identificacion ASC),
  UNIQUE INDEX correo_institucional (correo_institucional ASC)
) ENGINE = InnoDB AUTO_INCREMENT = 47;

-- -----------------------------------------------------
-- Table `periodos_academicos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS periodos_academicos (
  periodo_academico_id INT NOT NULL AUTO_INCREMENT,
  nombre_periodo VARCHAR(255) NOT NULL,
  fecha_inicio DATE NULL DEFAULT NULL,
  fecha_fin DATE NULL DEFAULT NULL,
  PRIMARY KEY (periodo_academico_id)
) ENGINE = InnoDB AUTO_INCREMENT = 3;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


-- -----------------------------------------------------
-- Tabla: periodos_academicos
-- -----------------------------------------------------
INSERT INTO periodos_academicos (nombre_periodo, fecha_inicio, fecha_fin)
VALUES
('2025-1', '2025-01-20', '2025-06-30'),
('2025-2', '2025-07-15', '2025-12-15');

-- -----------------------------------------------------
-- Tabla: docentes
-- -----------------------------------------------------
INSERT INTO docentes (nombre_docente, identificacion, tipo_identificacion, genero, correo, titulo_estudios, idiomas, certificaciones)
VALUES
('Laura Gómez', '1001234567', 'CC', 'Femenino', 'laura.gomez@uniajc.edu.co', 'Lic. Matemáticas', 'Español, Inglés', 'Certificación en Didáctica'),
('Carlos Pérez', '1009876543', 'CC', 'Masculino', 'carlos.perez@uniajc.edu.co', 'Ing. de Sistemas', 'Español', 'Diplomado en TIC');

-- -----------------------------------------------------
-- Tabla: cursos
-- -----------------------------------------------------
INSERT INTO cursos (nombre_curso, periodo_academico_id, docente_id, descripcion_curso)
VALUES
('Matemáticas I', 1, 1, 'Curso introductorio de álgebra y trigonometría.'),
('Programación I', 1, 2, 'Fundamentos de programación en Java.');

-- -----------------------------------------------------
-- Tabla: docentes_cursos
-- -----------------------------------------------------
INSERT INTO docentes_cursos (docente_id, curso_id)
VALUES
(1, 1),
(2, 2);

-- -----------------------------------------------------
-- Tabla: estudiantes
-- -----------------------------------------------------
INSERT INTO estudiantes (identificacion, nombre, correo_institucional, correo_personal, telefono, es_vocero, comentarios, tipo_documento, genero)
VALUES
('1098765432', 'María Rodríguez', 'maria.rodriguez@uniajc.edu.co', 'mariarodriguez@gmail.com', '3201112233', 0, 'Estudiante puntual.', 'CC', 'Femenino'),
('1087654321', 'Juan Martínez', 'juan.martinez@uniajc.edu.co', 'juanmartinez@gmail.com', '3102223344', 1, 'Vocero del grupo.', 'CC', 'Masculino'),
('1076543210', 'Andrés López', 'andres.lopez@uniajc.edu.co', 'andreslopez@gmail.com', '3003334455', 0, NULL, 'CC', 'Masculino');

-- -----------------------------------------------------
-- Tabla: cortes_evaluacion
-- -----------------------------------------------------
INSERT INTO cortes_evaluacion (curso_id, periodo_academico_id, nombre_corte, porcentaje, comentarios_corte)
VALUES
(1, 1, 'Primer Corte', 30.00, 'Temas iniciales del curso'),
(1, 1, 'Segundo Corte', 30.00, 'Temas intermedios'),
(1, 1, 'Tercer Corte', 40.00, 'Temas finales y examen final'),
(2, 1, 'Primer Corte', 50.00, 'Fundamentos de programación'),
(2, 1, 'Segundo Corte', 50.00, 'Proyecto final en Java');

-- -----------------------------------------------------
-- Tabla: componentes_evaluacion
-- -----------------------------------------------------
INSERT INTO componentes_evaluacion (corte_evaluacion_id, nombre_componente, porcentaje)
VALUES
(1, 'Parcial 1', 20.00),
(1, 'Talleres', 10.00),
(2, 'Parcial 2', 20.00),
(2, 'Tareas', 10.00),
(3, 'Examen Final', 40.00),
(4, 'Quiz', 20.00),
(4, 'Laboratorio', 30.00),
(5, 'Proyecto Final', 50.00);

-- -----------------------------------------------------
-- Tabla: calificaciones
-- -----------------------------------------------------
INSERT INTO calificaciones (estudiante_id, componente_evaluacion_id, nota, comentarios_calificacion)
VALUES
(1, 1, 4.5, 'Buen desempeño en el parcial.'),
(1, 2, 4.0, 'Tareas completas.'),
(2, 1, 3.8, 'Puede mejorar.'),
(3, 3, 4.2, 'Buen avance.'),
(1, 8, 4.8, 'Excelente proyecto final.');

-- -----------------------------------------------------
-- Tabla: clases
-- -----------------------------------------------------
INSERT INTO clases (curso_id, numero_clase, fecha_clase, tema_clase, descripcion_clase, comentarios_clase)
VALUES
(1, 1, '2025-02-01', 'Números reales y enteros', 'Introducción al conjunto numérico.', 'Clase participativa.'),
(1, 2, '2025-02-05', 'Ecuaciones lineales', 'Resolución de ecuaciones básicas.', 'Requiere refuerzo.'),
(2, 1, '2025-02-02', 'Introducción a Java', 'Conceptos básicos del lenguaje.', 'Buena asistencia.'),
(2, 2, '2025-02-09', 'Condicionales y bucles', 'Uso de estructuras de control.', 'Excelente participación.');

-- -----------------------------------------------------
-- Tabla: asistencias
-- -----------------------------------------------------
INSERT INTO asistencias (estudiante_id, curso_id, fecha_clase, estado_asistencia, novedades)
VALUES
(1, 1, '2025-02-01', 'presente', NULL),
(2, 1, '2025-02-01', 'tardanza', 'Llegó 10 minutos tarde.'),
(3, 1, '2025-02-01', 'ausente', 'No presentó excusa.'),
(1, 2, '2025-02-02', 'presente', NULL),
(2, 2, '2025-02-02', 'presente', NULL);
