package logica_negocio;

/**
 * 
 * @author Alexander Inagan
 */
public class Sesion {
    
    private String usuario;
    private String contrasenia;
    private String fecha_hora;
    private String ingreso_valido;
    
    public Sesion() {
        super();
    }

    public Sesion(String usuario, String contrasenia, String fecha_hora, String ingreso_valido) {
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.fecha_hora = fecha_hora;
        this.ingreso_valido = ingreso_valido;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getFecha_hora() {
        return fecha_hora;
    }

    public void setFecha_hora(String fecha_hora) {
        this.fecha_hora = fecha_hora;
    }

    public String getIngreso_valido() {
        return ingreso_valido;
    }

    public void setIngreso_valido(String ingreso_valido) {
        this.ingreso_valido = ingreso_valido;
    }
  
}