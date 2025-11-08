create database estudiantes;
use estudiantes;

-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema control-academico
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema control-academico
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `control-academico` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `control-academico` ;

-- -----------------------------------------------------
-- Table `control-academico`.`asistencias`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `control-academico`.`asistencias` (
  `asistencia_id` INT NOT NULL AUTO_INCREMENT,
  `estudiante_id` INT NULL DEFAULT NULL,
  `curso_id` INT NULL DEFAULT NULL,
  `fecha_clase` DATE NULL DEFAULT NULL,
  `estado_asistencia` ENUM('presente', 'ausente', 'tardanza') CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NULL DEFAULT NULL,
  `novedades` TEXT CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NULL DEFAULT NULL,
  PRIMARY KEY (`asistencia_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `control-academico`.`calificaciones`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `control-academico`.`calificaciones` (
  `calificacion_id` INT NOT NULL AUTO_INCREMENT,
  `estudiante_id` INT NULL DEFAULT NULL,
  `componente_evaluacion_id` INT NULL DEFAULT NULL,
  `nota` DECIMAL(3,2) NOT NULL,
  `comentarios_calificacion` TEXT CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NULL DEFAULT NULL,
  PRIMARY KEY (`calificacion_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 12
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `control-academico`.`clases`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `control-academico`.`clases` (
  `clase_id` INT NOT NULL AUTO_INCREMENT,
  `curso_id` INT NULL DEFAULT NULL,
  `numero_clase` INT NULL DEFAULT NULL,
  `fecha_clase` DATE NULL DEFAULT NULL,
  `tema_clase` VARCHAR(255) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NULL DEFAULT NULL,
  `descripcion_clase` TEXT CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NULL DEFAULT NULL,
  `comentarios_clase` TEXT CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NULL DEFAULT NULL,
  PRIMARY KEY (`clase_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `control-academico`.`componentes_evaluacion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `control-academico`.`componentes_evaluacion` (
  `componente_evaluacion_id` INT NOT NULL AUTO_INCREMENT,
  `corte_evaluacion_id` INT NULL DEFAULT NULL,
  `nombre_componente` VARCHAR(255) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NOT NULL,
  `porcentaje` DECIMAL(5,2) NOT NULL,
  PRIMARY KEY (`componente_evaluacion_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 22
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `control-academico`.`cortes_evaluacion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `control-academico`.`cortes_evaluacion` (
  `corte_evaluacion_id` INT NOT NULL AUTO_INCREMENT,
  `curso_id` INT NULL DEFAULT NULL,
  `periodo_academico_id` INT NULL DEFAULT NULL,
  `nombre_corte` VARCHAR(255) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NOT NULL,
  `porcentaje` DECIMAL(5,2) NOT NULL,
  `comentarios_corte` TEXT CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NULL DEFAULT NULL,
  PRIMARY KEY (`corte_evaluacion_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 10
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `control-academico`.`cursos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `control-academico`.`cursos` (
  `curso_id` INT NOT NULL AUTO_INCREMENT,
  `nombre_curso` VARCHAR(255) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NOT NULL,
  `periodo_academico_id` INT NULL DEFAULT NULL,
  `docente_id` INT NULL DEFAULT NULL,
  `descripcion_curso` TEXT CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NULL DEFAULT NULL,
  PRIMARY KEY (`curso_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `control-academico`.`docentes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `control-academico`.`docentes` (
  `docente_id` INT NOT NULL AUTO_INCREMENT,
  `nombre_docente` VARCHAR(255) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NOT NULL,
  `identificacion` VARCHAR(20) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NULL DEFAULT NULL,
  `tipo_identificacion` VARCHAR(20) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NULL DEFAULT NULL,
  `genero` VARCHAR(20) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NULL DEFAULT NULL,
  `correo` VARCHAR(255) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NULL DEFAULT NULL,
  `titulo_estudios` VARCHAR(255) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NULL DEFAULT NULL,
  `idiomas` VARCHAR(255) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NULL DEFAULT NULL,
  `certificaciones` TEXT CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NULL DEFAULT NULL,
  PRIMARY KEY (`docente_id`),
  UNIQUE INDEX `identificacion` (`identificacion` ASC) VISIBLE,
  UNIQUE INDEX `correo` (`correo` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `control-academico`.`docentes_cursos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `control-academico`.`docentes_cursos` (
  `docente_id` INT NOT NULL,
  `curso_id` INT NOT NULL,
  PRIMARY KEY (`docente_id`, `curso_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `control-academico`.`estudiantes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `control-academico`.`estudiantes` (
  `estudiante_id` INT NOT NULL AUTO_INCREMENT,
  `identificacion` VARCHAR(20) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NULL DEFAULT NULL,
  `nombre` VARCHAR(255) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NOT NULL,
  `correo_institucional` VARCHAR(255) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NULL DEFAULT NULL,
  `correo_personal` VARCHAR(255) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NULL DEFAULT NULL,
  `telefono` VARCHAR(20) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NULL DEFAULT NULL,
  `es_vocero` TINYINT(1) NULL DEFAULT '0',
  `comentarios` TEXT CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NULL DEFAULT NULL,
  `tipo_documento` VARCHAR(20) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NULL DEFAULT NULL,
  `genero` VARCHAR(20) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NULL DEFAULT NULL,
  PRIMARY KEY (`estudiante_id`),
  UNIQUE INDEX `identificacion` (`identificacion` ASC) VISIBLE,
  UNIQUE INDEX `correo_institucional` (`correo_institucional` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 47
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `control-academico`.`periodos_academicos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `control-academico`.`periodos_academicos` (
  `periodo_academico_id` INT NOT NULL AUTO_INCREMENT,
  `nombre_periodo` VARCHAR(255) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NOT NULL,
  `fecha_inicio` DATE NULL DEFAULT NULL,
  `fecha_fin` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`periodo_academico_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
