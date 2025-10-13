# Sistema de Gestión Académica

# Integrantes
## Samir Rosero y Valeri Solis

## Descripción

Este es un proyecto de escritorio desarrollado en Java con Swing que implementa el patrón de diseño Modelo-Vista-Controlador (MVC). La aplicación permite gestionar entidades académicas como Estudiantes, Profesores y Cursos, interactuando con una base de datos MySQL.

El proyecto está construido con Maven para la gestión de dependencias y del ciclo de vida de la compilación.

## Características

- **Gestión de Estudiantes**: Permite agregar, actualizar, eliminar y listar estudiantes.
- **Gestión de Profesores**: Permite agregar, actualizar, eliminar y listar profesores.
- **Gestión de Cursos**: Permite asignar profesores y estudiantes a cursos, así como realizar operaciones CRUD sobre ellos.
- **Interfaz Gráfica de Usuario (GUI)**: Desarrollada con Java Swing para una interacción intuitiva.
- **Conexión a Base de Datos**: Configuración de la conexión a MySQL a través de un archivo externo `config.properties`.

## Tecnologías Utilizadas

- **Lenguaje**: Java 17
- **Librería Gráfica**: Java Swing
- **Gestor de Dependencias**: Apache Maven
- **Base de Datos**: MySQL
- **Driver**: MySQL Connector/J

## Prerrequisitos

- **JDK 17** o superior.
- **Apache Maven** 3.6 o superior.
- Un servidor de **MySQL** en ejecución.

## Configuración del Proyecto

### 1. Base de Datos

Primero, crea una base de datos en tu servidor MySQL. Puedes usar el siguiente script SQL para crear la base de datos y las tablas necesarias.

```sql
CREATE DATABASE IF NOT EXISTS practica_mvn_mvc_bd;
USE practica_mvn_mvc_bd;

CREATE TABLE estudiante (
    id_estudiante INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    edad INT
);

CREATE TABLE profesor (
    id_profesor INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    materia VARCHAR(255),
    correo_electronico VARCHAR(255) UNIQUE
);

CREATE TABLE curso (
    id_curso INT AUTO_INCREMENT PRIMARY KEY,
    nombre_curso VARCHAR(255) NOT NULL,
    id_profesor INT,
    id_estudiante INT,
    FOREIGN KEY (id_profesor) REFERENCES profesor(id_profesor) ON DELETE SET NULL,
    FOREIGN KEY (id_estudiante) REFERENCES estudiante(id_estudiante) ON DELETE CASCADE
);
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
