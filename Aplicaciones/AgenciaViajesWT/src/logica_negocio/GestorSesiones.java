package logica_negocio;

import acceso.IServiciosSesiones;
import acceso.ServiciosAgenciaSocket;
import com.google.gson.Gson;
import java.util.ArrayList;

/**
 * 
 * @author Alexander Inagan
 */
public class GestorSesiones {
    
    private final IServiciosSesiones servidorSesiones;
    private ArrayList<Sesion> listaSesiones;
    private Sesion sesion;
    private String respuesta;
    
    /**
     * Constructor
     */
    public GestorSesiones() {
        servidorSesiones = new ServiciosAgenciaSocket();
        listaSesiones = new ArrayList<>();
        sesion = null;
        respuesta = "";
    }

    public ArrayList<Sesion> getListaSesiones() {
        return listaSesiones;
    }

    public void setListaSesiones(ArrayList<Sesion> listaSesiones) {
        this.listaSesiones = listaSesiones;
    }

    public Sesion getSesion() {
        return sesion;
    }

    public void setSesion(Sesion sesion) {
        this.sesion = sesion;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }
    
    /**
     * Agrega la informacion de una Sesión al servidor remoto de la Agencia de Viajes.
     * @param nueva_sesion
     * @return String
     */
    public String agregar_Sesion(Sesion nueva_sesion)
    {
        String json = servidorSesiones.agregarSesion(nueva_sesion);
        respuesta = json;
        return json;
    }
    
    /**
     * Obtiene la sesiones registrada de un usuario en particular.
     * @param user_name 
     * @return ArrayList
     */
    public ArrayList<Sesion> obtener_sesiones_por_usuario(String user_name)
    {
        String arrayJson = servidorSesiones.obtenerSesionesDeUnUsuario(user_name);
        ArrayList<Sesion> lista_sesiones = new ArrayList<>();
        if(!arrayJson.equals("Aun no se encuentra registrado ningun intento de sesion del usuario: " + user_name))
        {
            lista_sesiones = deserialiaLasSesiones(arrayJson);
            listaSesiones = lista_sesiones;
        }
        else
        {
            respuesta = arrayJson;
        }
        return lista_sesiones;
    }
    
    /**
     * Obtiene todas las sesiones del servidor remoto de la Agencia.
     * @return ArrayList
     */
    public ArrayList<Sesion> obtener_todas_sesiones()
    {
        String arrayJson = servidorSesiones.obtenerTodasLasSesiones();
        ArrayList<Sesion> lista_sesiones = new ArrayList<>();
        if(!arrayJson.equals("Aun no se encuentra ninguna sesión registrada."))
        {
            lista_sesiones = deserialiaLasSesiones(arrayJson);
            listaSesiones = lista_sesiones;
        }
        else
        {
            respuesta = arrayJson;
        }
        return lista_sesiones;
    }
    
    /**
     * Deserializa el ArrayJson de Sesiones en una ArrayList de Sesiones.
     * @param arrayJsonSerializado
     * @return ArrayList
     */
    private ArrayList<Sesion> deserialiaLasSesiones(String arrayJsonSerializado)
    {
        Sesion[] misSesiones = new Gson().fromJson(arrayJsonSerializado, Sesion[].class);
        ArrayList<Sesion> lista_sesiones = new ArrayList<>();
        
        for(int i=0; i<misSesiones.length; i++) {
            lista_sesiones.add(misSesiones[i]);
        }
        return lista_sesiones;  
    }
}
