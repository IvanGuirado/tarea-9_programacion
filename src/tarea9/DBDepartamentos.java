/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tarea9;

import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.sql.*;

/**
 *
 * @author Ivan
 */
// Definición de la clase DBDepartamentos
public class DBDepartamentos {
    // Constantes para la conexión a la base de datos

    // URL de la base de datos
    final String url_conexion = "jdbc:mysql://localhost/43177602r";
    final String usuario = "root";// Usuario para la conexión
    final String contrasena = "";// Contraseña para la conexión
    Connection conexion;// Objeto de conexión

    // Método para establecer la conexión a la base de datos
    public boolean conectar() {

        try {
            // Intenta establecer la conexión con los parámetros especificados
            conexion = DriverManager.getConnection(url_conexion, usuario,
                    contrasena);
            return true;// Retorna true si la conexión es exitosa
        } catch (SQLException e) {// Maneja cualquier excepción que ocurra 
            //durante la conexión
            e.printStackTrace();
            return false;// Retorna false si ocurre una excepción
        }

    }

    // Método para verificar si un código ya existe
    private boolean existeCodigo(int codigo) {
        //Consulta SQL para contar cuántos departamentos tienen el código 
        //especificado
        String sql = "SELECT COUNT(*) FROM Departamentos WHERE codigo = ?";

        try (PreparedStatement st = conexion.prepareStatement(sql)) {

            // Configura el parámetro de la consulta con el valor del código
            st.setInt(1, codigo);

            // Ejecuta la consulta y obtiene el resultado en un ResultSet
            try (ResultSet rs = st.executeQuery()) {
                // Si hay resultados, verifica si el conteo es mayor a cero
                if (rs.next()) {
                    // Retorna true si el código existe
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DBDepartamentos.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        }
        // Retorna false si el código no existe o si ocurrió una excepción
        return false;
    }

// Método para la inserccion de departamentos en la base de datos
    public boolean insertar(Departamentos dept) {

        //Variable para indicar si la inserción fue exitosa
        boolean insertado = false;

        // Verifica si ya existe un departamento con el mismo código
        if (existeCodigo(dept.getCodigo())) {
            //Si el código existe, imprime un mensaje de error y retorna false
            System.out.println("Error: El código " + dept.getCodigo()
                    + " ya existe.");
            return insertado;
        }
        try {
            //Si el código no existe, intenta insertar el nuevo departamento 
            //en la base de datos
            String sql = "insert into Departamentos (codigo, nombre, "
                    + "id_localizacion, id_manager) VALUES (?, ?, ?, ?)";
            // Configura los parámetros de la consulta
            PreparedStatement st = conexion.prepareStatement(sql);
            st.setInt(1, dept.getCodigo());
            st.setString(2, dept.getNombre());
            st.setInt(3, dept.getId_localizacion());
            st.setInt(4, dept.getId_manager());
            // Ejecuta la consulta de inserción
            st.executeUpdate();
            insertado = true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        // Retorna true si la inserción fue exitosa, false en caso contrario
        return insertado;
    }

    // Método para obtener y mostrar información de todos los departamentos
    public String mostrar() {
        String resultado = "";// Variable para almacenar el resultado a devolver
        try {

            Statement st = conexion.createStatement();
            // Consulta SQL para obtener todos los departamentos
            String sql = "select * from Departamentos";
            // Ejecuta la consulta y obtiene los resultados en un ResultSet
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                //Crear un objeto Departamentos y asignar los valores obtenidos
                //de la base de datos
                Departamentos d = new Departamentos();
                Integer codigo = rs.getInt(1);
                String nombre = rs.getString(2);
                Integer id_localizacion = rs.getInt(3);
                Integer id_manager = rs.getInt(4);
                //Construye la cadena de resultado con la información del 
                //departamento
                resultado += "El codigo del departamento es: " + codigo
                        + " el nombre del departamento es: " + nombre
                        + " el codigo de localizacion es: " + id_localizacion
                        + " el manager es : " + id_manager + " \n";

            }
            rs.close(); // Cerrar el ResultSet
            st.close(); // Cerrar el Statement

        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DBDepartamentos.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
            // Devuelve el mensaje de error
            return "Ha habido un error: " + ex.getMessage();

        }
        return resultado;// Devolver el resultado con la información de los 
        //departamentos

    }

    // Método para eliminar un departamento de la base de datos
    public boolean borrar(Departamentos dept) {
        try {
            // Consulta SQL para eliminar los departamentos con un codigo 
            //especifico
            String sql = "DELETE FROM Departamentos WHERE codigo = ?";

            int borrados;
            try (PreparedStatement st = conexion.prepareStatement(sql)) {
                //Establece el parámetro de la consulta con el código del 
                //departamento
                st.setInt(1, dept.getCodigo());
                //Ejecuta la consulta de eliminación y devuelve el número de 
                //filas afectadas
                borrados = st.executeUpdate();

            }
            // Retorna true si exactamente una fila fue borrada, false en caso 
            //contrario
            return borrados == 1;

        } catch (SQLException e) {
            System.out.println(e.getMessage());

            return false;// Retorna false si ocurre una excepción
        }
    }

    // Método para modificar un departamento en la base de datos
    public boolean modificar(Departamentos dept) {
        try {
            // Consulta SQL para actualizar los campos de un departamento
            String sql = "UPDATE  Departamentos SET nombre= ?, "
                    + "id_localizacion = ?, id_manager=? WHERE codigo= ?";

            int actualizados;
            // Establecer los parámetros de la consulta con los valores del 
            //objeto Departamentos proporcionado
            try (PreparedStatement st = conexion.prepareStatement(sql)) {

                st.setString(1, dept.getNombre());
                st.setInt(2, dept.getId_localizacion());
                st.setInt(3, dept.getId_manager());
                st.setInt(4, dept.getCodigo());
                //Ejecuta la consulta de actualización y devuelve el número 
                //de filas afectadas
                actualizados = st.executeUpdate();

            }
            // Retorna true si exactamente una fila fue actualizada, 
            //false en caso contrario
            return actualizados == 1;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;// Retorna false si ocurre una excepción
        }
    }
// Método para buscar un departamento en la base de datos por su código

    public Departamentos buscar(int codigo) {
        try {
            // Consulta SQL para buscar un departamento por código
            String sql = "SELECT * FROM Departamentos WHERE codigo = ?";

            try (PreparedStatement st = conexion.prepareStatement(sql)) {
                // Establece el parámetro de la consulta con el código 
                //del departamento
                st.setInt(1, codigo);
                // Ejecuta la consulta y obtiene los resultados en un ResultSet
                ResultSet rs = st.executeQuery();

                while (rs.next()) {
                    // Obtiene los valores de las columnas
                    String nombre = rs.getString(2);
                    Integer id_localizacion = rs.getInt(3);
                    Integer id_manager = rs.getInt(4);

                    // Crea un objeto Departamentos con los valores obtenidos
                    Departamentos dept = new Departamentos(codigo, nombre,
                            id_localizacion, id_manager);

                    rs.close(); // Cerrar el ResultSet
                    st.close(); // Cerrar el Statement
                    return dept;//devuelve el departamento.
                }
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DBDepartamentos.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);

        }
        return null;//Retorna null si no se encuentra el departamento 
                    //o si ocurre una excepción
    }

}
