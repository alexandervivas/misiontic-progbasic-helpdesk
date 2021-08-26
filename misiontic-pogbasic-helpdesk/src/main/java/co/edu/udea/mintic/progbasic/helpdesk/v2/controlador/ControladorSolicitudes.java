/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.mintic.progbasic.helpdesk.v2.controlador;

import co.edu.udea.mintic.progbasic.helpdesk.v2.modelo.ModeloSolicitudes;
import co.edu.udea.mintic.progbasic.helpdesk.v2.modelo.dominio.Usuario;
import co.edu.udea.mintic.progbasic.helpdesk.v2.modelo.dominio.eventos.EventoModeloSolicitud;
import co.edu.udea.mintic.progbasic.helpdesk.v2.modelo.excepciones.persistencia.EntidadNoCreadaException;
import co.edu.udea.mintic.progbasic.helpdesk.v2.util.Suscriptor;
import co.edu.udea.mintic.progbasic.helpdesk.v2.vista.VistaCrearSolicitud;
import co.edu.udea.mintic.progbasic.helpdesk.v2.vista.VistaListadoSolicitudes;
import co.edu.udea.mintic.progbasic.helpdesk.v2.vista.eventos.EventoVistaSolicitudes;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alexandervivas
 */
public class ControladorSolicitudes implements Suscriptor {
    
    // modelo
    private ModeloSolicitudes modelo;
    
    // vistaListadoSolicitudes
    private VistaListadoSolicitudes vistaListadoSolicitudes;
    private VistaCrearSolicitud vistaCrearSolicitud;
    
    public ControladorSolicitudes(Usuario usuario) {
        modelo = new ModeloSolicitudes();
        vistaListadoSolicitudes = new VistaListadoSolicitudes();
        vistaCrearSolicitud = new VistaCrearSolicitud(usuario);
        
        // this, se refiere al controlador, al objeto actual (instancia de ControladorSolicitudes)
        modelo.agregarSuscriptor(this);
        vistaListadoSolicitudes.agregarSuscriptor(this);
        vistaCrearSolicitud.agregarSuscriptor(this);
    }

    public void iniciar() {
        vistaListadoSolicitudes.setSolicitudes(modelo.consultarSolicitudes());
        vistaListadoSolicitudes.setVisible(true);
    }

    @Override
    public void recibirEvento(Object evento) {
        // instanceof permite validar si un objeto es una instancia de una clase específica
        if(evento instanceof EventoModeloSolicitud) {
            procesarEvento((EventoModeloSolicitud) evento);
        }
        
        if(evento instanceof EventoVistaSolicitudes) {
            procesarEvento((EventoVistaSolicitudes) evento);
        }
    }
    
    private void procesarEvento(EventoModeloSolicitud eventoModeloSolicitud) {
        switch(eventoModeloSolicitud.getTipo()) {
            case SOLICITUD_CREADA: {
                System.out.println("Solicitud creada: " + eventoModeloSolicitud.getSolicitud());
                vistaListadoSolicitudes.agregarSolicitud(eventoModeloSolicitud.getSolicitud());
            } break;
            case SOLICITUD_ACTUALIZADA: {
                System.out.println("Solicitud actualizada: " + eventoModeloSolicitud.getSolicitud());
                vistaListadoSolicitudes.actualizarSolicitud(eventoModeloSolicitud.getSolicitud());
            } break;
            case SOLICITUD_ELIMINADA: {
                System.out.println("Solicitud creada: " + eventoModeloSolicitud.getSolicitud());
                vistaListadoSolicitudes.eliminarSolicitud(eventoModeloSolicitud.getSolicitud());
            } break;
        }
    }
    
    private void procesarEvento(EventoVistaSolicitudes eventoVistaSolicitudes) {
        
        switch(eventoVistaSolicitudes.getTipo()) {
            case CREAR: {
                if(eventoVistaSolicitudes.getSolicitud() == null) {
                    vistaCrearSolicitud.setVisible(true);
                } else {
                    try {
                        modelo.crearSolicitud(eventoVistaSolicitudes.getSolicitud());
                    } catch (EntidadNoCreadaException ex) {
                        ex.printStackTrace();
                    }
                }
                
            } break;
        }
        
    }
    
}
