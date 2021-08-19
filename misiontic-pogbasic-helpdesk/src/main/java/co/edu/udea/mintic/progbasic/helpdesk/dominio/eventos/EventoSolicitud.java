/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.mintic.progbasic.helpdesk.dominio.eventos;

import co.edu.udea.mintic.progbasic.helpdesk.dominio.Solicitud;

/**
 *
 * @author alexandervivas
 */
public class EventoSolicitud {
    
    private EventoSolicitudTipo tipo;
    private Solicitud solicitud;
    
    public EventoSolicitud(EventoSolicitudTipo tipo, Solicitud solicitud) {
        this.tipo = tipo;
        this.solicitud = solicitud;
    }

    /**
     * @return the tipo
     */
    public EventoSolicitudTipo getTipo() {
        return tipo;
    }

    /**
     * @return the solicitud
     */
    public Solicitud getSolicitud() {
        return solicitud;
    }
    
}
