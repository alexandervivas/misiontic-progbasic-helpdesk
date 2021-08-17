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
public class Empleado extends Persona {
    
    private String rol;
    
    public Empleado(String id, String nombre, String correo) {
        super(id, nombre, correo);
    }
    
    public Empleado(String id, String nombre, String correo, String rol) {
        super(id, nombre, correo);
        this.rol = rol;
    }
    
    public void setRol(String rol) {
        this.rol = rol;
    }
    
}
