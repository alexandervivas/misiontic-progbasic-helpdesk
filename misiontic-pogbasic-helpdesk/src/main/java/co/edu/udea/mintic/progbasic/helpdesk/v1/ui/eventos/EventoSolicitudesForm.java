/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.mintic.progbasic.helpdesk.v1.ui.eventos;

import co.edu.udea.mintic.progbasic.helpdesk.v1.dominio.Solicitud;

/**
 *
 * @author alexandervivas
 */
public class EventoSolicitudesForm {
    
    private TipoEventoSolicitudesForm tipo;
    private Solicitud solicitud;
    
    public EventoSolicitudesForm(TipoEventoSolicitudesForm tipo) {
        this.tipo = tipo;
    }
    
    public EventoSolicitudesForm(TipoEventoSolicitudesForm tipo, Solicitud solicitud) {
        this.tipo = tipo;
        this.solicitud = solicitud;
    }

    /**
     * @return the tipo
     */
    public TipoEventoSolicitudesForm getTipo() {
        return tipo;
    }

    /**
     * @return the solicitud
     */
    public Solicitud getSolicitud() {
        return solicitud;
    }
    
}
