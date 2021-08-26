/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.mintic.progbasic.helpdesk.v2.modelo.dominio.eventos;

import co.edu.udea.mintic.progbasic.helpdesk.v2.modelo.dominio.Solicitud;

/**
 *
 * @author alexandervivas
 */
public class EventoModeloSolicitud {
    
    private EventoSolicitudTipo tipo;
    private Solicitud solicitud;
    
    public EventoModeloSolicitud(EventoSolicitudTipo tipo, Solicitud solicitud) {
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
