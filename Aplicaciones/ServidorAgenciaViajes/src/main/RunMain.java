package main;

import servicio.ServidorAgenciaViajes;

/**
 * 
 * @author Alexander Inagan
 */
public class RunMain {
    
    public static void main(String args[]) {
        
        ServidorAgenciaViajes servidor = new ServidorAgenciaViajes();
        servidor.iniciar();
    }
}