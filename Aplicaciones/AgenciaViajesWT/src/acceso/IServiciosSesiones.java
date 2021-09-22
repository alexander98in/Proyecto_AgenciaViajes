package acceso;

import logica_negocio.Sesion;

/**
 *
 * @author Alexander Inagan
 */
public interface IServiciosSesiones {
    
    public String agregarSesion(Sesion sesion_agregar);
    public String obtenerSesionesDeUnUsuario(String user_name);
    public String obtenerTodasLasSesiones();

}
