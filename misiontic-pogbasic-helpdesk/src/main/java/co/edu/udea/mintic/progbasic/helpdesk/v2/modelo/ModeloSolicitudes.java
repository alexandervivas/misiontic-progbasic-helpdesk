/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.mintic.progbasic.helpdesk.v2.modelo;

import co.edu.udea.mintic.progbasic.helpdesk.v2.modelo.dominio.Solicitud;
import co.edu.udea.mintic.progbasic.helpdesk.v2.modelo.dominio.eventos.EventoSolicitud;
import co.edu.udea.mintic.progbasic.helpdesk.v2.modelo.dominio.eventos.EventoSolicitudTipo;
import co.edu.udea.mintic.progbasic.helpdesk.v2.modelo.excepciones.persistencia.EntidadNoCreadaException;
import co.edu.udea.mintic.progbasic.helpdesk.v2.modelo.persistencia.Persistencia;
import co.edu.udea.mintic.progbasic.helpdesk.v2.modelo.persistencia.mysql.PersistenciaMySQLSolicitudes;
import co.edu.udea.mintic.progbasic.helpdesk.v2.util.Publicador;
import co.edu.udea.mintic.progbasic.helpdesk.v2.util.Suscriptor;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alexandervivas
 */
public class ModeloSolicitudes implements Publicador<EventoSolicitud>{
    
    private Persistencia<Long, Solicitud> persistencia;
    private List<Suscriptor<EventoSolicitud>> suscriptores;
    
    public ModeloSolicitudes() {
        persistencia = new PersistenciaMySQLSolicitudes();
        suscriptores = new ArrayList<>();
    }
    
    public List<Solicitud> consultarSolicitudes() {
        return persistencia.listar();
    }
    
    public void crearSolicitud(Solicitud solicitud) throws EntidadNoCreadaException {
        Long idSolicitud = persistencia.crear(solicitud);
        solicitud.setId(idSolicitud);
        notificarEvento(new EventoSolicitud(EventoSolicitudTipo.SOLICITUD_CREADA, solicitud));
    }

    @Override
    public void agregarSuscriptor(Suscriptor<EventoSolicitud> suscriptor) {
        suscriptores.add(suscriptor);
    }

    @Override
    public void notificarEvento(EventoSolicitud evento) {
        suscriptores.forEach((suscriptor) -> suscriptor.recibirEvento(evento));
    }
    
}
