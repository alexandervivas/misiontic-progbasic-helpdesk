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
    
    private String id;
    private String nombre;
    private String correo;
    
    public Persona(String id, String nombre, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
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
