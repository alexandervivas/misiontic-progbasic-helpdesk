/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.mintic.progbasic.helpdesk.v2.modelo.excepciones.persistencia;

/**
 *
 * @author alexandervivas
 */
public class EntidadNoActualizadaException extends Exception {
    
    public EntidadNoActualizadaException(String mensaje) {
        super(mensaje);
    }
    
    public EntidadNoActualizadaException(String mensaje, Exception excepcion) {
        super(mensaje, excepcion);
    }
    
}
