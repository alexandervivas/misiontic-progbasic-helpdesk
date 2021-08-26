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
public enum EstadoSolicitud {
    
    CREADA, ASIGNADA, FINALIZADA;
    
    public static EstadoSolicitud fromString(String estadoSolicitud) {
        switch(estadoSolicitud) {
            case "CREADA" : return CREADA;
            case "ASIGNADA" : return ASIGNADA;
            case "FINALIZADA" : return FINALIZADA;
        }
        return null;
    }
    
}
