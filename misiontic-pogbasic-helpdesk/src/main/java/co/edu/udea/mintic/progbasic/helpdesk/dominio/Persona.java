/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.mintic.progbasic.helpdesk.dominio;

/**
 *
 * @author alexandervivas
 */
public abstract class Persona {
    
    private long id;
    private String cedula;
    private String nombre;
    private String correo;
    
    public Persona(long id, String cedula, String nombre, String correo) {
        this.id = id;
        this.cedula = cedula;
        this.nombre = nombre;
        this.correo = correo;
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @return the cedula
     */
    public String getCedula() {
        return cedula;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }
    
}
