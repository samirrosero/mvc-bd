# Sistema de Gestión Académica

## Descripción

Este es un proyecto de escritorio desarrollado en **Java con Swing** que implementa el patrón de diseño **Modelo-Vista-Controlador (MVC)**. La aplicación está diseñada para la gestión integral de información académica, permitiendo administrar estudiantes, cursos, calificaciones y asistencias, todo ello interactuando con una base de datos **MySQL**.

El proyecto está construido con Maven para la gestión de dependencias y del ciclo de vida de la compilación.

## Características

- **Gestión de Estudiantes**: Operaciones CRUD (Crear, Leer, Actualizar, Eliminar) para los estudiantes.
- **Gestión de Cursos**: Operaciones CRUD para los cursos, con asignación de docentes y periodos académicos.
- **Registro de Calificaciones**: Permite registrar y gestionar las notas de los estudiantes por componentes de evaluación.
- **Control de Asistencia**: Permite registrar la asistencia de los estudiantes a cada clase (`presente`, `ausente`, `tardanza`).
- **Generación de Reportes**:
  - Cálculo de la nota final de un estudiante en un curso.
  - Cálculo del porcentaje de asistencia.
- **Interfaz Gráfica de Usuario (GUI)**: Desarrollada con Java Swing para una interacción intuitiva.
- **Conexión a Base de Datos**: Configuración centralizada para la conexión a MySQL.

## Tecnologías Utilizadas

- **Lenguaje**: Java (JDK 17 o superior)
- **Librería Gráfica**: Java Swing
- **Gestor de Dependencias**: Apache Maven
- **Base de Datos**: MySQL
- **Driver**: MySQL Connector/J

## Prerrequisitos

- **JDK 17** o una versión superior.
- **Apache Maven** 3.6 o una versión superior.
- Un servidor de **MySQL** en ejecución.

## Configuración del Proyecto

### 1. Base de Datos

Ejecuta el script ubicado en `src/main/resources/db.sql` en tu servidor MySQL. Este script creará la base de datos `control_academico`, todas las tablas necesarias e insertará algunos datos de ejemplo para que puedas probar la aplicación.

```sql
-- Creación de la base de datos
CREATE DATABASE IF NOT EXISTS control_academico;
USE control_academico;

-- Creación de tablas (estudiantes, docentes, cursos, calificaciones, etc.)
-- ... (script completo en db.sql)

-- Inserción de datos de ejemplo
-- ... (script completo en db.sql)
```

### 2. Procedimientos Almacenados (Opcional pero recomendado)

El proyecto está preparado para usar procedimientos almacenados que calculan reportes complejos. Aunque el código Java (`ReportesDao.java`) ya incluye las llamadas, necesitas crear estos procedimientos en tu base de datos.

```sql
-- Ejemplo de cómo podrían ser los procedimientos (debes implementarlos)
DELIMITER $$
CREATE PROCEDURE sp_calcular_nota_final(IN p_estudiante_id INT, IN p_curso_id INT)
BEGIN
    -- Lógica para calcular la nota final
    -- ...
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_calcular_asistencia(IN p_estudiante_id INT, IN p_curso_id INT)
BEGIN
    -- Lógica para calcular el porcentaje de asistencia
    -- ...
END$$
DELIMITER ;
```

### 2. Archivo de Configuración

Crea un archivo llamado `config.properties` en la raíz del proyecto con tus credenciales de la base de datos.

**`config.properties`**
```properties
# Configuración de la conexión a la base de datos
URL=jdbc:mysql://localhost:3306/practica_mvn_mvc_bd
USER=root
PASSWORD=tu_contraseña_secreta
```

> **Importante**: Este archivo debe estar en el directorio de trabajo desde donde se ejecuta la aplicación. Si ejecutas desde un IDE, la raíz del proyecto suele ser el directorio de trabajo por defecto.

## Cómo Ejecutar la Aplicación

Puedes ejecutar la aplicación desde la línea de comandos usando Maven o directamente desde tu IDE preferido (IntelliJ, Eclipse, etc.).

### Desde la Línea de Comandos

1.  Abre una terminal en la raíz del proyecto.
2.  Compila el proyecto y empaquétalo en un archivo JAR:
    ```bash
    mvn clean package
    ```
3.  Ejecuta el archivo JAR generado. Asegúrate de que `config.properties` esté en el mismo directorio desde donde ejecutas el comando.
    ```bash
    java -jar target/practica-mvn-mvc-bd-1.0-SNAPSHOT.jar
    ```

### Desde un IDE

1.  Importa el proyecto como un proyecto Maven.
2.  Asegúrate de que el archivo `config.properties` se encuentra en la raíz del proyecto.
3.  Busca la clase `com.uniajc.mvn.Main` y ejecútala.

## Estructura del Proyecto

El código fuente está organizado en paquetes siguiendo el patrón MVC:

- `com.uniajc.mvn`: Contiene la clase principal `Main`.
- `com.uniajc.mvn.modelo`: Clases del modelo de datos (`Estudiante`, `Profesor`, `Curso`), clases DAO (`EstudianteDao`, `ProfesorDao`) y la gestión de la conexión (`ConexionDatabase`).
- `com.uniajc.mvn.controlador`: Clases controladoras que actúan como intermediarias entre la vista y el modelo.
- `com.uniajc.mvn.vista`: Clases de la interfaz gráfica de usuario (vistas) construidas con Swing.

## Buenas Prácticas y Seguridad

- **No subir credenciales a repositorios**: Es una mala práctica subir el archivo `config.properties` con contraseñas a un repositorio de código como Git. Para evitarlo, puedes añadir `config.properties` a tu archivo `.gitignore`.

- **Manejo de recursos**: Es fundamental cerrar siempre los recursos de la base de datos (`Connection`, `Statement`, `ResultSet`) para evitar fugas de recursos. El uso de bloques `try-with-resources` es la forma recomendada de hacerlo.