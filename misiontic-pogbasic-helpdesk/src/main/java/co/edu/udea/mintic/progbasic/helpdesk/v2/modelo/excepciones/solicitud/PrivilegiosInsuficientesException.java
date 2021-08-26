/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.mintic.progbasic.helpdesk.v2.modelo.excepciones.solicitud;

/**
 *
 * @author alexandervivas
 */
public class PrivilegiosInsuficientesException extends Exception {

    public PrivilegiosInsuficientesException(String mensaje) {
        super(mensaje);
    }
    
}
