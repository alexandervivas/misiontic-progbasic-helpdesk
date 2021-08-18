/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.mintic.progbasic.helpdesk.dominio;

import co.edu.udea.mintic.progbasic.helpdesk.excepciones.PrivilegiosInsuficientesException;
import co.edu.udea.mintic.progbasic.helpdesk.excepciones.SolicitudNoModificableException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.fail;
import org.junit.Test;

/**
 *
 * @author alexandervivas
 */
public class SolicitudTest {
    
    @Test
    public void asignaEmpleado() {
        
        Empleado empleado = new Empleado("CC1", "Pepito", "pepito@mail.com", RolEmpleado.AUXILIAR);
        Usuario usuarioCreador = new Usuario("CC2", "Juanito", "juanito@mail.com");
        
        Solicitud solicitud = new Solicitud(usuarioCreador, "Titulo", "me duele el pecho");
        
        assertEquals("La solicitud se encuentra en estado CREADA", solicitud.getEstado(), EstadoSolicitud.CREADA);
        
        try {
            solicitud.setEmpleadoAsignado(empleado);
            assertEquals("La solicitud se encuentra en estado ASIGNADA", solicitud.getEstado(), EstadoSolicitud.ASIGNADA);
        } catch (SolicitudNoModificableException ex) {
            fail("La solicitud no se pudo modificar");
        }
        
    }
    
    @Test
    public void noPuedeAgregarActividadEmpleado() {
        
        Empleado empleado = new Empleado("CC1", "Pepito", "pepito@mail.com", RolEmpleado.AUXILIAR);
        Usuario usuarioCreador = new Usuario("CC2", "Juanito", "juanito@mail.com");
        
        Solicitud solicitud = new Solicitud(usuarioCreador, "Titulo", "me duele el pecho");
        
        Actividad actividad = new Actividad("blablabla", empleado);
        
        assertThrows("Debe lanzar excepcion si alguien no autorizado trata de agregar una actividad", 
                PrivilegiosInsuficientesException.class, 
                () -> {
                    solicitud.agregarActividad(actividad);
                });
        
        try {
            solicitud.setEmpleadoAsignado(empleado);
        } catch (SolicitudNoModificableException ex) {
            fail("La solicitud no se pudo modificar");
        }
        
        Empleado empleado2 = new Empleado("CC2", "Pablito", "pablito@mail.com", RolEmpleado.AUXILIAR);
        Actividad actividad2 = new Actividad("blablabla", empleado2);
        
        assertThrows("Debe lanzar excepcion si alguien no autorizado trata de agregar una actividad", 
                PrivilegiosInsuficientesException.class, 
                () -> {
                    solicitud.agregarActividad(actividad2);
                });
        
        Empleado administrador = new Empleado("CC3", "Jesus", "jesus@mail.com", RolEmpleado.ADMINISTRADOR);
        Actividad actividad3 = new Actividad("blablabla", administrador);
        
        try {
            solicitud.agregarActividad(actividad3);
        } catch (SolicitudNoModificableException ex) {
            fail("La solicitud no se encontraba FINALIZADA");
        } catch (PrivilegiosInsuficientesException ex) {
            fail("El administrador debe poder agregar actividades a cualquier solicitud");
        }
        
    }
    
}
