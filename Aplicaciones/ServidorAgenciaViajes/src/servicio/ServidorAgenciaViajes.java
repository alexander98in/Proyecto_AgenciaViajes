package servicio;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import logica_negocio.Cliente;
import logica_negocio.GestorClientesDB;
import logica_negocio.GestorRegionesDB;
import logica_negocio.GestorSesionDB;
import logica_negocio.GestorUsuariosDB;
import logica_negocio.Region;
import logica_negocio.Sesion;
import logica_negocio.Usuario;
import utilidades.Serializar;

/**
 * 
 * @author Alexander Inagan
 */
public class ServidorAgenciaViajes implements Runnable {

    private final GestorClientesDB gestorClientes;
    private final GestorUsuariosDB gestorUsuarios;
    private final GestorSesionDB gestorSesiones;
    private final GestorRegionesDB gestorRegiones;
    private final Serializar objSerializador;
    
    private static ServerSocket serverSocket;
    private static Socket socket;
    private Scanner entradaDecorada;
    private PrintStream salidaDecorada;
    private static final int PUERTO = 5000;
    
    /**
     * Constructor
     */
    public ServidorAgenciaViajes() 
    {
        gestorClientes = new GestorClientesDB();
        gestorUsuarios = new GestorUsuariosDB();
        gestorSesiones = new GestorSesionDB();
        gestorRegiones = new GestorRegionesDB();
        objSerializador = new Serializar();
    }
    
    /**
     * Logica completa del servidor
     */
    public void iniciar() {
        abrirPuerto();
        
        while(true) {
            esperarCliente();
            lanzarHilo();
        }
    }
    
    /**
     * Abre el puerto de comunicación
     */
    private static void abrirPuerto() {
        try {
            serverSocket = new ServerSocket(PUERTO);
            System.out.println("Servidor escuchando por el puerto: " + PUERTO);
        }
        catch(IOException ex) {          
            Logger.getLogger(ServidorAgenciaViajes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Espera que el cliente se conecte y le devuelve un Socket
     */
    private static void esperarCliente() {
        try {
            socket = serverSocket.accept();
            System.out.println("Cliente conectado...");
        }
        catch(IOException ex) {
            Logger.getLogger(ServidorAgenciaViajes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Lanza el Hilo
     */
    private static void lanzarHilo() {
        new Thread (new ServidorAgenciaViajes()).start();
    }
    
    /**
     * Cuerpo del Hilo
     */
    @Override
    public void run() {
        try {
            crearFlujos();
            leerFlujos();
            cerrarFlujos();
        }
        catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
        catch(ClassNotFoundException | SQLException excep) {
            Logger.getLogger(ServidorAgenciaViajes.class.getName()).log(Level.SEVERE, null, excep);
        }  
    }
    
    /**
     * Crea los flujos con el Socket
     * @throws IOException 
     */
    private void crearFlujos() throws IOException {
        salidaDecorada = new PrintStream(socket.getOutputStream());
        entradaDecorada = new Scanner(socket.getInputStream());
    }
    
    /**
     * Lee los flujos del Socket.
     */
    private void leerFlujos() throws ClassNotFoundException, SQLException
    {
        if(entradaDecorada.hasNextLine())
        {
            //Extrae el flujo que envia el cliente
            String peticion = entradaDecorada.nextLine();
            decodificarPeticion(peticion);
        }
        else
        {
            salidaDecorada.flush();
            salidaDecorada.println("No_Encontrado.");
        }
    }
    
    /**
     * Cierra los flujos de entrada y salida.
     * @throws IOException 
     */
    private void cerrarFlujos() throws IOException
    {
        salidaDecorada.close();
        entradaDecorada.close();
        socket.close();
    }
    
    /**
     * Decodifica la petición, extrayendo la acción y los parametros 
     * @param peticion petición completa al estilo "accion, información"
     */
    private void decodificarPeticion(String peticion) throws ClassNotFoundException, SQLException
    {
        StringTokenizer tokens = new StringTokenizer(peticion, ",");
        String parametros[] = new String[tokens.countTokens() + 1];
        
        int i=0;
        while(tokens.hasMoreTokens()) {
            parametros[i++] = tokens.nextToken();
        }
        
        String accion = parametros[0];
        procesarAccion(accion, parametros);
    }
    
    /**
     * Procesa las acciones enviadas por el cliente
     * @param accion
     * @param parametros
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    private void procesarAccion(String accion, String parametros[]) throws ClassNotFoundException, SQLException
    {
        //Información del cliente
        String doc_id;
        String nombres;
        String apellidos;
        String fecha_nac;
        String email;
        String genero;
        String direccion;
        String ciudad;
        String celular;
        
        Cliente cliente;
        
        //Información del Usuario 
        String usuario_id;
        String nombre_usuario;
        String contrasenia;
        String nombre_completo; 
        String fecha_creacion;
        String cargo;
        
        Usuario usuario;
        
        //Informacion de las Sesiones
        String usuario_sesion;
        String contrasenia_sesion;
        String fecha_hora;
        String ingreso_valido;
        
        Sesion sesion;

        //Selecciona la acción
        switch(accion) 
        {
            case "Agregar Cliente":
                
                doc_id = parametros[1];
                nombres = parametros[2];
                apellidos = parametros[3];
                fecha_nac = parametros[4];
                email = parametros[5];
                genero = parametros[6];
                direccion = parametros[7];
                ciudad = parametros[8];
                celular = parametros[9];
                
                cliente = gestorClientes.consultarCliente(doc_id);
                if(cliente == null)
                {
                    cliente = new Cliente(doc_id, nombres, apellidos, fecha_nac, email, genero, direccion, ciudad, celular);
                    gestorClientes.agregarCliente(cliente);
                    salidaDecorada.println("Se agrego con exito la información del cliente.");
                }
                else
                {
                    salidaDecorada.println("El cliente con esa información ya se encuentra registrado!");
                }              
            break;
            
            case "Editar Cliente":
                
                doc_id = parametros[1];
                nombres = parametros[2];
                apellidos = parametros[3];
                fecha_nac = parametros[4];
                email = parametros[5];
                genero = parametros[6];
                direccion = parametros[7];
                ciudad = parametros[8];
                celular = parametros[9];
                
                cliente = gestorClientes.consultarCliente(doc_id);
                if(cliente == null) 
                {
                    salidaDecorada.println("No se encuentra a ningun cliente con esa identificación.");                    
                }
                else
                {
                    cliente = new Cliente(doc_id, nombres, apellidos, fecha_nac, email, genero, direccion, ciudad, celular);
                    gestorClientes.editarCliente(cliente);
                    salidaDecorada.println("Se edito con exito la información del cliente: " + cliente.getNombres());
                }     
            break;
            
            case "Consultar Cliente":
                
                doc_id = parametros[1];
                cliente = gestorClientes.consultarCliente(doc_id);
                
                if(cliente == null) 
                {
                    salidaDecorada.println("No se encontro a ningun cliente con esa identificación.");
                }
                else
                {
                    JsonObject objJsonCliente = objSerializador.parseToJSONCliente(cliente);
                    salidaDecorada.println(objJsonCliente.toString());
                }
            break;
            
            case "Eliminar Cliente":
                
                doc_id = parametros[1];
                Cliente clienteAUX = gestorClientes.consultarCliente(doc_id);
                
                if(clienteAUX == null) 
                {
                    salidaDecorada.println("La informacion del cliente que desea eliminar no existe!");
                }
                else
                {
                    gestorClientes.eliminarCliente(doc_id);
                    cliente = gestorClientes.consultarCliente(doc_id);
                    if(cliente == null)
                        salidaDecorada.println("La información del cliente se elimino con exito.");
                    else
                        salidaDecorada.println("Ocurrio un error al eliminar la información del cliente.");       
                }
            break;
            
            case "Consultar todos los Clientes":
        
                ArrayList<Cliente> listaCliente = gestorClientes.consultarTodosClientes();
                
                if(listaCliente.isEmpty())
                {
                    salidaDecorada.println("Aun no se encuentra ningun cliente registrado.");
                }
                else
                {
                    salidaDecorada.println(objSerializador.serializarClientes(listaCliente));
                }
            break;
            
            case "Agregar Usuario":
                
                usuario_id = parametros[1];
                nombre_usuario = parametros[2];
                contrasenia = parametros[3];
                nombre_completo = parametros[4];
                fecha_creacion = parametros[5];
                cargo = parametros[6];
                
                usuario = gestorUsuarios.consultarUsuario(usuario_id);
                if(usuario == null)
                {
                    usuario = new Usuario(usuario_id, nombre_usuario, contrasenia, nombre_completo, fecha_creacion, cargo);
                    gestorUsuarios.agregarUsuario(usuario);
                    salidaDecorada.println("Se agrego con exito la información del usuario.");
                }
                else
                {
                    salidaDecorada.println("El usuario con esa información ya se encuentra registrado!");
                }
            break;
            
            case "Editar Usuario":
                
                usuario_id = parametros[1];
                nombre_usuario = parametros[2];
                contrasenia = parametros[3];
                nombre_completo = parametros[4];
                fecha_creacion = parametros[5];
                cargo = parametros[6];
                
                usuario = gestorUsuarios.consultarUsuario(usuario_id);
                if(usuario == null)
                {
                    salidaDecorada.println("No se encuentra a ningun usuario con esa identificación.");
                }
                else
                {
                    usuario = new Usuario(usuario_id, nombre_usuario, contrasenia, nombre_completo, fecha_creacion, cargo);
                    gestorUsuarios.editarUsuario(usuario);
                    salidaDecorada.println("Se edito con exito la información del usuario: " + usuario.getNombre_completo());
                }                
            break;

            case "Consultar Usuario":
                
                usuario_id = parametros[1];
                usuario = gestorUsuarios.consultarUsuario(usuario_id);
                
                if(usuario == null)
                {
                    salidaDecorada.println("No se encontro a ningun usuario con ese id.");
                }
                else
                {
                    JsonObject objJsonUsuario = objSerializador.parseToJSONUsuario(usuario);
                    salidaDecorada.println(objJsonUsuario.toString());
                }       
            break;

            case "Eliminar Usuario":
                
                usuario_id = parametros[1];
                Usuario usuarioAUX = gestorUsuarios.consultarUsuario(usuario_id);
                
                if(usuarioAUX == null)
                {
                    salidaDecorada.println("La información del usuario que desea eliminar no existe!");
                }
                else
                {
                    gestorUsuarios.eliminarUsuario(usuario_id);
                    usuario = gestorUsuarios.consultarUsuario(usuario_id);
                    if(usuario == null)
                        salidaDecorada.println("La información del usuario se elimino con exito.");
                    else
                        salidaDecorada.println("Ocurrio un error al eliminar la información del usuario.");
                }    
            break;
            
            case "Consultar todos los Usuarios":
                
                ArrayList<Usuario> listaUsuario = gestorUsuarios.consultarTodosUsuarios();
                
                if(listaUsuario.isEmpty())
                {
                    salidaDecorada.println("Aun no se encuentra ningun usario registrado.");
                }
                else
                {
                    salidaDecorada.println(objSerializador.serializarUsuarios(listaUsuario));
                }
            break;
            
            case "Agregar Sesion":
                
                usuario_sesion = parametros[1];
                contrasenia_sesion = parametros[2];
                fecha_hora = parametros[3];
                ingreso_valido = parametros[4];
                
                sesion = new Sesion(usuario_sesion, contrasenia_sesion, fecha_hora, ingreso_valido);
                gestorSesiones.agregarSesion(sesion);
                salidaDecorada.println("Se agrego con exito la información del intento de Sesion.");            
            break;
            
            case "Consultar Sesiones de un Usuario":
                
                usuario_sesion = parametros[1];
                
                ArrayList<Sesion> lista_sesion_usuario = gestorSesiones.consultarSesionesDeUnUsuario(usuario_sesion);
                if(lista_sesion_usuario.isEmpty())
                {
                    salidaDecorada.println("Aun no se encuentra registrado ningun intento de sesion del usuario: " + usuario_sesion);
                }
                else
                {
                    salidaDecorada.println(objSerializador.serializarSesiones(lista_sesion_usuario));
                }
            break;
            
            case "Consultar todas las Sesiones":
                
                ArrayList<Sesion> listaSesiones = gestorSesiones.consultarTodasLasSesiones();
                
                if(listaSesiones.isEmpty())
                {
                    salidaDecorada.println("Aun no se encuentra ninguna sesión registrada.");
                }
                else
                {
                    salidaDecorada.println(objSerializador.serializarSesiones(listaSesiones));
                }
            break;
            
            case "Consultar Regiones":
                
                ArrayList<Region> listaRegiones = gestorRegiones.consultarRegiones();
                if(listaRegiones.isEmpty())
                    salidaDecorada.println("Aun no existen regiones registradas.");
                else
                    salidaDecorada.println(objSerializador.serializarRegiones(listaRegiones));
            break;
            
            case "Consultar Paises":
                
                String region = parametros[1];
                ArrayList<Region> lista_paises = gestorRegiones.consultarPaises(region);
                if(lista_paises.isEmpty())
                    salidaDecorada.println("Aun no existen paises de esa region registrados.");
                else
                    salidaDecorada.println(objSerializador.serializarRegiones(lista_paises));
            break;
            
            case "Consultar Ciudades":
                
                String pais = parametros[1];
                ArrayList<Region> lista_ciudades = gestorRegiones.consultarCiudades(pais);
                if(lista_ciudades.isEmpty())
                    salidaDecorada.println("Aun no existen ciudades de ese país registradas.");
                else
                    salidaDecorada.println(objSerializador.serializarRegiones(lista_ciudades));
            break;
              
            default:
                salidaDecorada.println("Opción Invalida...");
        }
    }
}
