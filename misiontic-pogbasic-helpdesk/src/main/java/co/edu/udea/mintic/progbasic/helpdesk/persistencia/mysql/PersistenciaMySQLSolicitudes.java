/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.mintic.progbasic.helpdesk.persistencia.mysql;

import co.edu.udea.mintic.progbasic.helpdesk.dominio.Solicitud;
import co.edu.udea.mintic.progbasic.helpdesk.excepciones.persistencia.EntidadNoCreadaException;
import co.edu.udea.mintic.progbasic.helpdesk.excepciones.persistencia.EntidadNoEliminadaException;
import co.edu.udea.mintic.progbasic.helpdesk.excepciones.persistencia.EntidadNoEncontradaException;
import co.edu.udea.mintic.progbasic.helpdesk.persistencia.Persistencia;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author alexandervivas
 */
public class PersistenciaMySQLSolicitudes implements Persistencia<Long, Solicitud> {
    
    // usualmente se opta por usar un pool de conexiones (C3P0)
    private Connection conexion;
    
    public final static String ENTIDAD_NO_CREADA = "La entidad no se pudo crear en la base de datos";
    
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
            ex.printStackTrace();
        }
    }
    

    @Override
    public void crear(Solicitud solicitud) throws EntidadNoCreadaException {
        try {
            
            Statement statement = conexion.createStatement();
            
            int registros = statement.executeUpdate("INSERT INTO solicitudes (titulo, descripcion, usuario_creador) VALUES ('" + 
                    solicitud.getTitulo() + "', '" + 
                    solicitud.getDescripcion() + "', '" + 
                    solicitud.getUsuarioCreador().getId() + "');");
            
            conexion.close();
            
            if(registros == 0) {
                throw new EntidadNoCreadaException(ENTIDAD_NO_CREADA);
            }
            
        } catch (SQLException ex) {
            throw new EntidadNoCreadaException(ENTIDAD_NO_CREADA, ex);
        }
    }

    @Override
    public Solicitud leer(Long id) throws EntidadNoEncontradaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long actualizar(Solicitud entidad) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void borrar(Long id) throws EntidadNoEliminadaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Solicitud> listar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
