/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.mintic.progbasic.helpdesk.v1.dominio;

/**
 *
 * @author alexandervivas
 */
public class Usuario extends Persona {
    
    public Usuario(long id, String cedula, String nombre, String correo) {
        super(id, cedula, nombre, correo);
    }
    
}
