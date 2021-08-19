/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.mintic.progbasic.helpdesk.dominio;

import co.edu.udea.mintic.progbasic.helpdesk.excepciones.solicitud.PrivilegiosInsuficientesException;
import co.edu.udea.mintic.progbasic.helpdesk.excepciones.solicitud.SolicitudNoModificableException;
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
    private final static String MENSAJE_SOLICITUD_NO_REASIGNABLE = "La solicitud no se puede reasignar puesto que ya se encuentra finalizada.";
    private final static String MENSAJE_SOLICITUD_FINALIZADA = "La solicitud no se puede modificar puesto que ya se encuentra finalizada.";
    
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
            throw new SolicitudNoModificableException(MENSAJE_SOLICITUD_NO_REASIGNABLE);
        }
        estado = EstadoSolicitud.ASIGNADA;
        this.empleadoAsignado = empleadoAsignado;
    }
    
    public void agregarActividad(Actividad actividad) throws SolicitudNoModificableException, PrivilegiosInsuficientesException {
        if(estado == EstadoSolicitud.FINALIZADA) {
            throw new SolicitudNoModificableException(MENSAJE_SOLICITUD_FINALIZADA);
        }
        if(!actividad.getEncargado().equals(empleadoAsignado) 
                && !actividad.getEncargado().getRol().equals(RolEmpleado.ADMINISTRADOR)) {
            throw new PrivilegiosInsuficientesException(MENSAJE_PRIVILEGIOS_INSUFICIENTES);
        }
        getActividades().add(actividad);
    }
    
    public void finalizarSolicitud() throws SolicitudNoModificableException {
        if(estado == EstadoSolicitud.FINALIZADA) {
            throw new SolicitudNoModificableException(MENSAJE_SOLICITUD_FINALIZADA);
        }
        estado = EstadoSolicitud.FINALIZADA;
    }

    /**
     * @return the estado
     */
    public EstadoSolicitud getEstado() {
        return estado;
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @return the usuarioCreador
     */
    public Usuario getUsuarioCreador() {
        return usuarioCreador;
    }

    /**
     * @return the empleadoAsignado
     */
    public Empleado getEmpleadoAsignado() {
        return empleadoAsignado;
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @return the actividades
     */
    public List<Actividad> getActividades() {
        return actividades;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }
    
}
