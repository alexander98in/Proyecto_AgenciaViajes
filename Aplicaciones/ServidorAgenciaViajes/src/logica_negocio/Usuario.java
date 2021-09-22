package logica_negocio;

/**
 * 
 * @author Alexander Inagan
 */
public class Usuario {
    
    private String usuario_id;
    private String nombre_usuario;
    private String contrasenia;
    private String nombre_completo;
    private String fecha_creacion;
    private String cargo;
    
    public Usuario() {
        super();
    }

    public Usuario(String usuario_id, String nombre_usuario, String contrasenia, String nombre_completo, String fecha_creacion, String cargo)
    {
        this.usuario_id = usuario_id;
        this.nombre_usuario = nombre_usuario;
        this.contrasenia = contrasenia;
        this.nombre_completo = nombre_completo;
        this.fecha_creacion = fecha_creacion;
        this.cargo = cargo;
    }

    public String getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(String usuario_id) {
        this.usuario_id = usuario_id;
    }
    
    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getNombre_completo() {
        return nombre_completo;
    }

    public void setNombre_completo(String nombre_completo) {
        this.nombre_completo = nombre_completo;
    }

    public String getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(String fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}
