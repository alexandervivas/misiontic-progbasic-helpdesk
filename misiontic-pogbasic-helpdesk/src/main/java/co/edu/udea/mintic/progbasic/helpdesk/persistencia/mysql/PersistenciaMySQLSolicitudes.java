/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.mintic.progbasic.helpdesk.persistencia.mysql;

import co.edu.udea.mintic.progbasic.helpdesk.dominio.EstadoSolicitud;
import co.edu.udea.mintic.progbasic.helpdesk.dominio.Solicitud;
import co.edu.udea.mintic.progbasic.helpdesk.excepciones.persistencia.EntidadNoActualizadaException;
import co.edu.udea.mintic.progbasic.helpdesk.excepciones.persistencia.EntidadNoCreadaException;
import co.edu.udea.mintic.progbasic.helpdesk.excepciones.persistencia.EntidadNoEliminadaException;
import co.edu.udea.mintic.progbasic.helpdesk.excepciones.persistencia.EntidadNoEncontradaException;
import co.edu.udea.mintic.progbasic.helpdesk.persistencia.Persistencia;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alexandervivas
 */
public class PersistenciaMySQLSolicitudes implements Persistencia<Long, Solicitud> {
    
    // usualmente se opta por usar un pool de conexiones (C3P0)
    private Connection conexion;
    
    public final static String ENTIDAD_NO_CREADA = "La entidad no se pudo crear en la base de datos";
    public final static String ENTIDAD_NO_ELIMINADA = "La entidad no se pudo borrar de la base de datos";
    public final static String ENTIDAD_NO_ACTUALIZADA = "La entidad no se pudo actualizar en la base de datos";
    public final static String ENTIDAD_NO_ENCONTRADA = "La entidad no existe en la base de datos";
    
    public PersistenciaMySQLSolicitudes() {
        String host = "sql11.freemysqlhosting.net";
        int puerto = 3306;
        String nombreBD = "sql11431777";
        String usuario = "sql11431777";
        String password = "r8hHCrMlnJ";
        try {
            // create a mysql database connection
            String myDriver = "com.mysql.cj.jdbc.Driver";
            Class.forName(myDriver);
            conexion = DriverManager.getConnection("jdbc:mysql://" + host + ":" + puerto + "/" + nombreBD, usuario, password);
        } catch (SQLException | ClassNotFoundException ex) {
        }
    }
    

    @Override
    public Long crear(Solicitud solicitud) throws EntidadNoCreadaException {
        long idGenerado = 0;
        
        try {
            
            int registros;
            try (Statement statement = conexion.createStatement()) {
                String query = "INSERT INTO solicitudes (titulo, descripcion, estado, usuario_creador) "
                        + "VALUES ("
                        + "'" + solicitud.getTitulo() + "', "
                        + "'" + solicitud.getDescripcion() + "', "
                        + "'" + solicitud.getEstado().toString() + "', "
                        + "'" + solicitud.getUsuarioCreador().getId() + "'"
                        + ");";
                registros = statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
                try (ResultSet resultSet = statement.getGeneratedKeys()) {
                    if (resultSet.next()){
                        idGenerado = resultSet.getLong(1);
                    }
                }
            }
            
            if(registros == 0) {
                throw new EntidadNoCreadaException(ENTIDAD_NO_CREADA);
            }
            
        } catch (SQLException ex) {
            throw new EntidadNoCreadaException(ENTIDAD_NO_CREADA, ex);
        }
        
        return idGenerado;
    }

    @Override
    public Solicitud leer(Long id) throws EntidadNoEncontradaException {
        Solicitud solicitud = null;
        try {
            
            try (Statement statement = conexion.createStatement()) {
                String query = "SELECT "
                        + "titulo, "
                        + "descripcion, "
                        + "estado "
                        + "FROM solicitudes "
                        + "WHERE id_solicitud = " + id + ";";
                
                ResultSet resultSet = statement.executeQuery(query);
                
                while(resultSet.next()) {
                    solicitud = new Solicitud(
                            id,
                            EstadoSolicitud.fromString(resultSet.getString("estado")),
                            null,
                            null,
                            resultSet.getString("titulo"),
                            resultSet.getString("descripcion"),
                            null);
                    
                    
                }
            }
            
        } catch (SQLException ex) {
            throw new EntidadNoEncontradaException(ENTIDAD_NO_ENCONTRADA, ex);
        }
        
        return solicitud;
    }

    @Override
    public void actualizar(Solicitud solicitud) throws EntidadNoActualizadaException {
        try {
            
            int registros;
            try (Statement statement = conexion.createStatement()) {
                String query = "UPDATE solicitudes set "
                        + "titulo = '" + solicitud.getTitulo() + "', "
                        + "descripcion = '" + solicitud.getDescripcion() + "' "
                        + "WHERE id_solicitud = " + solicitud.getId() + ";";
                registros = statement.executeUpdate(query);
            }
            
            if(registros == 0) {
                throw new EntidadNoActualizadaException(ENTIDAD_NO_ACTUALIZADA);
            }
            
        } catch (SQLException ex) {
            throw new EntidadNoActualizadaException(ENTIDAD_NO_ACTUALIZADA, ex);
        }
    }

    @Override
    public void borrar(Long id) throws EntidadNoEliminadaException {
        try {
            
            int registros;
            try (Statement statement = conexion.createStatement()) {
                String query = "DELETE FROM solicitudes "
                        + "WHERE id_solicitud = " + id + ";";
                registros = statement.executeUpdate(query);
            }
            
            if(registros == 0) {
                throw new EntidadNoEliminadaException(ENTIDAD_NO_ELIMINADA);
            }
            
        } catch (SQLException ex) {
            throw new EntidadNoEliminadaException(ENTIDAD_NO_ELIMINADA, ex);
        }
    }

    @Override
    public List<Solicitud> listar() {
        List<Solicitud> solicitudes = new ArrayList<>();
        try {
            
            try (Statement statement = conexion.createStatement()) {
                String query = "SELECT "
                        + "id_solicitud, "
                        + "titulo, "
                        + "descripcion, "
                        + "estado "
                        + "FROM solicitudes;";
                
                try (ResultSet resultSet = statement.executeQuery(query)) {
                    while(resultSet.next()) {
                        Solicitud solicitud = new Solicitud(
                                resultSet.getLong("id_solicitud"),
                                EstadoSolicitud.fromString(resultSet.getString("estado")),
                                null,
                                null,
                                resultSet.getString("titulo"),
                                resultSet.getString("descripcion"),
                                null);
                        
                        solicitudes.add(solicitud);
                    }
                }
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return solicitudes;
    }
    
}
