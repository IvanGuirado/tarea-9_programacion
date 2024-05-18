/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tarea9;

/**
 *
 * @author Ivan
 */
public class Departamentos {

    /**
     * Cada departamento debe tener los atributos codigo,nombre,id_localizacion
     * y id_manager.
     */
    //código, nombre, id de localizacion e id del manager
    private int codigo;
    private String nombre;
    private int id_localizacion;
    private int id_manager;

    // Constructor por defecto de la clase Departamentos
    // No recibe parámetros y no hace nada específico.
    public Departamentos() {
    }

    // Constructor parametrizado de la clase Departamentos
    // Permite inicializar un objeto Departamentos con valores específicos.
    public Departamentos(int codigo, String nombre, int id_localizacion,
            int id_manager) {
        this.codigo = codigo;// Inicializa el atributo codigo con el valor del parámetro codigo
        this.nombre = nombre;// Inicializa el atributo nombre con el valor del parámetro nombre
        this.id_localizacion = id_localizacion;// Inicializa el atributo nombre con el valor del parámetro id_localizacion
        this.id_manager = id_manager;// Inicializa el atributo nombre con el valor del parámetro id_manager
    }

    // Métodos getter y setter para el atributo codigo
    public int getCodigo() {
        return codigo; // Devuelve el valor del atributo codigo
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo; // Establece el valor del atributo codigo
    }

// Métodos getter y setter para el atributo nombre
    public String getNombre() {
        return nombre; // Devuelve el valor del atributo nombre
    }

    public void setNombre(String nombre) {
        this.nombre = nombre; // Establece el valor del atributo nombre
    }

// Métodos getter y setter para el atributo id_localizacion
    public int getId_localizacion() {
        return id_localizacion; // Devuelve el valor del atributo id_localizacion
    }

    public void setId_localizacion(int id_localizacion) {
        this.id_localizacion = id_localizacion; // Establece el valor del atributo id_localizacion
    }

// Métodos getter y setter para el atributo id_manager
    public int getId_manager() {
        return id_manager; // Devuelve el valor del atributo id_manager
    }

    public void setId_manager(int id_manager) {
        this.id_manager = id_manager; // Establece el valor del atributo id_manager
    }
}
