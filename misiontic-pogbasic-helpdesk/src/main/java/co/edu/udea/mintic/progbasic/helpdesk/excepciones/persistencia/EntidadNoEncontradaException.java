/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.mintic.progbasic.helpdesk.excepciones.persistencia;

/**
 *
 * @author alexandervivas
 */
public class EntidadNoEncontradaException extends Exception {
    
    public EntidadNoEncontradaException(String mensaje) {
        super(mensaje);
    }
    
    public EntidadNoEncontradaException(String mensaje, Exception excepcion) {
        super(mensaje, excepcion);
    }
    
}
