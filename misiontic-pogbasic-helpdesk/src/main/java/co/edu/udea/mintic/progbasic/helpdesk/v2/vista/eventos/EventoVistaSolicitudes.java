/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.mintic.progbasic.helpdesk.v2.vista.eventos;

import co.edu.udea.mintic.progbasic.helpdesk.v2.modelo.dominio.Solicitud;

/**
 *
 * @author alexandervivas
 */
public class EventoVistaSolicitudes {
    
    private TipoEventoVistaSolicitudes tipo;
    private Solicitud solicitud;
    
    public EventoVistaSolicitudes(TipoEventoVistaSolicitudes tipo) {
        this.tipo = tipo;
    }
    
    public EventoVistaSolicitudes(TipoEventoVistaSolicitudes tipo, Solicitud solicitud) {
        this.tipo = tipo;
        this.solicitud = solicitud;
    }

    /**
     * @return the tipo
     */
    public TipoEventoVistaSolicitudes getTipo() {
        return tipo;
    }

    /**
     * @return the solicitud
     */
    public Solicitud getSolicitud() {
        return solicitud;
    }
    
}
