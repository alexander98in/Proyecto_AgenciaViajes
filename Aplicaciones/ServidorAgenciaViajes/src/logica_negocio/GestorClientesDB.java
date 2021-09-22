package logica_negocio;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 
 * @author Alexander Inagan
 */
public class GestorClientesDB {
    
    private final ConectorJDBC conector;
    
    /**
     * Constructor.
     */
    public GestorClientesDB() {
        this.conector = new ConectorJDBC();
    }
    
    /**
     * Agrega los datos de un cliente en la base de datos.
     * @param cliente_agregar
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public void agregarCliente(Cliente cliente_agregar) throws ClassNotFoundException, SQLException
    {
        String doc_id = cliente_agregar.getDoc_id();
        String nombres = cliente_agregar.getNombres();
        String apellidos = cliente_agregar.getApellidos();
        String fecha_nac = cliente_agregar.getFecha_nac();
        String email = cliente_agregar.getEmail();
        String genero = cliente_agregar.getGenero();
        String direccion = cliente_agregar.getDireccion();
        String ciudad = cliente_agregar.getCiudad();
        String celular = cliente_agregar.getCelular();
        
        conector.conectarse();
        String sql = "INSERT INTO CLIENTE (DOC_ID, NOMBRES, APELLIDOS, FECHA_NAC, EMAIL, GENERO, DIRECCION, CIUDAD, CELULAR)"
                + " VALUES ("
                + "'" + doc_id + "',"
                + "'" + nombres + "',"
                + "'" + apellidos + "',"
                + "'" + fecha_nac + "',"
                + "'" + email + "',"
                + "'" + genero + "',"
                + "'" + direccion + "',"
                + "'" + ciudad + "',"
                + "'" + celular + "'"
                + ")";
        
        conector.actualizar(sql);
        conector.desconectarse();
    }
        
    /**
     * Edita la informacion de un cliente que se encuentre en la base de datos
     * @param cliente_editar
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public void editarCliente(Cliente cliente_editar) throws ClassNotFoundException, SQLException
    {
        String doc_id = cliente_editar.getDoc_id();
        String nombres = cliente_editar.getNombres();
        String apellidos = cliente_editar.getApellidos();
        String fecha_nac = cliente_editar.getFecha_nac();
        String email = cliente_editar.getEmail();
        String genero = cliente_editar.getGenero();
        String direccion = cliente_editar.getDireccion();
        String ciudad = cliente_editar.getCiudad();
        String celular = cliente_editar.getCelular();
        
        conector.conectarse();
        String sql = "UPDATE CLIENTE SET "
                + "NOMBRES = '" + nombres + "',"
                + "APELLIDOS = '" + apellidos + "',"
                + "FECHA_NAC = '" + fecha_nac + "',"
                + "EMAIL = '" + email + "',"
                + "GENERO = '" + genero + "',"
                + "DIRECCION = '" + direccion + "',"
                + "CIUDAD = '" + ciudad + "'," 
                + "CELULAR = '" + celular + "'"
                + "WHERE DOC_ID = '" + doc_id + "'";
        conector.actualizar(sql);
        conector.desconectarse();
    }
    
    /**
     * Busca un cliente en la base de datos de la agencia de viajes
     * @param documento_id
     * @return Cliente
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public Cliente consultarCliente(String documento_id) throws ClassNotFoundException, SQLException
    {
        conector.conectarse();
        conector.crearConsulta("SELECT * FROM CLIENTE WHERE DOC_ID = '" + documento_id + "'");
        
        Cliente cliente = null;
        if(conector.getResultado().next())
        {
            String doc_id = conector.getResultado().getString("DOC_ID");
            String nombres = conector.getResultado().getString("NOMBRES");
            String apellidos = conector.getResultado().getString("APELLIDOS");
            String fecha_nac = conector.getResultado().getString("FECHA_NAC");
            String email = conector.getResultado().getString("EMAIL");
            String genero = conector.getResultado().getString("GENERO");
            String direccion = conector.getResultado().getString("DIRECCION");
            String ciudad = conector.getResultado().getString("CIUDAD");
            String celular = conector.getResultado().getString("CELULAR");
            
            cliente = new Cliente(doc_id, nombres, apellidos, fecha_nac, email, genero, direccion, ciudad, celular);
        }
        conector.desconectarse();
        return cliente;
    }
    
    /**
     * Elimina la información de un cliente
     * @param doc_id
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public void eliminarCliente(String doc_id) throws ClassNotFoundException, SQLException
    {
        conector.conectarse();
        String sql = "DELETE FROM CLIENTE "
                + "WHERE DOC_ID = '" + doc_id + "'";
        conector.actualizar(sql);
        conector.desconectarse();
    }   
       
    /**
     * Trae de la base de datos todos los clientes
     * @return ArrayList
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public ArrayList<Cliente> consultarTodosClientes() throws ClassNotFoundException, SQLException 
    {
        conector.conectarse();
        conector.crearConsulta("SELECT * FROM CLIENTE");
        
        ArrayList<Cliente> listaClientes = new ArrayList<>();
        Cliente miCliente;
        
        while(conector.getResultado().next()) 
        {
            String doc_id = conector.getResultado().getString("DOC_ID");
            String nombres = conector.getResultado().getString("NOMBRES");
            String apellidos = conector.getResultado().getString("APELLIDOS");
            String fecha_nac = conector.getResultado().getString("FECHA_NAC");
            String email = conector.getResultado().getString("EMAIL");
            String genero = conector.getResultado().getString("GENERO");
            String direccion = conector.getResultado().getString("DIRECCION");
            String ciudad = conector.getResultado().getString("CIUDAD");
            String celular = conector.getResultado().getString("CELULAR");
            
            miCliente = new Cliente(doc_id, nombres, apellidos, fecha_nac, email, genero, direccion, ciudad, celular);
            listaClientes.add(miCliente);
        }
        conector.desconectarse();
        return listaClientes;
    }
    
}