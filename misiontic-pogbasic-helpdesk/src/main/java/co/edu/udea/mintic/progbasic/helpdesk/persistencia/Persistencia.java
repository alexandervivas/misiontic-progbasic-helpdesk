/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.mintic.progbasic.helpdesk.persistencia;

import co.edu.udea.mintic.progbasic.helpdesk.excepciones.persistencia.EntidadNoCreadaException;
import co.edu.udea.mintic.progbasic.helpdesk.excepciones.persistencia.EntidadNoEliminadaException;
import co.edu.udea.mintic.progbasic.helpdesk.excepciones.persistencia.EntidadNoEncontradaException;
import java.util.List;

/**
 *
 * @author alexandervivas
 */
public interface Persistencia<I, E> {
    
    public void crear(E entidad) throws EntidadNoCreadaException;
    public E leer(I id) throws EntidadNoEncontradaException;
    public long actualizar(E entidad);
    public void borrar(I id) throws EntidadNoEliminadaException;
    public List<E> listar();
    
}
