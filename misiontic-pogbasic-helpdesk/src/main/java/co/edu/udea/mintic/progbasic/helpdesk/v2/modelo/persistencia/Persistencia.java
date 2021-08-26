/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.mintic.progbasic.helpdesk.v2.modelo.persistencia;

import co.edu.udea.mintic.progbasic.helpdesk.v2.modelo.excepciones.persistencia.EntidadNoActualizadaException;
import co.edu.udea.mintic.progbasic.helpdesk.v2.modelo.excepciones.persistencia.EntidadNoCreadaException;
import co.edu.udea.mintic.progbasic.helpdesk.v2.modelo.excepciones.persistencia.EntidadNoEliminadaException;
import co.edu.udea.mintic.progbasic.helpdesk.v2.modelo.excepciones.persistencia.EntidadNoEncontradaException;
import java.util.List;

/**
 *
 * @author alexandervivas
 */
public interface Persistencia<I, E> {
    
    public I crear(E entidad) throws EntidadNoCreadaException;
    public E leer(I id) throws EntidadNoEncontradaException;
    public void actualizar(E entidad) throws EntidadNoActualizadaException;
    public void borrar(I id) throws EntidadNoEliminadaException;
    public List<E> listar();
    
}
