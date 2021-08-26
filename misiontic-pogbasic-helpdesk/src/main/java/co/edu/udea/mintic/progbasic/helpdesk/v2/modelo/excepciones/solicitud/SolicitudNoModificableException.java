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
public class SolicitudNoModificableException extends Exception {

    public SolicitudNoModificableException(String mensaje) {
        super(mensaje);
    }
    
}
