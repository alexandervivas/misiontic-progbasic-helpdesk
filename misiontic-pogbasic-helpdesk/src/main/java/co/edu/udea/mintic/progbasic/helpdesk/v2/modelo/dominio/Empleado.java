/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.mintic.progbasic.helpdesk.v2.modelo.dominio;

/**
 *
 * @author alexandervivas
 */
public class Empleado extends Persona {
    
    private RolEmpleado rol;
    
    public Empleado(long id, String cedula, String nombre, String correo) {
        super(id, cedula, nombre, correo);
    }
    
    public Empleado(long id, String cedula, String nombre, String correo, RolEmpleado rol) {
        super(id, cedula, nombre, correo);
        this.rol = rol;
    }
    
    public RolEmpleado getRol() {
        return rol;
    }
    
    public void setRol(RolEmpleado rol) {
        this.rol = rol;
    }
    
}
