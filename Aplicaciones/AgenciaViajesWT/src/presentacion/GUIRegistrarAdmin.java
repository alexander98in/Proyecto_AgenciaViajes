package presentacion;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import logica_negocio.GestorUsuarios;
import logica_negocio.Usuario;
import utilidades.Utilidades;

/**
 *
 * @author Alexander Inagan
 */
public class GUIRegistrarAdmin extends javax.swing.JFrame {

    private final GestorUsuarios gestorUsuarios;
    private String contrasenia_usuario = "";
    private String contrasenia_usuario_con = "";
    /**
     * Creates new form GUIRegistrarAdmin
     */
    public GUIRegistrarAdmin() {
        initComponents();
        gestorUsuarios = new GestorUsuarios();
        botonesEstadoInicial();
    }
    
    public String getUsuario_Id() {
        return jtxtUsuario_Id.getText().trim();
    }
    
    public String getNombre_Usuario() {
        return jtxtNombre_Usuario.getText().trim();
    }
    
    public String getContrasenia() {
        String contrasenia = "";
        if(this.contrasenia_usuario.equals(contrasenia_usuario_con))
            contrasenia = contrasenia_usuario;
        return contrasenia;
    }
    
    public String getNombre_Completo() {
        return jtxtNombre_Completo.getText().trim();
    }
    
    public String getFecha_Creacion() {
        Date fecha = new Date();
        DateFormat fecha_formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String fecha_creacion = fecha_formato.format(fecha);
        return fecha_creacion;
    }
    
    public String getCargo() {
        return "ADMINISTRADOR";
    }
    
    private void botonesEstadoInicial() {
        jbtnRegistrar.setEnabled(false);
        jPasswordContraseniaCon.setEditable(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelNorte = new javax.swing.JPanel();
        jLabelTitulo = new javax.swing.JLabel();
        jPanelCentro = new javax.swing.JPanel();
        jLabelUsuario_Id = new javax.swing.JLabel();
        jtxtUsuario_Id = new javax.swing.JTextField();
        jLabelNombre_Usuario = new javax.swing.JLabel();
        jtxtNombre_Usuario = new javax.swing.JTextField();
        jLabelContrasenia = new javax.swing.JLabel();
        jPasswordContrasenia = new javax.swing.JPasswordField();
        jLabelConfirmarContrasenia = new javax.swing.JLabel();
        jPasswordContraseniaCon = new javax.swing.JPasswordField();
        jLabelNombre_Completo = new javax.swing.JLabel();
        jtxtNombre_Completo = new javax.swing.JTextField();
        jPanelSur = new javax.swing.JPanel();
        jbtnRegistrar = new javax.swing.JButton();
        jbtnLimpiar = new javax.swing.JButton();
        jbtnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Registrar Administrador");

        jPanelNorte.setBackground(new java.awt.Color(235, 151, 110));
        jPanelNorte.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Información: \n", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13))); // NOI18N
        jPanelNorte.setLayout(new java.awt.BorderLayout());

        jLabelTitulo.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabelTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitulo.setText("Registrar Administrador");
        jPanelNorte.add(jLabelTitulo, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanelNorte, java.awt.BorderLayout.NORTH);

        jPanelCentro.setBackground(new java.awt.Color(94, 209, 94));
        jPanelCentro.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(22, 64, 22), 2), "Ingrese los datos: ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        jPanelCentro.setLayout(new java.awt.GridLayout(5, 2));

        jLabelUsuario_Id.setFont(new java.awt.Font("Trebuchet MS", 0, 15)); // NOI18N
        jLabelUsuario_Id.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelUsuario_Id.setText("   Identificación (Administrador):  ");
        jLabelUsuario_Id.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelCentro.add(jLabelUsuario_Id);

        jtxtUsuario_Id.setBackground(new java.awt.Color(204, 212, 238));
        jtxtUsuario_Id.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtxtUsuario_IdFocusLost(evt);
            }
        });
        jPanelCentro.add(jtxtUsuario_Id);

        jLabelNombre_Usuario.setFont(new java.awt.Font("Trebuchet MS", 0, 15)); // NOI18N
        jLabelNombre_Usuario.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelNombre_Usuario.setText("Nombre de Usuario:  ");
        jLabelNombre_Usuario.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelCentro.add(jLabelNombre_Usuario);

        jtxtNombre_Usuario.setBackground(new java.awt.Color(204, 212, 238));
        jPanelCentro.add(jtxtNombre_Usuario);

        jLabelContrasenia.setFont(new java.awt.Font("Trebuchet MS", 0, 15)); // NOI18N
        jLabelContrasenia.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelContrasenia.setText("Ingrese la Contraseña:  ");
        jLabelContrasenia.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelCentro.add(jLabelContrasenia);

        jPasswordContrasenia.setBackground(new java.awt.Color(204, 212, 238));
        jPasswordContrasenia.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jPasswordContraseniaFocusLost(evt);
            }
        });
        jPanelCentro.add(jPasswordContrasenia);

        jLabelConfirmarContrasenia.setFont(new java.awt.Font("Trebuchet MS", 0, 15)); // NOI18N
        jLabelConfirmarContrasenia.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelConfirmarContrasenia.setText("Ingrese Nuevamente la Contraseña:  ");
        jLabelConfirmarContrasenia.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelCentro.add(jLabelConfirmarContrasenia);

        jPasswordContraseniaCon.setBackground(new java.awt.Color(204, 212, 238));
        jPasswordContraseniaCon.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jPasswordContraseniaConFocusLost(evt);
            }
        });
        jPanelCentro.add(jPasswordContraseniaCon);

        jLabelNombre_Completo.setFont(new java.awt.Font("Trebuchet MS", 0, 15)); // NOI18N
        jLabelNombre_Completo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelNombre_Completo.setText("Nombre Completo:  ");
        jLabelNombre_Completo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelCentro.add(jLabelNombre_Completo);

        jtxtNombre_Completo.setBackground(new java.awt.Color(204, 212, 238));
        jPanelCentro.add(jtxtNombre_Completo);

        getContentPane().add(jPanelCentro, java.awt.BorderLayout.CENTER);

        jPanelSur.setBackground(new java.awt.Color(246, 204, 183));
        jPanelSur.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)), "Opciones: \n", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13))); // NOI18N

        jbtnRegistrar.setBackground(new java.awt.Color(159, 221, 159));
        jbtnRegistrar.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jbtnRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/agregar.png"))); // NOI18N
        jbtnRegistrar.setText("Registrar");
        jbtnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnRegistrarActionPerformed(evt);
            }
        });
        jPanelSur.add(jbtnRegistrar);

        jbtnLimpiar.setBackground(new java.awt.Color(223, 165, 165));
        jbtnLimpiar.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jbtnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/escoba.png"))); // NOI18N
        jbtnLimpiar.setText("Limpiar");
        jbtnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnLimpiarActionPerformed(evt);
            }
        });
        jPanelSur.add(jbtnLimpiar);

        jbtnCancelar.setBackground(new java.awt.Color(255, 208, 153));
        jbtnCancelar.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jbtnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/cerrar.png"))); // NOI18N
        jbtnCancelar.setText("Cancelar");
        jbtnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnCancelarActionPerformed(evt);
            }
        });
        jPanelSur.add(jbtnCancelar);

        getContentPane().add(jPanelSur, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtxtUsuario_IdFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxtUsuario_IdFocusLost
        String usuario_ID = jtxtUsuario_Id.getText().trim();
        
        if(usuario_ID.equals(""))
        {
            return;
        }
        
        //Busca el Usuario en el servidor.
        Usuario user = null;
        try{
            user = gestorUsuarios.consultar_Usuario(usuario_ID);
        }
        catch(Exception ex) {
            Logger.getLogger(GUIRegistrarAdmin.class.getName()).log(Level.SEVERE, null, ex);
            Utilidades.mensajeError("Error al buscar el Usuario en la base de datos del servidor de la Agencia.", "Advertencia.");
        }
        
        if(user == null) {
            habilitar_campos();
            Utilidades.mensajeExito("No hay ningun problema con el Id del Usuario, proceda a digitar los demas datos.", "Usuario ID");
            jbtnRegistrar.setEnabled(true);
        }
        else
        {
            jtxtUsuario_Id.setText(user.getUsuario_id());
            fijarCamposFormulario(user);
        }
        
    }//GEN-LAST:event_jtxtUsuario_IdFocusLost

    private void jbtnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnLimpiarActionPerformed
        limpiarCampos(true);
    }//GEN-LAST:event_jbtnLimpiarActionPerformed

    private void jbtnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnRegistrarActionPerformed
        //Registra el usuario como Administrador
        Usuario user;
        Usuario nuevo_usuario;
        String confirmacion;
        if(validarFormulario()) 
        {       
            String usaurio_id = getUsuario_Id();
            String nombre_usaurio = getNombre_Usuario();
            String contrasenia = getContrasenia();
            String nombre_completo = getNombre_Completo();
            String fecha_creacion = getFecha_Creacion();
            String cargo = getCargo();
                        
            user = gestorUsuarios.consultar_Usuario(usaurio_id);
            if(user == null) 
            {
                nuevo_usuario = new Usuario(usaurio_id, nombre_usaurio, contrasenia, nombre_completo, fecha_creacion, cargo);
                confirmacion = gestorUsuarios.agregar_Usuario(nuevo_usuario);
                Utilidades.mensajeExito(confirmacion, "Registro Administrador.");
                limpiarCampos(true);
                jtxtUsuario_Id.setText("");
                this.dispose();
            }
            else
            {
                Utilidades.mensajeError("El Administrador con ese Id ya se encuentra registrado.", "Advertencia");
                jtxtUsuario_Id.setText("");
                limpiarCampos(true);
            }
        }      
    }//GEN-LAST:event_jbtnRegistrarActionPerformed

    private void jbtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_jbtnCancelarActionPerformed

    private void jPasswordContraseniaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPasswordContraseniaFocusLost
        String miContrasenia = "";
        char[] password = jPasswordContrasenia.getPassword();
     
        if((password.length >= 5) && (password.length <= 12)) 
        {
            for(int i=0; i<password.length; i++) {
                miContrasenia += password[i];
            }
            boolean password_valida = es_valida_contrasenia(miContrasenia);
            if(password_valida) {
                this.contrasenia_usuario = miContrasenia;
                jPasswordContraseniaCon.setEditable(true);
            }
            else
            {
                Utilidades.mensajeAdvertencia("La contraseña debe tener al menos un caracter númerico y un caracter especial(+, -, *, /, =, %, &, etc).", "password");
                jPasswordContrasenia.setText("");
            }
           
        }
        else
        {
            Utilidades.mensajeAdvertencia("La contraseña debe tener minimo 5 caracteres y maximo 12 caracteres.", "Password");
            jPasswordContrasenia.setText("");
        }
    }//GEN-LAST:event_jPasswordContraseniaFocusLost

    private void jPasswordContraseniaConFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPasswordContraseniaConFocusLost
        String miContrasenia = "";
        String miContraseniaAUX = this.contrasenia_usuario;
        char[] password = jPasswordContraseniaCon.getPassword();
        
        if(password.length > 1)
        {
            if((password.length >= 5) && (password.length <= 12)) 
            {
                for(int i=0; i<password.length; i++) {
                    miContrasenia += password[i];
                }
                boolean password_valida = es_valida_contrasenia(miContrasenia);
                if(password_valida) {

                    boolean son_iguales = son_contrasenias_iguales(miContraseniaAUX, miContrasenia);
                    if(son_iguales) {
                        this.contrasenia_usuario_con = miContrasenia;
                    }
                    else
                    { 
                        Utilidades.mensajeError("Las contraseña no son iguales.", "Password");
                        jPasswordContraseniaCon.setText("");
                    }
                }
                else
                {
                    Utilidades.mensajeError("La contraseña debe tener al menos un caracter númerico y un caracter especial(+, -, *, /, =, %, &, etc).", "password");
                    jPasswordContraseniaCon.setText("");
                }
            }
            else
            {
                Utilidades.mensajeError("La contraseña debe tener minimo 5 caracteres y maximo 12 caracteres.", "Password");
                jPasswordContraseniaCon.setText("");
            }
        }
    }//GEN-LAST:event_jPasswordContraseniaConFocusLost

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelConfirmarContrasenia;
    private javax.swing.JLabel jLabelContrasenia;
    private javax.swing.JLabel jLabelNombre_Completo;
    private javax.swing.JLabel jLabelNombre_Usuario;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JLabel jLabelUsuario_Id;
    private javax.swing.JPanel jPanelCentro;
    private javax.swing.JPanel jPanelNorte;
    private javax.swing.JPanel jPanelSur;
    private javax.swing.JPasswordField jPasswordContrasenia;
    private javax.swing.JPasswordField jPasswordContraseniaCon;
    private javax.swing.JButton jbtnCancelar;
    private javax.swing.JButton jbtnLimpiar;
    private javax.swing.JButton jbtnRegistrar;
    private javax.swing.JTextField jtxtNombre_Completo;
    private javax.swing.JTextField jtxtNombre_Usuario;
    private javax.swing.JTextField jtxtUsuario_Id;
    // End of variables declaration//GEN-END:variables

    
    /**
     * Valida que los datos estén correctamente diligenciados en el formulario
     * @return true si están bien diligenciados, false en caso contrario
     */
    private boolean validarFormulario() {
        
        if(this.getUsuario_Id().equals(""))
        {
            Utilidades.mensajeAdvertencia("Debe agregar la identificación del Administrador.", "Atención");
            jtxtUsuario_Id.requestFocus();
            return false;
        }
        
        if(this.getNombre_Usuario().equals(""))
        {
            Utilidades.mensajeAdvertencia("Debe agregar un nombre de usuario.", "Advertencia");
            jtxtNombre_Usuario.requestFocus();
            return false;
        }
        
        if(this.getContrasenia().equals(""))
        {
            Utilidades.mensajeAdvertencia("Debe digitar la contraseña.", "Advertencia");
            jPasswordContrasenia.setText("");
            jPasswordContraseniaCon.setText("");
            jPasswordContraseniaCon.setEditable(false);
            jPasswordContrasenia.requestFocus();
            return false;
        }
        
        if(this.getNombre_Completo().equals(""))
        {
            Utilidades.mensajeAdvertencia("Debe digitar el nombre completo.", "Advertencia");
            jtxtNombre_Completo.requestFocus();
            return false;
        }
        
        return true;
    }

    /**
     * Fija los campos de texto con los datos del Usuario
     * @param usuario 
     */
    private void fijarCamposFormulario(Usuario usuario) 
    {
        jtxtNombre_Usuario.setText(usuario.getNombre_usuario());
        jPasswordContrasenia.setText(usuario.getContrasenia());
        jPasswordContraseniaCon.setText(usuario.getContrasenia());
        jtxtNombre_Completo.setText(usuario.getNombre_completo());  
        desabilitar_campos();
    }
    
    /**
     * Vuelve los campos no editables.
     */
    private void desabilitar_campos()
    {
        jtxtNombre_Usuario.setEditable(false);
        jPasswordContrasenia.setEditable(false);
        jPasswordContraseniaCon.setEditable(false);
        jtxtNombre_Completo.setEditable(false);
    }
    
    /**
     * Habilita los campos para editar.
     */
    private void habilitar_campos() 
    {
        jtxtNombre_Usuario.setEditable(true);
        jPasswordContrasenia.setEditable(true);
        jtxtNombre_Completo.setEditable(true);  
    }
    
    /**
     * Limpia los campos de texto.
     * @param limpiar 
     */
    private void limpiarCampos(boolean limpiar) {
        
        if(limpiar)
        {
            jtxtUsuario_Id.setText("");
            jtxtNombre_Usuario.setText("");
            jPasswordContrasenia.setText("");
            jPasswordContraseniaCon.setText("");
            jtxtNombre_Completo.setText("");
        }
    }
    
    /**
     * Verifica que la contraseña sea valida, es decir que tenga como minimo un caracte numerico y un caracter especial.
     * @param contrasenia
     * @return bolean 
     */
    private boolean es_valida_contrasenia(String contrasenia) {
        
        boolean es_valida = false;
        char[] mi_password = contrasenia.toCharArray();
           
        char[] digitos = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};
        char[] carac_especiales = {'+', '-', '*', '/', '=', '_', '%', '@', '&', '#', '!', '?', '^', '~', '|', ':', ';' ,'.', ','};
        int contador_digitos = 0;
        int contador_caracteres = 0;
            
        for(int i=0; i<mi_password.length; i++) {
            for(int j=0; j<digitos.length; j++) {
                if(mi_password[i] == digitos[j]) {
                       contador_digitos++;
                }
            }
        }
            
        for(int i=0; i<mi_password.length; i++) {
            for(int j=0; j<carac_especiales.length; j++) {
                if(mi_password[i] == carac_especiales[j]) {
                    contador_caracteres++;
                }
            }
        }
           
        if(contador_digitos > 0 && contador_caracteres > 0)
        {
            System.out.print("La contraseña es valida...\n");
            es_valida = true;
        }  
        return es_valida;
    }
    
    /**
     * Verifica si dos contraseña son iguales.
     * @param contrasenia1
     * @param contrasenia2
     * @return boolean
     */
    private boolean son_contrasenias_iguales(String contrasenia1, String contrasenia2)
    {
        boolean son_iguales = false;
        if(contrasenia1.equals(contrasenia2)) {
            son_iguales = true;
        }
        return son_iguales;
    }
}
