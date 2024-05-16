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
public class DBDepartamentos {

    final String url_conexion = "jdbc:mysql://localhost/43177602r";
    final String usuario = "root";
    final String contrasena = "";
    Connection conexion;

    public boolean conectar() {

        try {
            conexion = DriverManager.getConnection(url_conexion, usuario, contrasena);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean insertar(Departamentos dept) {

        try {
            String sql = "insert into Departamentos values (?,?,?)";
            PreparedStatement st = conexion.prepareStatement(sql);
            st.setInt(1, dept.getCodigo());
            st.setString(2, dept.getNombre());
            st.setInt(3, dept.getId_localizacion());
            st.setInt(4, dept.getId_manager());

            int insertados = st.executeUpdate(sql);
            st.close();
            return insertados == 1;
        } catch (SQLException ex) {
            //Logger.getLogger(DBDepartamentos.class.getName()).log(Level.ERROR, null, ex);
            return false;
        }
    }

    public String mostrar() {
        try {
            String resultado = "";
            String sql = "select*from Departamentos";
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Integer codigo = rs.getInt(1);
                String nombre = rs.getString(2);
                Integer id_localizacion = rs.getInt(3);
                Integer id_manager = rs.getInt(4);
                resultado += codigo + " " + nombre + " " + id_localizacion + " " + id_manager + " \n";

            }
            st.close();
            return resultado;
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DBDepartamentos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            return "Ha habido un error: " + ex.getMessage();
        }

    }

    public boolean borrar(String codigo) {
        try {
            String sql = "delete from Departamentos where codigo=?";
            PreparedStatement st = conexion.prepareStatement(sql);
            st.setString(1, codigo);

            int borrados = st.executeUpdate(sql);
            st.close();
            return borrados == 1;
        } catch (SQLException ex) {
            //Logger.getLogger(DBDepartamentos.class.getName()).log(Level.ERROR, null, ex);
            return false;
        }
    }

    public boolean modificar(Departamentos dept) {
        try {
            String sql = "update  Departamentos set nombre=?, id_localizacion=?, id_manager=? where codigo=?";
            PreparedStatement st = conexion.prepareStatement(sql);
            st.setInt(1, dept.getCodigo());
            st.setString(2, dept.getNombre());
            st.setInt(3, dept.getId_localizacion());
            st.setInt(4, dept.getId_manager());
            

            int actualizados = st.executeUpdate(sql);
            st.close();
            return actualizados == 1;
        } catch (SQLException ex) {
            //Logger.getLogger(DBDepartamentos.class.getName()).log(Level.ERROR, null, ex);
            return false;
        }
    }

    public Departamentos buscar(int codigo) {
        try {
            String resultado = "";
            String sql = "select*from Departamentos where codigo=?";
            PreparedStatement st = conexion.prepareStatement(sql);
            st.setInt(1, codigo);
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String nombre = rs.getString(1);
                Integer id_localizacion = rs.getInt(2);
                Integer id_manager = rs.getInt(3);
                Departamentos dept = new Departamentos(codigo, nombre, id_localizacion, id_manager);
                return dept;

            }
            st.close();
            return null;
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DBDepartamentos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            return null;
        }

    }

}
