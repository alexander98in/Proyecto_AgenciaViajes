package logica_negocio;

/**
 * 
 * @author Alexander Inagan
 */
public class Cliente {
    
    private String doc_id;
    private String nombres;
    private String apellidos;
    private String fecha_nac;
    private String email;
    private String genero;
    private String direccion;
    private String ciudad;
    private String celular;
    
    public Cliente() {
        super();
    }

    public Cliente(String doc_id, String nombres, String apellidos, String fecha_nac, String email, String genero, String direccion, String ciudad, String celular) {
        this.doc_id = doc_id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fecha_nac = fecha_nac;
        this.email = email;
        this.genero = genero;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.celular = celular;
    }

    public String getDoc_id() {
        return doc_id;
    }

    public void setDoc_id(String doc_id) {
        this.doc_id = doc_id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getFecha_nac() {
        return fecha_nac;
    }

    public void setFecha_nac(String fecha_nac) {
        this.fecha_nac = fecha_nac;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

}