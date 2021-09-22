package logica_negocio;

/**
 * 
 * @author Alexander Inagan
 */
public class Region {
    
    private String id_region;
    private String nombre_region;

    public Region() {
        super();
    }
    
    public Region(String id_region, String nombre_region) {
        this.id_region = id_region;
        this.nombre_region = nombre_region;
    }
   
    public String getId_region() {
        return id_region;
    }

    public void setId_region(String id_region) {
        this.id_region = id_region;
    }

    public String getNombre_region() {
        return nombre_region;
    }

    public void setNombre_region(String nombre_region) {
        this.nombre_region = nombre_region;
    }

}