package acceso;

import logica_negocio.Usuario;

/**
 *
 * @author Alexander Inagan
 */
public interface IServiciosUsuarios {
    
    public String agregarUsuario(Usuario nuevoUsuario);
    public String editarUsuario(Usuario usuarioEditar);
    public String consultarUsuario(String user_id);
    public String eliminarUsuario(String user_id);
    public String obtenerTodosLosUsuarios();    

}
