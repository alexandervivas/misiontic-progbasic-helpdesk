/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.mintic.progbasic.helpdesk;

import co.edu.udea.mintic.progbasic.helpdesk.dominio.Usuario;
import co.edu.udea.mintic.progbasic.helpdesk.persistencia.Persistencia;
import co.edu.udea.mintic.progbasic.helpdesk.persistencia.memoria.PersistenciaMemoriaSolicitudes;
import co.edu.udea.mintic.progbasic.helpdesk.persistencia.mysql.PersistenciaMySQLSolicitudes;
import co.edu.udea.mintic.progbasic.helpdesk.ui.CrearSolicitudForm;
import co.edu.udea.mintic.progbasic.helpdesk.ui.SolicitudesForm;
import co.edu.udea.mintic.progbasic.helpdesk.ui.VerSolicitudForm;
import co.edu.udea.mintic.progbasic.helpdesk.ui.eventos.EventoSolicitudesForm;
import co.edu.udea.mintic.progbasic.helpdesk.util.Suscriptor;

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
        
        formularioSolicitudes = new SolicitudesForm();
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
