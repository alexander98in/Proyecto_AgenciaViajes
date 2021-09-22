package utilidades;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import logica_negocio.Cliente;
import logica_negocio.Region;
import logica_negocio.Sesion;
import logica_negocio.Usuario;

/**
 * 
 * @author Alexander Inagan
 */
public class Serializar {
    
    /**
     * Constructor.
     */
    public Serializar() {
        super();
    }
    
    /**
     * Convierte un ArrayList de Cliente a un ArrayJson
     * @param listaCliente
     * @return String
     */
    public String serializarClientes(ArrayList<Cliente> listaCliente)
    {
        JsonArray array = new JsonArray();
        JsonObject objJson;
        
        for(Cliente cliente : listaCliente)
        {
            objJson = parseToJSONCliente(cliente);
            array.add(objJson);
        }
        System.out.println("Clientes json serializado: " + array.toString());
        return array.toString();
    }
    
    /**
     * Convierte un objeto Cliente en un Objeto JSON
     * @param objCliente
     * @return JsonObject
     */
    public JsonObject parseToJSONCliente(Cliente objCliente) 
    {
        JsonObject jsonobj = new JsonObject();
        
        jsonobj.addProperty("doc_id", objCliente.getDoc_id());
        jsonobj.addProperty("nombres", objCliente.getNombres());
        jsonobj.addProperty("apellidos", objCliente.getApellidos());
        jsonobj.addProperty("fecha_nac", objCliente.getFecha_nac());
        jsonobj.addProperty("email", objCliente.getEmail());
        jsonobj.addProperty("genero", objCliente.getGenero());
        jsonobj.addProperty("direccion", objCliente.getDireccion());
        jsonobj.addProperty("ciudad", objCliente.getCiudad());
        jsonobj.addProperty("celular", objCliente.getCelular());
        
        return jsonobj;
    }
 
    /**
     * Convierte un ArrayList de Usuario a un ArrayJson
     * @param listaUsuarios
     * @return String
     */
    public String serializarUsuarios(ArrayList<Usuario> listaUsuarios)
    {
        JsonArray array = new JsonArray();
        JsonObject objJson;
        
        for(Usuario usuario : listaUsuarios)
        {
            objJson = parseToJSONUsuario(usuario);
            array.add(objJson);
        }
        System.out.println("Usuarios json serializado: " + array.toString());
        return array.toString();
    }
    
    /**
     * Convierte un objeto Usuario en un Objeto JSON.
     * @param objUsuario
     * @return JsonObject
     */
    public JsonObject parseToJSONUsuario(Usuario objUsuario) 
    {
        JsonObject jsonobj = new JsonObject();
        
        jsonobj.addProperty("usuario_id", objUsuario.getUsuario_id());
        jsonobj.addProperty("nombre_usuario", objUsuario.getNombre_usuario());
        jsonobj.addProperty("contrasenia", objUsuario.getContrasenia());
        jsonobj.addProperty("nombre_completo", objUsuario.getNombre_completo());
        jsonobj.addProperty("fecha_creacion", objUsuario.getFecha_creacion());
        jsonobj.addProperty("cargo", objUsuario.getCargo());
       
        return jsonobj;
    }
    
    /**
     * Convierte un ArrayList de Sesiones en un ArrayJson
     * @param listaSesiones
     * @return 
     */
    public String serializarSesiones(ArrayList<Sesion> listaSesiones)
    {
        JsonArray array = new JsonArray();
        JsonObject objJson;
        
        for(Sesion sesion : listaSesiones)
        {
            objJson = parseToJSONSesion(sesion);
            array.add(objJson);
        }
        System.out.println("Sesiones json serializado: " + array.toString());
        return array.toString();
    }
    
    /**
     * Convierte un objeto Sesion en un Objeto JSON.
     * @param objSesion
     * @return JsonObject
     */
    public JsonObject parseToJSONSesion(Sesion objSesion) 
    {
        JsonObject jsonobj = new JsonObject();
        
        jsonobj.addProperty("usuario", objSesion.getUsuario());
        jsonobj.addProperty("contrasenia", objSesion.getContrasenia());
        jsonobj.addProperty("fecha_hora", objSesion.getFecha_hora());
        jsonobj.addProperty("ingreso_valido", objSesion.getIngreso_valido());
        
        return jsonobj;
    }
    
    /**
     * Convierte un ArrayList de Sesiones en un ArrayJson
     * @param listaRegiones
     * @return String, lista de regiones convertido en un Strin json
     */
    public String serializarRegiones(ArrayList<Region> listaRegiones)
    {
        JsonArray array = new JsonArray();
        JsonObject objJson;
        
        for(Region region : listaRegiones)
        {
            objJson = parseToJSONRegion(region);
            array.add(objJson);
        }
        System.out.println("Resiones json serializado: " + array.toString());
        return array.toString();
    }

    /**
     * Convierte un objRegion en un Objeto JSON
     * @param objRegion
     * @return JsonObject, Objeto Json 
     */
    public JsonObject parseToJSONRegion(Region objRegion) 
    {
        JsonObject jsonobj = new JsonObject();
        
        jsonobj.addProperty("id_region", objRegion.getId_region());
        jsonobj.addProperty("nombre_region", objRegion.getNombre_region());

        return jsonobj;        
    }
}
