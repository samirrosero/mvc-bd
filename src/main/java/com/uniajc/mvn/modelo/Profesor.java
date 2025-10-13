package com.uniajc.mvn.modelo;


public class Profesor {
    private int id_Profesor;
    private String nombre;
    private String materia;
    private String correo_electronico;

    public Profesor(int id_Profesor, String nombre, String materia, String correo_electronico) {
        this.id_Profesor = id_Profesor;
        this.nombre = nombre;
        this.materia = materia;
        this.correo_electronico = correo_electronico;
    }

    public int getId() {
        return this.id_Profesor;
    }

    public void setId(int id_Profesor) {
        this.id_Profesor = id_Profesor;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMateria() {
        return this.materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }
    public String getCorreoElectronico() {
        return this.correo_electronico;
    }
    public void setCorreoElectronico(String correo_electronico) {
        this.correo_electronico = correo_electronico;
    }
<<<<<<< HEAD

   // INSERT
    public static void insertarProfesor(Profesor profesor) {
        String sql = "INSERT INTO profesor (id_profesor, nombre, materia, correo_electronico) VALUES (?, ?, ?, ?)";
        try (Connection conexion = ConexionDatabase.getConnection();
             PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, profesor.getId());
            ps.setString(2, profesor.getNombre());
            ps.setString(3, profesor.getMateria());
            ps.setString(4, profesor.getCorreoElectronico());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error al insertar profesor: " + e.getMessage());
        }
    }



    // UPDATE
    public static void actualizarProfesor(Profesor profesor) {
        String sql = "UPDATE profesor SET nombre=?, materia=?, correo_electronico=? WHERE id_profesor=?";
        try (Connection conexion = ConexionDatabase.getConnection();
             PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, profesor.getNombre());
            ps.setString(2, profesor.getMateria());
            ps.setString(3, profesor.getCorreoElectronico());
            ps.setInt(4, profesor.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error al actualizar profesor: " + e.getMessage());
        }
    }

    // DELETE
    public static void eliminarProfesor(int idProfesor) {
        String sql = "DELETE FROM profesor WHERE id_profesor=?";
        try (Connection conexion = ConexionDatabase.getConnection();
             PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idProfesor);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error al eliminar profesor: " + e.getMessage());
        }
    }

    public static List<Profesor> obtenerTodosLosProfesores() {

    List<Profesor> profesores = new ArrayList<>();

    String sql = "SELECT id_profesor, nombre, materia, correo_electronico FROM profesor";

    try {
      Connection conexion = ConexionDatabase.getConnection();

      Statement statement = conexion.createStatement();

      // Ejecutar la sentencias SQL SELECT
      ResultSet resultSet = statement.executeQuery(sql);

      while (resultSet.next()) {
        int id_profesor = resultSet.getInt("id_profesor");
        String nombre = resultSet.getString("nombre");
        String materia = resultSet.getString("materia");
        String correo_electronico = resultSet.getString("correo_electronico");
        Profesor profesor = new Profesor(id_profesor,nombre, materia, correo_electronico);
        profesores.add(profesor);
      }

    } catch (Exception e) {
      System.out.println("Error al insertar el profesor: " + e.getMessage());
      e.printStackTrace();
    }

    return profesores;
  }
=======
>>>>>>> 159fcaad578f75ea199ca3b3495bdc7189db1f40
}