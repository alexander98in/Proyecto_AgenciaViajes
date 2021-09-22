package acceso;

import logica_negocio.Cliente;

/**
 *
 * @author Alexander Inagan
 */
public interface IServiciosClientes {
    
    public String agregarCliente(Cliente nuevoCliente);
    public String editarCliente(Cliente clienteEditar);
    public String consultarCliente(String doc_id);
    public String eliminarCliente(String doc_id);
    public String obtenerTodosLosClientes();
    
}