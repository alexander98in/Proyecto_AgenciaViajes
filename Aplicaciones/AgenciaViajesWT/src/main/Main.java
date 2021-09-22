package main;

import presentacion.GUIAutenticacionUsers;

/**
 *
 * @author Alexander Inagan
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        GUIAutenticacionUsers autenticacion = new GUIAutenticacionUsers();
        autenticacion.setVisible(true);
        autenticacion.setSize(650, 250);
        autenticacion.setLocationRelativeTo(null);
        autenticacion.setResizable(false);
        
    }  
}
