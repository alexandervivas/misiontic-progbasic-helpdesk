/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.mintic.progbasic.helpdesk.dominio;

import co.edu.udea.mintic.progbasic.helpdesk.excepciones.PrivilegiosInsuficientesException;
import co.edu.udea.mintic.progbasic.helpdesk.excepciones.SolicitudNoModificableException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alexandervivas
 */
public class Solicitud {
    
    private long id;
    private EstadoSolicitud estado;
    private Usuario usuarioCreador;
    private Empleado empleadoAsignado;
    private String titulo;
    private String descripcion;
    private List<Actividad> actividades;
    
    
    private final static String MENSAJE_PRIVILEGIOS_INSUFICIENTES = "El empleado no tiene privilegios suficientes para realizar esta operación";
    
    public Solicitud(Usuario usuarioCreador, String titulo, String descripcion) {
        estado = EstadoSolicitud.CREADA;
        actividades = new ArrayList<>();
        this.usuarioCreador = usuarioCreador;
        this.titulo = titulo;
        this.descripcion = descripcion;
    }
    
    public Solicitud(long id, EstadoSolicitud estado, Usuario usuarioCreador, 
            Empleado empleadoAsignado, String titulo, String descripcion, 
            List<Actividad> actividades) {
        this.id = id;
        this.estado = estado;
        this.usuarioCreador = usuarioCreador;
        this.empleadoAsignado = empleadoAsignado;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.actividades = actividades;
    }
    
    public void setEmpleadoAsignado(Empleado empleadoAsignado) throws SolicitudNoModificableException {
        if(estado == EstadoSolicitud.FINALIZADA) {
            throw new SolicitudNoModificableException("La solicitud " + id + " no se puede reasignar puesto que ya se encuentra finalizada.");
        }
        estado = EstadoSolicitud.ASIGNADA;
        this.empleadoAsignado = empleadoAsignado;
    }
    
    public void agregarActividad(Actividad actividad) throws SolicitudNoModificableException, PrivilegiosInsuficientesException {
        if(estado == EstadoSolicitud.FINALIZADA) {
            throw new SolicitudNoModificableException("La solicitud " + id + " ya se encuentra finalizada, por lo tanto no se pueden ejecutar más actividades sobre ella.");
        }
        if(!actividad.getEncargado().equals(empleadoAsignado) 
                && !actividad.getEncargado().getRol().equals(RolEmpleado.ADMINISTRADOR)) {
            throw new PrivilegiosInsuficientesException(MENSAJE_PRIVILEGIOS_INSUFICIENTES);
        }
        actividades.add(actividad);
    }
    
    public void finalizarSolicitud() throws SolicitudNoModificableException {
        if(estado == EstadoSolicitud.FINALIZADA) {
            throw new SolicitudNoModificableException("La solicitud " + id + " ya se encuentra finalizada.");
        }
        estado = EstadoSolicitud.FINALIZADA;
    }
    
    public EstadoSolicitud getEstado() {
        return estado;
    }
    
}
