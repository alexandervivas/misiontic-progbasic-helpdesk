/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.mintic.progbasic.helpdesk.dominio;

import org.joda.time.DateTime;

/**
 *
 * @author alexandervivas
 */
public class Actividad {
    
    private DateTime fecha;
    private String descripcion;
    private Empleado encargado;
    
    public Actividad(String descripcion, Empleado encargado) {
        fecha = DateTime.now();
        this.descripcion = descripcion;
        this.encargado = encargado;
    }
    
    public Empleado getEncargado() {
        return encargado;
    }
    
}
