package logica_negocio;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 
 * @author Alexander Inagan
 */
public class GestorRegionesDB {
    
    private final ConectorJDBC conector;
    
    /**
     * Constructor
     */
    public GestorRegionesDB() {
        this.conector = new ConectorJDBC();
    }
    
    /**
     * Consulta las regiones que se encuentran registradas.
     * @return ArrayList
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public ArrayList<Region> consultarRegiones() throws ClassNotFoundException, SQLException
    {
        conector.conectarse();
        conector.crearConsulta("SELECT ID_REGION, NOMBRE_REGION FROM REGION");
        
        ArrayList<Region> listaRegiones = new ArrayList<>();
        Region miRegion;
        
        while(conector.getResultado().next())
        {
            String id_region = conector.getResultado().getString("ID_REGION");
            String nombre_region = conector.getResultado().getString("NOMBRE_REGION");
            
            miRegion = new Region(id_region, nombre_region);
            listaRegiones.add(miRegion);
        }
        conector.desconectarse();
        return listaRegiones;
    }
    
    /**
     * Consulta los paises de una region en especifico.
     * @param nombre_region
     * @return ArrayList
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public ArrayList<Region> consultarPaises(String nombre_region) throws ClassNotFoundException, SQLException 
    {
        conector.conectarse();
        String sql = "SELECT P.ID_PAIS, P.NOMBRE_PAIS "
                   + "FROM PAIS P INNER JOIN REGION R ON (P.ID_REGION = R.ID_REGION "
                   + "WHERE R.NOMBRE_REGION = '" + nombre_region + "'";
        
        conector.crearConsulta(sql);
        
        ArrayList<Region> listaPaises = new ArrayList<>();
        Region miPais;
        
        while(conector.getResultado().next())
        {
            String id_pais = conector.getResultado().getString("ID_PAIS");
            String nombre_pais = conector.getResultado().getString("NOMBRE_PAIS");
            
            miPais = new Region(id_pais, nombre_pais);
            listaPaises.add(miPais);
        }
        conector.desconectarse();
        return listaPaises;   
    }
    
    /**
     * Obtiene las ciudades de un País en especifico.
     * @param nombre_pais
     * @return ArrayList
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public ArrayList<Region> consultarCiudades(String nombre_pais) throws ClassNotFoundException, SQLException 
    {
        conector.conectarse();
        String sql = "SELECT C.ID_CIUDAD, C.NOMBRE_CIUDAD "
                   + "FROM CIUDAD C INNER JOIN PAIS P ON (C.ID_PAIS = P.ID_PAIS "
                   + "WHERE P.NOMBRE_PAIS = '" + nombre_pais + "'";
        
        conector.crearConsulta(sql);
        
        ArrayList<Region> listaCiudades = new ArrayList<>();
        Region miCiudad;
        
        while(conector.getResultado().next())
        {
            String id_ciudad = conector.getResultado().getString("ID_CIUDAD");
            String nombre_ciudad = conector.getResultado().getString("NOMBRE_CIUDAD");
            
            miCiudad = new Region(id_ciudad, nombre_ciudad);
            listaCiudades.add(miCiudad);
        }
        conector.desconectarse();
        return listaCiudades;
    }
}