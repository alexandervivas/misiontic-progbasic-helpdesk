/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.mintic.progbasic.helpdesk.dominio;

/**
 *
 * @author alexandervivas
 */
public class Solicitud {
    
    private long id;
    private String estado;
    private Usuario usuarioCreador;
    private Empleado empleadoAsignado;
    private String titulo;
    private String descripcion;
    
    public Solicitud(Usuario usuarioCreador, String titulo, String descripcion) {
        estado = "CREADA";
        this.usuarioCreador = usuarioCreador;
        this.titulo = titulo;
        this.descripcion = descripcion;
    }
    
    public void setEmpleadoAsignado(Empleado empleadoAsignado) {
        this.empleadoAsignado = empleadoAsignado;
    }
    
}
