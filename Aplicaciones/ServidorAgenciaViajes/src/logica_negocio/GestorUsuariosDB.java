package logica_negocio;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 
 * @author Alexander Inagan
 */
public class GestorUsuariosDB {

    private final ConectorJDBC conector;
    
    /**
     * Constructor.
     */
    public GestorUsuariosDB() {
        this.conector = new ConectorJDBC();
    }
    
    /**
     * Agrega los datos de un Usuario en la base de datos. 
     * @param usuario_agregar
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public void agregarUsuario(Usuario usuario_agregar) throws ClassNotFoundException, SQLException
    {
        String usuario_id = usuario_agregar.getUsuario_id();
        String nombre_usuario = usuario_agregar.getNombre_usuario();
        String contrasenia = usuario_agregar.getContrasenia();
        String nombre_completo = usuario_agregar.getNombre_completo();
        String fecha_creacion = usuario_agregar.getFecha_creacion();
        String cargo = usuario_agregar.getCargo();
        
        conector.conectarse();
        String sql = "INSERT INTO USUARIO (USUARIO_ID, NOMBRE_USUARIO, CONTRASENIA, NOMBRE_COMPLETO, FECHA_CREACION, CARGO)"
                + " VALUES ("
                + "'" + usuario_id + "',"
                + "'" + nombre_usuario + "',"
                + "'" + contrasenia +  "',"
                + "'" + nombre_completo + "',"
                + "'" + fecha_creacion + "',"
                + "'" + cargo + "'"
                + ")";
        
        conector.actualizar(sql);
        conector.desconectarse();
    }
    
    /**
     * Edita la información de un Usuario que se encuentre en la base de datos. 
     * @param usuario_editar
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public void editarUsuario(Usuario usuario_editar) throws ClassNotFoundException, SQLException
    {
        String usuario_id = usuario_editar.getUsuario_id();
        String nombre_usuario = usuario_editar.getNombre_usuario();
        String contrasenia = usuario_editar.getContrasenia();
        String nombre_completo = usuario_editar.getNombre_completo();
        String fecha_creacion = usuario_editar.getFecha_creacion();
        String cargo = usuario_editar.getCargo();
        
        conector.conectarse();
        String sql = "UPDATE USUARIO SET "
                + "NOMBRE_USUARIO = '" + nombre_usuario + "',"
                + "CONTRASENIA = '" + contrasenia + "',"
                + "NOMBRE_COMPLETO = '" + nombre_completo + "',"
                + "FECHA_CREACION = '" + fecha_creacion + "',"
                + "CARGO = '" + cargo + "'"
                + "WHERE USUARIO_ID = '" + usuario_id + "'";
        conector.actualizar(sql);
        conector.desconectarse();
    }
    
    /**
     * Busca un Usuario en la base de datos de la agencia de viajes
     * @param usuario_ID
     * @return Usuario
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public Usuario consultarUsuario(String usuario_ID) throws ClassNotFoundException, SQLException
    {
        conector.conectarse();
        conector.crearConsulta("SELECT * FROM USUARIO WHERE USUARIO_ID = '" + usuario_ID + "'");
    
        Usuario usuario = null;
        if(conector.getResultado().next())
        {
            String usuario_id = conector.getResultado().getString("USUARIO_ID");
            String nombre_usuario = conector.getResultado().getString("NOMBRE_USUARIO");
            String contrasenia = conector.getResultado().getString("CONTRASENIA");
            String nombre_completo = conector.getResultado().getString("NOMBRE_COMPLETO");
            String fecha_creacion = conector.getResultado().getString("FECHA_CREACION");
            String cargo = conector.getResultado().getString("CARGO");

            usuario = new Usuario(usuario_id, nombre_usuario, contrasenia, nombre_completo, fecha_creacion, cargo);
        }
        conector.desconectarse();
        return usuario;
    }
    
    /**
     * Elimina la información de un Usuario de la base de datos. 
     * @param usuario_ID
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public void eliminarUsuario(String usuario_ID) throws ClassNotFoundException, SQLException
    {
        conector.conectarse();
        String sql = "DELETE FROM USUARIO "
                + "WHERE USUARIO_ID = '" + usuario_ID + "'";
        conector.actualizar(sql);
        conector.desconectarse();
    }
    
    /**
     * Trae de la base de datos a todos los usuarios
     * @return ArrayList
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public ArrayList<Usuario> consultarTodosUsuarios() throws ClassNotFoundException, SQLException
    {
        conector.conectarse();
        conector.crearConsulta("SELECT * FROM USUARIO");
        
        ArrayList<Usuario> listaUsuarios = new ArrayList<>();
        Usuario miUsuario;
        
        while(conector.getResultado().next())
        {
            String usuario_id = conector.getResultado().getString("USUARIO_ID");
            String nombre_usuario = conector.getResultado().getString("NOMBRE_USUARIO");
            String contrasenia = conector.getResultado().getString("CONTRASENIA");
            String nombre_completo = conector.getResultado().getString("NOMBRE_COMPLETO");
            String fecha_creacion = conector.getResultado().getString("FECHA_CREACION");
            String cargo = conector.getResultado().getString("CARGO");

            miUsuario = new Usuario(usuario_id, nombre_usuario, contrasenia, nombre_completo, fecha_creacion, cargo);
            listaUsuarios.add(miUsuario);
        }
        conector.desconectarse();
        return listaUsuarios;
    }
    
}