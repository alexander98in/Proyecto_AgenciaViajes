package logica_negocio;

import acceso.IServiciosClientes;
import acceso.ServiciosAgenciaSocket;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Properties;
import mvcf.AModel;

/**
 * 
 * @author Alexander Inagan
 */
public class GestorClientes extends AModel{
    
    private final IServiciosClientes servidorClientes;
    private ArrayList<Cliente> listaClientes;
    private Cliente cliente;
    private String respuesta;
    
    /**
     * Constructor
     */
    public GestorClientes() {
        servidorClientes = new ServiciosAgenciaSocket();
        listaClientes = new ArrayList<>();
        cliente = null;
        respuesta = "";
    }

    public ArrayList<Cliente> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(ArrayList<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }
    
    /**
     * Agrega un cliente en el servidor remoto de la Agencia de Viajes
     * @param nuevo_cliente 
     * @return String
     */
    public String agregar_Cliente(Cliente nuevo_cliente)
    {
        String json = servidorClientes.agregarCliente(nuevo_cliente);
        respuesta = json;
        return json;
    }
    
    /**
     * Edita la informacion de un cliente que se encuentre en el sevidor.
     * @param editar_cliente 
     * @return String
     */
    public String editar_Cliente(Cliente editar_cliente)
    {
        String json = servidorClientes.editarCliente(editar_cliente);
        respuesta = json;
        return json;
    }
    
    /**
     * Consulta un cliente en el servidor remoto de la Agencia de Viajes.
     * @param doc_id identificador del Cliente a consultar
     * @return Cliente
     */
    public Cliente consultar_Cliente(String doc_id) 
    {
        String json = servidorClientes.consultarCliente(doc_id);
        Cliente cli = null;
        if(!json.equals("No se encontro a ningun cliente con esa identificación."))
        {
            cli = parseToCliente(json);
            cliente = cli;
        }
        else   
        {
            respuesta = json;
        }
        return cli;
    }
    
    /**
     * Elimina la informacion de un cliente del servidor de la Agencia de Viajes
     * @param doc_id Identificador del cliente a Eliminar
     * @return String
     */
    public String eliminar_Cliente(String doc_id)
    {
        String json = servidorClientes.eliminarCliente(doc_id);
        respuesta = json;
        return json;
    }
    
    /**
     * Obtiene todos los clientes que se encuentran en el servidor remoto de la Agencia.
     * @return ArrayList
     */
    public ArrayList<Cliente> obtener_todos_Clientes()
    {
        String arrayJson = servidorClientes.obtenerTodosLosClientes();
        ArrayList<Cliente> lista_clientes = new ArrayList<>();
        if(!arrayJson.equals("Aun no se encuentra ningun cliente registrado."))
        {
            lista_clientes = deserializarMisClientes(arrayJson);
            listaClientes = lista_clientes;
        }
        else
        {
            respuesta = arrayJson;
        }
        return lista_clientes;
    }
    
    /**
     * Deserializa el objeto Json y lo convierte en un objeto Cliente.
     * @param cliente
     * @param json
     * @return Cliente
     */
    private Cliente parseToCliente(String json) 
    {
        Gson gson = new Gson();
        Properties propiedades = gson.fromJson(json, Properties.class);
        
        String doc_id = propiedades.getProperty("doc_id");
        String nombres = propiedades.getProperty("nombres");
        String apellidos = propiedades.getProperty("apellidos");
        String fecha_nac = propiedades.getProperty("fecha_nac");
        String email = propiedades.getProperty("email");
        String genero = propiedades.getProperty("genero");
        String direccion = propiedades.getProperty("direccion");
        String ciudad = propiedades.getProperty("ciudad");
        String celular = propiedades.getProperty("celular");
        
        Cliente miCliente = new Cliente(doc_id, nombres, apellidos, fecha_nac, email, genero, direccion, ciudad, celular);
        return miCliente;
    }
    
    /**
     * Deserializa el arrayJson de Clientes en un  arrayList de Clientes.
     * @param arrayJsonSerializado
     * @return ArrayList
     */
    private ArrayList<Cliente> deserializarMisClientes(String arrayJsonSerializado)
    {
        Cliente[] misClientes = new Gson().fromJson(arrayJsonSerializado, Cliente[].class);
        ArrayList<Cliente> lista_clientes = new ArrayList<>();
        
        for(int i=0; i<misClientes.length; i++) {
            lista_clientes.add(misClientes[i]);
        }       
        return lista_clientes;
    }
    
}