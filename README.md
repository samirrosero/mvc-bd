# practica-mvn-mvc-bd

Descripcion breve

Proyecto Java Maven (MVC) que usa un archivo `config.properties` en la raíz para leer los datos de conexión a la base de datos.

## Uso de `config.properties`

- Ubicación: el archivo `config.properties` debe estar en el directorio de trabajo (working directory) desde donde se ejecuta la aplicación. Si ejecutas el JAR con `java -jar target/practica-mvn-mvc-bd-1.0-SNAPSHOT.jar`, coloca `config.properties` en la misma carpeta donde ejecutas el comando (normalmente junto al JAR).

- Formato: es un archivo Java properties con pares clave=valor. Ejemplo recomendado:

```
# config.properties (ejemplo)
URL=jdbc:mysql://localhost:3306/practica_mvn_mvc_bd
USER=root
PASSWORD=TU_PASSWORD
```

Nota: en tu repositorio actual `config.properties` contiene:

```
URL =jdbc:mysql://localhost:3306/practica_mvn_mvc_bd
USER =root
PASSWORD =1234567890
```

Las diferencias de espacios alrededor del `=` no impiden que `Properties.load(...)` funcione, pero es más limpio no usar espacios.

## Cómo lo usa el código

La clase `com.uniajc.mvn.modelo.ConexionDatabase` carga el archivo con:

```java
properties.load(new FileInputStream(new File("config.properties")));
```

Esto significa que la ruta es relativa al directorio de trabajo. Si el archivo no se encuentra, la aplicación captura `FileNotFoundException` (en el código actual esa excepción está silenciosa), por lo que puede no mostrarse un mensaje claro si falta el archivo.

También se intenta cargar el driver MySQL con:

```java
Class.forName("com.mysql.cj.jdbc.Driver");
```

Si ves "Error al cargar el driver de MySQL", revisa que la dependencia `mysql-connector-java` esté presente en el `pom.xml`.

## Ejecutar (PowerShell)

1) Compilar y crear el JAR:

```powershell
mvn clean package
```

2) Coloca `config.properties` en el mismo directorio donde ejecutarás el JAR (por ejemplo en la raíz del proyecto o en `target/` junto al JAR). Luego ejecuta:

```powershell
cd target
java -jar practica-mvn-mvc-bd-1.0-SNAPSHOT.jar
```

O desde la raíz del proyecto (si config.properties está en la raíz):

```powershell
mvn clean package; java -jar target/practica-mvn-mvc-bd-1.0-SNAPSHOT.jar
```

Si ejecutas desde tu IDE (Eclipse, IntelliJ), asegúrate de que la configuración de Run tenga el directorio de trabajo apuntando a la carpeta que contiene `config.properties`.

## Seguridad y buenas prácticas

- No guardes contraseñas en texto plano en repositorios públicos. Usa variables de entorno, un archivo de configuración fuera del control de versiones, o un sistema de secretos.
- Para evitar que `config.properties` se suba al repositorio, añade una entrada a `.gitignore` (por ejemplo `config.properties`) y mantén un `config.properties.example` con valores de ejemplo.

Ejemplo `.gitignore` (añade en la raíz):

```
# Ignorar configuración local con credenciales
config.properties
```

- Alternativa segura: leer la contraseña desde una variable de entorno y usarla si existe, cayendo al `config.properties` como fallback.

## Solución de problemas comunes

- Aviso "intentando conectar..." seguido de "Error al conectar a la base de datos: ...": revisa la URL, usuario y contraseña. Asegúrate de que MySQL esté en ejecución y que la base de datos `practica_mvn_mvc_bd` exista.
- Si aparece "Error al cargar el driver de MySQL": añade dependencia en `pom.xml`:

```xml
<dependency>
  <groupId>mysql</groupId>
  <artifactId>mysql-connector-java</artifactId>
  <version>8.0.33</version>
</dependency>
```

- Si el archivo `config.properties` no se encuentra: coloca el archivo en el directorio desde el que ejecutas la aplicación o modifica el código para cargarlo desde un recurso del classpath.

## Mejora sugerida (opcional)

Cambiar `ConexionDatabase` para que primero busque variables de entorno y si no existen, lea `config.properties`. También se puede mostrar un mensaje claro cuando `config.properties` no existe (actualmente la excepción está comentada).

Si quieres, puedo:
- Crear un `config.properties.example` con valores de ejemplo.
- Añadir `config.properties` a `.gitignore`.
- Modificar `ConexionDatabase` para mejorar manejo de errores y soporte de variables de entorno.

Indícame cuál de estas acciones quieres que haga y lo implemento.