package logica_negocio;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Alexander Inagan
 */
public class GestorSesionDB {
    
    private final ConectorJDBC conector;
    
    /**
     * Constructor
     */
    public GestorSesionDB() {
        conector = new ConectorJDBC();
    }
    
    /**
     * Agrega los datos de una sesión en la base de datos.
     * @param sesion_agregar
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public void agregarSesion(Sesion sesion_agregar) throws ClassNotFoundException, SQLException 
    {
        String usuario = sesion_agregar.getUsuario();
        String contrasenia = sesion_agregar.getContrasenia();
        String fecha_hora = sesion_agregar.getFecha_hora();
        String ingreso_valido = sesion_agregar.getIngreso_valido();
        
        conector.conectarse();
        String sql = "INSERT INTO SESION (USUARIO, CONTRASENIA, FECHA_HORA, INGRESO_VALIDO)"
                + " VALUES ("
                + "'" + usuario + "',"
                + "'" + contrasenia + "',"
                + "'" + fecha_hora + "',"
                + "'" + ingreso_valido + "'"
                + ")";
        conector.actualizar(sql);
        conector.desconectarse();
    }
    
    /**
     * Trae los intentos de sesion de un usuario en especifico
     * @param usuario_name
     * @return ArrayList
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public ArrayList<Sesion> consultarSesionesDeUnUsuario(String usuario_name) throws ClassNotFoundException, SQLException
    {
        conector.conectarse();
        conector.crearConsulta("SELECT * FROM SESION WHERE USUARIO = '" + usuario_name + "'");
        
        ArrayList<Sesion> listaSesionUsuario = new ArrayList<>();
        Sesion miSesion;
        
        while(conector.getResultado().next())
        {
            String usuario = conector.getResultado().getString("USUARIO");
            String contrasenia = conector.getResultado().getString("CONTRASENIA");
            String fecha_hora = conector.getResultado().getString("FECHA_HORA");
            String ingreso_valido = conector.getResultado().getString("INGRESO_VALIDO");
            
            miSesion = new Sesion(usuario, contrasenia, fecha_hora, ingreso_valido);
            listaSesionUsuario.add(miSesion);
        }
        return listaSesionUsuario;  
    }
    
    /**
     * Trae de la base de datos todas las sesiones registradas
     * @return ArrayList
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public ArrayList<Sesion> consultarTodasLasSesiones() throws ClassNotFoundException, SQLException
    {
        conector.conectarse();
        conector.crearConsulta("SELECT * FROM SESION");
        
        ArrayList<Sesion> listaSesiones = new ArrayList<>();
        Sesion miSesion;
        
        while(conector.getResultado().next())
        {
            String usuario = conector.getResultado().getString("USUARIO");
            String contrasenia = conector.getResultado().getString("CONTRASENIA");
            String fecha_hora = conector.getResultado().getString("FECHA_HORA");
            String ingreso_valido = conector.getResultado().getString("INGRESO_VALIDO");
            
            miSesion = new Sesion(usuario, contrasenia, fecha_hora, ingreso_valido);
            listaSesiones.add(miSesion);
        }
        return listaSesiones;
    }

}