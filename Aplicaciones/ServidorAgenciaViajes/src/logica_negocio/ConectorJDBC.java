package logica_negocio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 * @author Alexander Inagan
 */
public class ConectorJDBC {
    
    private Connection conexion;
    private Statement sentencia;
    private ResultSet resultado;
    
    private final String USER = "servidor_agencia";
    private final String PASSWORD = "12345";
    private final String URL = "jdbc:hsqldb:file:E:\\INGENIERIA DE SISTEMAS\\6To Semestre\\Lab. Ingeniería de Software II\\Agencia de Viajes\\WServidorAgenciaViajes\\BaseDatos\\bd_agencia;hsqldb.lock_file=false";
    //Cambie la URL de su bd local, por ejemplo, si tiene Windows,sería algo como:
    //private final String URL = "jdbc:hsqldb:file:C:\\clientes\\bd\\clientes;hsqldb.lock_file=false";
    
    public ConectorJDBC() {
        super();
    }
    
    /**
     * Establece una conexion con la base de datos del servidor de la agencia de viajes.
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public void conectarse() throws ClassNotFoundException, SQLException {
        Class.forName("org.hsqldb.jdbcDriver");
        conexion = DriverManager.getConnection(URL, USER, PASSWORD);
    }
    
    /**
     * Ejecuta una consulta tipo Select
     * @param sql
     * @throws SQLException 
     */
    public void crearConsulta(String sql) throws SQLException 
    {
        sentencia = conexion.createStatement();
        resultado = sentencia.executeQuery(sql);
    }
    
    /**
     * Ejecuta una consulta tipo Insert, Update o Delete
     * @param sql
     * @throws SQLException 
     */
    public void actualizar(String sql) throws SQLException 
    {
        sentencia = conexion.createStatement();
        sentencia.executeUpdate(sql);
    }
    
    /**
     * Cierra las variables de resultado, sentencia y conexion que estén abiertas
     * @throws SQLException 
     */
    public void desconectarse() throws SQLException
    {
        if(resultado != null) 
            resultado.close();
        sentencia.close();
        conexion.close();
    }

    /**
     * Devuelve todo el conjunto de resultados.
     * @return ResultSet
     */
    public ResultSet getResultado() {
        return this.resultado;
    }
}