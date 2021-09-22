package logica_negocio;

import acceso.IServiciosUsuarios;
import acceso.ServiciosAgenciaSocket;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Properties;

/**
 * 
 * @author Alexander Inagan
 */
public class GestorUsuarios {
    
    private final IServiciosUsuarios servidorUsuarios;
    private ArrayList<Usuario> listaUsuarios;
    private Usuario usuario;
    private String respuesta;
    
    /**
     * Constructor.
     */
    public GestorUsuarios() {
        servidorUsuarios = new ServiciosAgenciaSocket();
        listaUsuarios = new ArrayList<>();
        usuario = null;
        respuesta = "";
    }

    public ArrayList<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(ArrayList<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }
    
    /**
     * Agrega un usuario en el servidor remoto de la Agencia de Viajes
     * @param nuevo_usuario 
     * @return String
     */
    public String agregar_Usuario(Usuario nuevo_usuario)
    {
        String json = servidorUsuarios.agregarUsuario(nuevo_usuario);
        respuesta = json;
        return json;
    }
    
    /**
     * Edita la informacion de un usuario que se encuentre en el sevidor.
     * @param editar_usuario 
     * @return String
     */
    public String editar_Usuario(Usuario editar_usuario)
    {
        String json = servidorUsuarios.editarUsuario(editar_usuario);
        respuesta = json;
        return json;
    }
    
    /**
     * Consulta un usuario en el servidor remoto de la Agencia de Viajes.
     * @param user_id 
     * @return Usuario
     */
    public Usuario consultar_Usuario(String user_id)
    {
        String json = servidorUsuarios.consultarUsuario(user_id);
        Usuario user = null;
        if(!json.equals("No se encontro a ningun usuario con ese id."))
        {
            user = parseToUsuario(json);
            usuario = user;
        }
        else
        {
            respuesta = json;
        }
        return user;
    }
    
    /**
     * Elimina la informacion de un usuario del servidor de la Agencia.
     * @param user_id
     * @return String
     */
    public String eliminar_Usuario(String user_id)
    {
        String json = servidorUsuarios.eliminarUsuario(user_id);
        respuesta = json;
        return json;
    }
    
    /**
     * Obtiene todos los usuarios que se encuentran en el servidor remotod de la Agencia
     * @return ArrayList
     */
    public ArrayList<Usuario> obtener_todos_Usuarios()
    {
        String arrayJson = servidorUsuarios.obtenerTodosLosUsuarios();
        ArrayList<Usuario> lista_usuarios = new ArrayList<>();
        if(!arrayJson.equals("Aun no se encuentra ningun usario registrado."))
        {
            lista_usuarios= deserializarMisUsuarios(arrayJson);
            listaUsuarios = lista_usuarios;
        }
        else
        {
            respuesta = arrayJson;
        }
        return lista_usuarios;
    }
    
    /**
     * Deserializa el objeto Json y lo convierte en un objeto Usuario.
     * @param json
     * @return Usuario
     */
    private Usuario parseToUsuario(String json)
    {
        Gson gson = new Gson();
        Properties propiedades = gson.fromJson(json, Properties.class);
        
        String usuario_id = propiedades.getProperty("usuario_id");
        String nombre_usuario = propiedades.getProperty("nombre_usuario");
        String contrasenia = propiedades.getProperty("contrasenia");
        String nombre_completo =  propiedades.getProperty("nombre_completo"); 
        String fecha_creacion =  propiedades.getProperty("fecha_creacion");
        String cargo = propiedades.getProperty("cargo");
        
        Usuario miUsuario = new Usuario(usuario_id, nombre_usuario, contrasenia, nombre_completo, fecha_creacion, cargo);
        return miUsuario;
    }
    
    /**
     * Deserializa el arrayJson de Clientes en un  arrayList de Usuarios.
     * @param arrayJsonSerializado
     * @return ArrayList
     */
    private ArrayList<Usuario> deserializarMisUsuarios(String arrayJsonSerializado)
    {
        Usuario[] misUsuarios = new Gson().fromJson(arrayJsonSerializado, Usuario[].class);
        ArrayList<Usuario> lista_usuarios = new ArrayList<>();
        
        for(int i=0; i<misUsuarios.length; i++) {
            lista_usuarios.add(misUsuarios[i]);
        }
        return lista_usuarios;
    }
}
