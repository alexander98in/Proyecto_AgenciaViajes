package acceso;

/**
 *
 * @author Alexander Inagan
 */
public interface IServiciosRegiones {
    
    public String consultarRegiones();
    public String consultarPaises(String nombre_region);
    public String consultarCiudades(String nombre_ciudad);
    
}
