package logica_negocio;

import acceso.IServiciosRegiones;
import acceso.ServiciosAgenciaSocket;
import com.google.gson.Gson;
import java.util.ArrayList;

/**
 * 
 * @author Alexander Inagan
 */
public class GestorRegiones {
    
    private final IServiciosRegiones servidorRegiones;
    private String respuesta;
    
    /**
     * Constructor
     */
    public GestorRegiones() {
        servidorRegiones = new ServiciosAgenciaSocket();
        respuesta = "";
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }
    
    /**
     * Obtiene la regiones que se encuentran en el servidor de la Agencia.
     * @return ArrayList
     */
    public ArrayList<Region> obtener_Regiones()
    {
        String arrayJson = servidorRegiones.consultarRegiones();
        ArrayList<Region> lista_regiones = new ArrayList<>();
        if(!arrayJson.equals("Aun no existen regiones registradas."))
        {
            lista_regiones = deserializarMisRegiones(arrayJson);
        }
        else
        {
            respuesta = arrayJson;
        }
        return lista_regiones;
    }
    
    /**
     * Obtiene los paises de esa region.
     * @param nombre_region
     * @return ArrayList
     */
    public ArrayList<Region> obtener_Paises(String nombre_region)
    {
        String arrayJson = servidorRegiones.consultarPaises(nombre_region);
        ArrayList<Region> lista_paises = new ArrayList<>();
        if(!arrayJson.equals("Aun no existen paises de esa region registrados."))
        {
            lista_paises = deserializarMisRegiones(arrayJson);
        }
        else
        {
            respuesta = arrayJson;
        }
        return lista_paises;
    }
    
    /**
     * Obtiene las ciudades de ese pais.
     * @param nombre_ciudad
     * @return ArrayList
     */
    public ArrayList<Region> obtener_Ciudades(String nombre_ciudad)
    {
        String arrayJson = servidorRegiones.consultarCiudades(nombre_ciudad);
        ArrayList<Region> lista_ciudades = new ArrayList<>();
        if(!arrayJson.equals("Aun no existen ciudades de ese país registradas."))
        {
            lista_ciudades = deserializarMisRegiones(arrayJson);
        }
        else
        {
            respuesta = arrayJson;
        }
        return lista_ciudades;
    }
    
    /**
     * Deserializa el arrayJson de Regiones en un  arrayList de Regiones.
     * @param arrayJsonSerializado
     * @return ArrayList
     */
    private ArrayList<Region> deserializarMisRegiones(String arrayJsonSerializado)
    {
        Region[] misRegiones = new Gson().fromJson(arrayJsonSerializado, Region[].class);
        ArrayList<Region> lista_regiones = new ArrayList<>();
        
        for(int i=0; i<misRegiones.length; i++) {
            lista_regiones.add(misRegiones[i]);
        }
        return lista_regiones;
    }

}
