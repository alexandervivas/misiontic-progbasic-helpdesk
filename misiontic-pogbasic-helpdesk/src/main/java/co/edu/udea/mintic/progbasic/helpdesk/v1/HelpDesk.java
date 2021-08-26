/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.mintic.progbasic.helpdesk.v1;

import co.edu.udea.mintic.progbasic.helpdesk.v1.dominio.Usuario;
import co.edu.udea.mintic.progbasic.helpdesk.v1.persistencia.Persistencia;
import co.edu.udea.mintic.progbasic.helpdesk.v1.persistencia.memoria.PersistenciaMemoriaSolicitudes;
import co.edu.udea.mintic.progbasic.helpdesk.v1.persistencia.mysql.PersistenciaMySQLSolicitudes;
import co.edu.udea.mintic.progbasic.helpdesk.v1.ui.CrearSolicitudForm;
import co.edu.udea.mintic.progbasic.helpdesk.v1.ui.SolicitudesForm;
import co.edu.udea.mintic.progbasic.helpdesk.v1.ui.VerSolicitudForm;
import co.edu.udea.mintic.progbasic.helpdesk.v1.ui.eventos.EventoSolicitudesForm;
import co.edu.udea.mintic.progbasic.helpdesk.v1.util.Suscriptor;

/**
 *
 * @author alexandervivas
 */
public class HelpDesk implements Suscriptor<EventoSolicitudesForm> {
    
    private final SolicitudesForm formularioSolicitudes;
    private final CrearSolicitudForm formularioCrearSolicitud;
    private final VerSolicitudForm formularioVerSolicitud;
        
    public HelpDesk(Usuario usuario) {
        
        Persistencia persistencia = new PersistenciaMySQLSolicitudes();
        
        formularioSolicitudes = new SolicitudesForm(persistencia);
        formularioCrearSolicitud = new CrearSolicitudForm(usuario, persistencia);
        formularioVerSolicitud = new VerSolicitudForm(usuario, persistencia);
        
        // se suscribe el fomulario principal a los eventos de los formularios especificos
        formularioCrearSolicitud.agregarSuscriptor(formularioSolicitudes);
        formularioVerSolicitud.agregarSuscriptor(formularioSolicitudes);
        
        // se suscribe esta clase a los eventos del formulario principal
        formularioSolicitudes.agregarSuscriptor(this);
        
        // se muestra el formulario principal
        formularioSolicitudes.setVisible(true);
    }

    @Override
    public void recibirEvento(EventoSolicitudesForm evento) {
        switch(evento.getTipo()) {
            case CREAR: {
                formularioCrearSolicitud.setVisible(true);
            } break;
            case VER: {
                formularioVerSolicitud.setSolicitud(evento.getSolicitud());
                formularioVerSolicitud.setVisible(true);
            } break;
        }
    }
}
