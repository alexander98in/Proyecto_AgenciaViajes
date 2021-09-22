package acceso;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import logica_negocio.Cliente;
import logica_negocio.Sesion;
import logica_negocio.Usuario;

/**
 * 
 * @author Alexander Inagan
 */
public class ServiciosAgenciaSocket implements IServiciosClientes, IServiciosUsuarios, IServiciosSesiones, IServiciosRegiones {

    private Socket socket;
    private Scanner entradaDecorada;
    private PrintStream salidaDecorada;
    private final String IP_SERVIDOR = "localhost";
    private final int PUERTO = 5000;
    
    /**
     * Establece la conexión con el servidor.
     * @param direccion
     * @param puerto
     * @throws IOException 
     */
    private void conectar(String direccion, int puerto) throws IOException 
    {
        socket = new Socket(direccion, puerto);
        System.out.println("Conectado...");
    }
    
    /**
     * Lee los flujos de entrada y salida.
     * @param accion
     * @return String
     * @throws IOException 
     */
    private String leerFlujoEntradaSalida(String accion) throws IOException
    {
        String respuesta = "";
        entradaDecorada = new Scanner(socket.getInputStream());
        salidaDecorada = new PrintStream(socket.getOutputStream());
        salidaDecorada.flush();
        //Usando protocolo de comunicación
        salidaDecorada.println(accion);
        if(entradaDecorada.hasNextLine()) {
            respuesta = entradaDecorada.nextLine();
        }
        
        return respuesta;
    }
    
    /**
     * Cierra los flujos en entrada y salida.
     */
    private void cerrarFlujos() {
        salidaDecorada.close();
        entradaDecorada.close();
    }
    
    /**
     * Cierra la conexión
     */
    private void desconectar() {
        try {
            socket.close();
        }
        catch(IOException ex) {
            Logger.getLogger(ServiciosAgenciaSocket.class.getName()).log(Level.SEVERE, null, ex); 
        }    
    }
    
    /**
     * Agrega la información de un cliente en el servidor de la Agencia
     * @param nuevoCliente
     * @return String
     */
    @Override
    public String agregarCliente(Cliente nuevoCliente) 
    {
        String respuesta = null;
        String accion = "Agregar Cliente";
        String informacionCliente = nuevoCliente.getDoc_id() + "," + nuevoCliente.getNombres() + "," + nuevoCliente.getApellidos() + "," + nuevoCliente.getFecha_nac() + "," + nuevoCliente.getEmail() + "," + nuevoCliente.getGenero() + "," + nuevoCliente.getDireccion() + "," + nuevoCliente.getCiudad() + "," + nuevoCliente.getCelular();
        try 
        {
            conectar(IP_SERVIDOR, PUERTO);
            respuesta = leerFlujoEntradaSalida(accion + "," + informacionCliente);
            cerrarFlujos();
            desconectar();
        }
        catch(IOException ex) {
            Logger.getLogger(ServiciosAgenciaSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }
    
    /**
     * Edita la información de un cliente
     * @param clienteEditar
     * @return String
     */
    @Override
    public String editarCliente(Cliente clienteEditar)
    {
        String respuesta = null;
        String accion = "Editar Cliente";
        String informacionCliente = clienteEditar.getDoc_id() + "," + clienteEditar.getNombres() + "," + clienteEditar.getApellidos() + "," + clienteEditar.getFecha_nac() + "," + clienteEditar.getEmail() + "," + clienteEditar.getGenero() + "," + clienteEditar.getDireccion() + "," + clienteEditar.getCiudad() + "," + clienteEditar.getCelular();
        try 
        {
            conectar(IP_SERVIDOR, PUERTO);
            respuesta = leerFlujoEntradaSalida(accion + "," + informacionCliente);
            cerrarFlujos();
            desconectar();
        }
        catch(IOException ex) {
            Logger.getLogger(ServiciosAgenciaSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;       
    }

    /**
     * Consulta la informacion de un cliente en especifico.
     * @param doc_id
     * @return String 
     */
    @Override
    public String consultarCliente(String doc_id)
    {
        String respuesta = null;
        String accion = "Consultar Cliente";
        try 
        {
            conectar(IP_SERVIDOR, PUERTO);
            respuesta = leerFlujoEntradaSalida(accion + "," + doc_id);
            cerrarFlujos();
            desconectar();
        }
        catch(IOException ex) {
            Logger.getLogger(ServiciosAgenciaSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;  
    }

    /**
     * Elimina la información de un cliente en especifico.
     * @param doc_id
     * @return String 
     */
    @Override
    public String eliminarCliente(String doc_id)
    {
        String respuesta = null;
        String accion = "Eliminar Cliente";
        try
        {
            conectar(IP_SERVIDOR, PUERTO);
            respuesta = leerFlujoEntradaSalida(accion + "," + doc_id);
            cerrarFlujos();
            desconectar();
        }
        catch (IOException ex) {
            Logger.getLogger(ServiciosAgenciaSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }

    /**
     * Obtiene la información de todos los clientes del servidor.
     * @return String
     */
    @Override
    public String obtenerTodosLosClientes()
    {
        String respuesta = null;
        String accion = "Consultar todos los Clientes";
        try
        {
            conectar(IP_SERVIDOR, PUERTO);
            respuesta = leerFlujoEntradaSalida(accion);
            cerrarFlujos();
            desconectar();
        }
        catch (IOException ex) {
            Logger.getLogger(ServiciosAgenciaSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;   
    }
    
    /**
     * Agrega la información de un usuario en el servidor de la Agencia
     * @param nuevoUsuario
     * @return String
     */
    @Override
    public String agregarUsuario(Usuario nuevoUsuario)
    {
        String respuesta = null;
        String accion = "Agregar Usuario";
        String informacionUsuario = nuevoUsuario.getUsuario_id() + "," + nuevoUsuario.getNombre_usuario() + "," + nuevoUsuario.getContrasenia() + "," + nuevoUsuario.getNombre_completo() + "," + nuevoUsuario.getFecha_creacion() + "," + nuevoUsuario.getCargo();
        try
        {
            conectar(IP_SERVIDOR, PUERTO);
            respuesta = leerFlujoEntradaSalida(accion + "," + informacionUsuario);
            cerrarFlujos();
            desconectar();
        }
        catch(IOException ex) {
            Logger.getLogger(ServiciosAgenciaSocket.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return respuesta;
    }
    
    /**
     * Edita la información de un usuario.
     * @param usuarioEditar
     * @return String
     */
    @Override
    public String editarUsuario(Usuario usuarioEditar)
    {
        String respuesta = null;
        String accion = "Editar Usuario";
        String informacionUsuario = usuarioEditar.getUsuario_id() + "," + usuarioEditar.getNombre_usuario() + "," + usuarioEditar.getContrasenia() + "," + usuarioEditar.getNombre_completo() + "," + usuarioEditar.getFecha_creacion() + "," + usuarioEditar.getCargo();
        try
        {
            conectar(IP_SERVIDOR, PUERTO);
            respuesta = leerFlujoEntradaSalida(accion + "," + informacionUsuario);
            cerrarFlujos();
            desconectar();
        }
        catch(IOException ex) {
            Logger.getLogger(ServiciosAgenciaSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }
    
    /**
     * Consulta la informacion de un usuario en especifico.
     * @param user_id
     * @return String
     */
    @Override
    public String consultarUsuario(String user_id)
    {
        String respuesta = null;
        String accion = "Consultar Usuario";
        try
        {
            conectar(IP_SERVIDOR, PUERTO);
            respuesta = leerFlujoEntradaSalida(accion + "," + user_id);
            cerrarFlujos();
            desconectar();
        }
        catch(IOException ex) {
            Logger.getLogger(ServiciosAgenciaSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }
    
    /**
     * Elimina la información de un usaurio en especifico.
     * @param user_id
     * @return String
     */
    @Override
    public String eliminarUsuario(String user_id)
    {
        String respuesta = null;
        String accion = "Eliminar Usuario";
        try
        {
            conectar(IP_SERVIDOR, PUERTO);
            respuesta = leerFlujoEntradaSalida(accion + "," + user_id);
            cerrarFlujos();
            desconectar();
        }
        catch (IOException ex) {
            Logger.getLogger(ServiciosAgenciaSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;   
    }
    
    /**
     * Obtiene la información de todos los usuarios del servidor.
     * @return String
     */
    @Override
    public String obtenerTodosLosUsuarios()
    {
        String respuesta = null;
        String accion = "Consultar todos los Usuarios";
        try
        {
            conectar(IP_SERVIDOR, PUERTO);
            respuesta = leerFlujoEntradaSalida(accion);
            cerrarFlujos();
            desconectar();
        }
        catch (IOException ex) {
            Logger.getLogger(ServiciosAgenciaSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta; 
    }

    /**
     * Agrega la información de una Sesión en el servidor de la Agencia.
     * @param sesion_agregar
     * @return String
     */
    @Override
    public String agregarSesion(Sesion sesion_agregar)
    {
        String respuesta = null;
        String accion = "Agregar Sesion";
        String informacionSesion = sesion_agregar.getUsuario() + "," + sesion_agregar.getContrasenia() + "," + sesion_agregar.getFecha_hora() + "," + sesion_agregar.getIngreso_valido();
        try
        {
            conectar(IP_SERVIDOR, PUERTO);
            respuesta = leerFlujoEntradaSalida(accion + "," + informacionSesion);
            cerrarFlujos();
            desconectar();
        }
        catch(IOException ex) {
            Logger.getLogger(ServiciosAgenciaSocket.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return respuesta;
    }
    
    /**
     * Obtiene la informacion de sesiones de un usuario en especifico
     * @param user_name
     * @return String
     */
    @Override
    public String obtenerSesionesDeUnUsuario(String user_name)
    {
        String respuesta = null;
        String accion = "Consultar Sesiones de un Usuario";
        try
        {
            conectar(IP_SERVIDOR, PUERTO);
            respuesta = leerFlujoEntradaSalida(accion + "," + user_name); 
        }
        catch (IOException ex) {
            Logger.getLogger(ServiciosAgenciaSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }
    
    /**
     * Obtiene la información de todas las sesiones registradas en el servidor.
     * @return String
     */
    @Override
    public String obtenerTodasLasSesiones()
    {
        String respuesta = null;
        String accion = "Consultar todas las Sesiones";
        try
        {
            conectar(IP_SERVIDOR, PUERTO);
            respuesta = leerFlujoEntradaSalida(accion);
            cerrarFlujos();
            desconectar();
        }
        catch (IOException ex) {
            Logger.getLogger(ServiciosAgenciaSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }
    
    /**
     * Consulta las regiones registradas en el servidor.
     * @return String
     */
    @Override
    public String consultarRegiones()
    {
        String respuesta = null;
        String accion = "Consultar Regiones";
        
        try
        {
            conectar(IP_SERVIDOR, PUERTO);
            respuesta = leerFlujoEntradaSalida(accion);
            cerrarFlujos();
            desconectar();
        }
        catch (IOException ex) {
            Logger.getLogger(ServiciosAgenciaSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }
    
    /**
     * Consulta los paises de una region en especifico.
     * @param nombre_region
     * @return String, paises de la región
     */
    @Override
    public String consultarPaises(String nombre_region)
    {
        String respuesta = null;
        String accion = "Consultar Paises";
        try
        {
            conectar(IP_SERVIDOR, PUERTO);
            respuesta = leerFlujoEntradaSalida(accion + "," + nombre_region);
            cerrarFlujos();
            desconectar();
        }
        catch (IOException ex) {
            Logger.getLogger(ServiciosAgenciaSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }
    
    /**
     * Consulta todas las ciudades de una país en especifico
     * @param nombre_ciudad
     * @return String, Ciudades del País
     */
    @Override
    public String consultarCiudades(String nombre_ciudad)
    {
        String respuesta = null;
        String accion = "Consultar Ciudades";   
        try
        {
            conectar(IP_SERVIDOR, PUERTO);
            respuesta = leerFlujoEntradaSalida(accion + "," + nombre_ciudad);
            cerrarFlujos();
            desconectar();
        }
        catch (IOException ex) {
            Logger.getLogger(ServiciosAgenciaSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }
    
}