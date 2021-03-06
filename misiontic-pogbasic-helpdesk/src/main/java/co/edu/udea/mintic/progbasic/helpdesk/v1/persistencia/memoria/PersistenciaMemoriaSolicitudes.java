/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.mintic.progbasic.helpdesk.v1.persistencia.memoria;

import co.edu.udea.mintic.progbasic.helpdesk.v1.dominio.Solicitud;
import co.edu.udea.mintic.progbasic.helpdesk.v1.excepciones.persistencia.EntidadNoActualizadaException;
import co.edu.udea.mintic.progbasic.helpdesk.v1.excepciones.persistencia.EntidadNoCreadaException;
import co.edu.udea.mintic.progbasic.helpdesk.v1.excepciones.persistencia.EntidadNoEliminadaException;
import co.edu.udea.mintic.progbasic.helpdesk.v1.excepciones.persistencia.EntidadNoEncontradaException;
import co.edu.udea.mintic.progbasic.helpdesk.v1.persistencia.Persistencia;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alexandervivas
 */
public class PersistenciaMemoriaSolicitudes implements Persistencia<Long, Solicitud> {
    
    private final List<Solicitud> db = new ArrayList<>();
    
    public final static String ENTIDAD_NO_ENCONTRADA = "La entidad no existe en la base de datos";
    public final static String ENTIDAD_NO_ELIMINADA = "La entidad no se pudo borrarr de la base de datos";

    @Override
    public Long crear(Solicitud entidad) throws EntidadNoCreadaException {
        long id = 0;
        
        if(db.size() > 0) {
            id = db.get(db.size() - 1).getId() + 1;
        }
        
        entidad.setId(id);
        
        db.add(entidad);
        
        return id;
    }

    @Override
    public Solicitud leer(Long id) throws EntidadNoEncontradaException {
        for(Solicitud solicitud : db) {
            if(solicitud.getId() == id) {
                return solicitud;
            }
        }
        throw new EntidadNoEncontradaException(ENTIDAD_NO_ENCONTRADA);
    }

    @Override
    public void actualizar(Solicitud entidad) throws EntidadNoActualizadaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void borrar(Long id) throws EntidadNoEliminadaException {
        for(int i = 0; i < db.size(); i++) {
            if(db.get(i).getId() == id) {
                try {
                    db.remove(i);
                } catch (Exception exception) {
                    throw new EntidadNoEliminadaException(ENTIDAD_NO_ELIMINADA, exception);
                }
                return;
            }
        }
    }

    @Override
    public List<Solicitud> listar() {
        return db;
    }
    
}
