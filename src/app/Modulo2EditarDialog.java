/*------------------------------------------------------------------------------------------
:*                         TECNOLOGICO NACIONAL DE MEXICO
:*                               CAMPUS LA LAGUNA
:*                     INGENIERIA EN SISTEMAS COMPUTACIONALES
:*                       TOPICOS AVANZADOS DE PROGRAMACION "B"
:*
:*                   SEMESTRE: ENE-JUN/2022    HORA: 17-18 HRS
:*
:*          Clase que muestra una GUI para hacer cambios en la tabla OPERACIONES
:*        
:*  Archivo     : Modulo2EditarDialog.java
:*  Autor(es)   : Efrain Montalvo Sánchez           20130810
:*              : Aranza Abigail Gassós Salazar     19130889
:*              : Tomás Alejandro Galván Gándara    20130770
:*  Fecha       : 08/May/2022
:*  Compilador  :  
:*  Descripción : Clase que muestra una Interfaz Gráfica de Usuario (GUI) 
:*                en el que se le permite al usuario acceder a los campos
:*                de la tabla OPERACIONES de la Base de Datos, permitiéndole
:*                crear nueva información y/o editarla a gusto del usuario.
:*
:*  Ultima modif: 08/May/2022
:*  Fecha        Modifico                 Motivo
:*========================================================================================== 
:*   
:*------------------------------------------------------------------------------------------*/


package app;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.Modelo1;
import mx.tecnm.util.Imagenes;
import modelo.Modelo2;
import modelo.Modelo3;

    //--------------------------------------------------------------------------

public class Modulo2EditarDialog extends javax.swing.JDialog {

    private PrincipalFrame    frmPrincipal;
    private Modelo2           modelo;
    private String            accion;
    private Vector<String>    vecTiposColumnas;
    private String            sql;

    //--------------------------------------------------------------------------
    
    public Modulo2EditarDialog(java.awt.Frame parent, Modelo2 modelo ) {
        super ( parent, true );
        initComponents();
        
        frmPrincipal     = (PrincipalFrame) parent;
        this.modelo      = modelo;
        vecTiposColumnas = frmPrincipal.getVecTiposColumnas ();
        
        accion = ( modelo == null )? PrincipalFrame.NUEVO : PrincipalFrame.EDITAR;
        setTitle ( accion );
        
        jlblLogo.setIcon ( Imagenes.escalarImagen ( jlblLogo.getIcon (), 105, 105 ) );
        inicializarFormulario ();
        
        llenarCombo1DesdeTabla ();
        llenarCombo2DesdeTabla ();
        inicializarFormulario  (); 
        
    }
    
    //--------------------------------------------------------------------------
    
    private void llenarCombo1DesdeTabla () {
        ResultSet rs;
        Modelo1   modelo1;
        
        // Llenado del comboBox de camiones
        sql = frmPrincipal.getPropSentenciasSQL ()
                          .getProperty ( PrincipalFrame.CAMIONES_TODOS_POR_IDCAMION );
        try {
            rs = EjecutorSQL.sqlQuery ( sql );
            
            
            jcboidcamion.addItem ( new Modelo1 ( 0, "", "--Seleccione--", 0.0f ) );
            while ( rs.next () ) {
                
                int idcamion        = rs.getInt    ( "idcamion"     );
                String codigo       = rs.getString ( "codigo"       );
                String descripcion  = rs.getString ( "descripcion"  );
                                
                modelo1 = new Modelo1 ( idcamion, codigo, descripcion, 0.0f );
                jcboidcamion.addItem  (modelo1);
                
                if ( modelo != null    &&
                     idcamion == ( modelo.getIDCAMION()) ) {
                    int index = jcboidcamion.getModel ().getSize();
                    jcboidcamion.setSelectedIndex ( index - 1 );
                }
            }
            rs.close ();
            
        } catch ( SQLException ex ) {
            dialogoMensaje ( ex.toString () );             
        }
    }
    
    //--------------------------------------------------------------------------
    
    private void llenarCombo2DesdeTabla () {
        ResultSet rs;
        Modelo3   modelo3;
        
        // Llenado del comboBox de formulas
        sql = frmPrincipal.getPropSentenciasSQL ()
                          .getProperty ( PrincipalFrame.FORMULAS_TODOS_POR_IDFORMULA );
        try {
            rs = EjecutorSQL.sqlQuery ( sql );
            
            jcboidformula.addItem ( new Modelo3 ( 0, "", "--Seleccione--", 0, 0, 0 ) );
            while ( rs.next () ) {
                
                int    idformula        = rs.getInt    ( "idformula"   );
                String codigo           = rs.getString ( "codigo"      );
                String descripcion      = rs.getString ( "descripcion" );
                
                modelo3 = new Modelo3 ( idformula, codigo, descripcion, 0, 0, 0 );
                jcboidformula.addItem ( modelo3 );
                
                if ( modelo != null    &&
                     idformula == ( modelo.getIDFORMULA()) ) {
                    int index = jcboidformula.getModel ().getSize();
                    jcboidformula.setSelectedIndex ( index - 1 );
                }                
            }
            rs.close ();
            
        } catch ( SQLException ex ) {
            dialogoMensaje ( ex.toString () );             
        }        
    }
    
    //--------------------------------------------------------------------------
    
    private void inicializarFormulario () {
        if        ( accion.equals ( PrincipalFrame.NUEVO ) ) {
            jtxtIdOperacion.requestFocus ();
        } else if ( accion.equals ( PrincipalFrame.EDITAR ) ) {
            jtxtIdOperacion.setText       ( String.valueOf(modelo.getIDOPERACION()) );
            jtxtFecha.setText             ( modelo.getFECHA() );
            jtxtHora.setText              ( modelo.getHORA()  );
            jcboidcamion.setSelectedItem  ( modelo.getIDCAMION() );
            jcboidformula.setSelectedItem ( modelo.getIDFORMULA());
            jtxtTeorico_Ingr_1.setText    ( String.valueOf(modelo.getTEORICO_INGR_1()) );
            jtxtTeorico_Ingr_2.setText    ( String.valueOf(modelo.getTEORICO_INGR_2()) );
            jtxtTeorico_Ingr_3.setText    ( String.valueOf(modelo.getTEORICO_INGR_3()) );
            jtxtReal_Ingr_1.setText       ( String.valueOf(modelo.getREAL_INGR_1()) );
            jtxtReal_Ingr_2.setText       ( String.valueOf(modelo.getREAL_INGR_2()) );
            jtxtReal_Ingr_3.setText       ( String.valueOf(modelo.getREAL_INGR_3()) );
            
            jtxtFecha.requestFocus     ();
            jtxtIdOperacion.setEditable    ( false );
        }
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jtxtIdOperacion = new javax.swing.JTextField();
        jtxtFecha = new javax.swing.JTextField();
        jlblLogo = new javax.swing.JLabel();
        jbtnGuardar = new javax.swing.JButton();
        jbtnCancelar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jtxtHora = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jtxtTeorico_Ingr_1 = new javax.swing.JTextField();
        jtxtTeorico_Ingr_2 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jtxtTeorico_Ingr_3 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jtxtReal_Ingr_1 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jtxtReal_Ingr_2 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jtxtReal_Ingr_3 = new javax.swing.JTextField();
        jcboidcamion = new javax.swing.JComboBox<>();
        jcboidformula = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("ID Operacion:");

        jLabel2.setText("Fecha");

        jlblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/btnModulo2.jpg"))); // NOI18N
        jlblLogo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jbtnGuardar.setText("Guardar");
        jbtnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnGuardarActionPerformed(evt);
            }
        });

        jbtnCancelar.setText("Cancelar");
        jbtnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnCancelarActionPerformed(evt);
            }
        });

        jLabel3.setText("Hora");

        jLabel5.setText("ID Formula");

        jLabel6.setText("ID Camion");

        jLabel7.setText("Teo.Ingr.2");

        jLabel8.setText("Teo.Ingr.1");

        jLabel9.setText("Teo.Ingr.3");

        jLabel10.setText("Real.Ingr.1");

        jLabel11.setText("Real.Ingr.2");

        jLabel12.setText("Real.Ingr.3");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(130, 130, 130)
                .addComponent(jbtnGuardar)
                .addGap(97, 97, 97)
                .addComponent(jbtnCancelar)
                .addContainerGap(47, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7)
                            .addComponent(jLabel9)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jtxtReal_Ingr_2)
                            .addComponent(jtxtReal_Ingr_1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtxtTeorico_Ingr_3)
                            .addComponent(jtxtTeorico_Ingr_2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtxtTeorico_Ingr_1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jcboidcamion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jtxtHora)
                            .addComponent(jtxtIdOperacion, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtxtFecha, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jcboidformula, 0, 150, Short.MAX_VALUE)
                            .addComponent(jtxtReal_Ingr_3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jlblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jcboidformula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jtxtIdOperacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(13, 13, 13)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jtxtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jtxtHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jcboidcamion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel8))
                    .addComponent(jtxtTeorico_Ingr_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtxtTeorico_Ingr_2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtxtTeorico_Ingr_3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel10))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jtxtReal_Ingr_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtxtReal_Ingr_2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtxtReal_Ingr_3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnGuardar)
                    .addComponent(jbtnCancelar))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    //--------------------------------------------------------------------------
    
    private void jbtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCancelarActionPerformed
        dispose ();
    }//GEN-LAST:event_jbtnCancelarActionPerformed

    //--------------------------------------------------------------------------
    
    private void jbtnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnGuardarActionPerformed
        Object [][] args = null;
        String mensaje   = "";
        
        if ( validarDatos () == false ) 
            return;
        
        // Se determina la sentencia SQL a ejecutar y formar la matriz de argumentos
        if        ( accion.equals ( PrincipalFrame.NUEVO ) ) {
          mensaje  = "El registro ha sido agregado.";
          sql = frmPrincipal.getPropSentenciasSQL().getProperty (
                              PrincipalFrame.OPERACIONES_INSERTA_NUEVO );
          args = new Object [][] {
                   { vecTiposColumnas.elementAt ( 0 ), modelo.getIDOPERACION() },
                   { vecTiposColumnas.elementAt ( 1 ), modelo.getFECHA() }, 
                   { vecTiposColumnas.elementAt ( 2 ), modelo.getHORA() },
                   { vecTiposColumnas.elementAt ( 3 ), modelo.getIDFORMULA() },
                   { vecTiposColumnas.elementAt ( 4 ), modelo.getIDCAMION() },
                   { vecTiposColumnas.elementAt ( 5 ), modelo.getTEORICO_INGR_1() },
                   { vecTiposColumnas.elementAt ( 6 ), modelo.getTEORICO_INGR_2() },
                   { vecTiposColumnas.elementAt ( 7 ), modelo.getTEORICO_INGR_3() },
                   { vecTiposColumnas.elementAt ( 8 ), modelo.getREAL_INGR_1() },
                   { vecTiposColumnas.elementAt ( 9 ), modelo.getREAL_INGR_2() },
                   { vecTiposColumnas.elementAt ( 10 ), modelo.getREAL_INGR_3() }
                };
                    
        } else if ( accion.equals ( PrincipalFrame.EDITAR ) ) {
          mensaje  = "El registro ha sido actualizado.";
          sql = frmPrincipal.getPropSentenciasSQL().getProperty (
                              PrincipalFrame.OPERACIONES_ACTUALIZA_DATOS );
          args = new Object [][] {
                   { vecTiposColumnas.elementAt ( 0 ), modelo.getIDOPERACION() },
                   { vecTiposColumnas.elementAt ( 1 ), modelo.getFECHA() }, 
                   { vecTiposColumnas.elementAt ( 2 ), modelo.getHORA() },
                   { vecTiposColumnas.elementAt ( 3 ), modelo.getIDFORMULA() },
                   { vecTiposColumnas.elementAt ( 4 ), modelo.getIDCAMION() },
                   { vecTiposColumnas.elementAt ( 5 ), modelo.getTEORICO_INGR_1() },
                   { vecTiposColumnas.elementAt ( 6 ), modelo.getTEORICO_INGR_2() },
                   { vecTiposColumnas.elementAt ( 7 ), modelo.getTEORICO_INGR_3() },
                   { vecTiposColumnas.elementAt ( 8 ), modelo.getREAL_INGR_1() },
                   { vecTiposColumnas.elementAt ( 9 ), modelo.getREAL_INGR_2() },
                   { vecTiposColumnas.elementAt ( 10 ), modelo.getREAL_INGR_3() },
                   { vecTiposColumnas.elementAt ( 0 ), modelo.getIDOPERACION() }
                };            
        }
        
        try {
            int regs = EjecutorSQL.sqlEjecutar ( sql, args );
            if ( regs == 1 ) {
                frmPrincipal.getJbtnModulo2 ().doClick();
                JOptionPane.showMessageDialog ( 
                        this,
                        mensaje,
                        accion,
                        JOptionPane.INFORMATION_MESSAGE );
            }
        } catch ( SQLIntegrityConstraintViolationException ex ) {
            dialogoMensaje ( "Ya existe un registro con el mismo ID" );
        } catch ( SQLException ex ) {
            dialogoMensaje ( ex.toString () );
        }
        dispose ();
    }//GEN-LAST:event_jbtnGuardarActionPerformed

    //--------------------------------------------------------------------------
    
    private boolean validarDatos () {
       
         Modelo1 modelo1  = ( Modelo1 ) jcboidcamion.getSelectedItem ();
        int idcamion = modelo1.getIDCAMION();
        String codigoC             = modelo1.getCODIGO();
        if ( codigoC.equals ( "--Seleccione--" ) ) {
            dialogoMensaje ( "Debe seleccionar un elemento de la lista" );
            jcboidcamion.requestFocus ();
            return false;
        }
        
        Modelo3 modelo3  = ( Modelo3 ) jcboidformula.getSelectedItem ();
        int idformula = modelo3.getIDFORMULA();
        String codigoF             = modelo3.getCODIGO();
        if ( codigoF.equals ( "--Seleccione--" ) ) {
            dialogoMensaje ( "Debe seleccionar un elemento de la lista" );
            jcboidformula.requestFocus ();
            return false;
        }        
        
        int idoperacion = 0;
        try {
            idoperacion      = Integer.parseInt ( jtxtIdOperacion.getText () );
        } catch ( NumberFormatException ex ) {
            dialogoMensaje ( "Debe capturar un valor numerico valido" );
            jtxtIdOperacion.requestFocus ();
            return false;
        }
        
         String fecha = jtxtFecha.getText ();
        if ( fecha.trim ().equals ( "" ) ) {
            dialogoMensaje ( "No se permite un valor en blanco" );
            jtxtFecha.requestFocus ();
            return false;
        }
        
        String hora = jtxtHora.getText ();
        if ( hora.trim ().equals ( "" ) ) {
            dialogoMensaje ( "No se permite un valor en blanco" );
            jtxtHora.requestFocus ();
            return false;
        }
        
        float teorico_ingr_1 = 0.0f;
        try {
            teorico_ingr_1      = Float.parseFloat ( jtxtTeorico_Ingr_1.getText () );
        } catch ( NumberFormatException ex ) {
            dialogoMensaje ( "Debe capturar un valor numerico valido" );
            jtxtTeorico_Ingr_1.requestFocus ();
            return false;
        }
        
        float teorico_ingr_2 = 0.0f;
        try {
            teorico_ingr_2      = Float.parseFloat ( jtxtTeorico_Ingr_2.getText () );
        } catch ( NumberFormatException ex ) {
            dialogoMensaje ( "Debe capturar un valor numerico valido" );
            jtxtTeorico_Ingr_2.requestFocus ();
            return false;
        }
        
        float teorico_ingr_3 = 0.0f;
        try {
            teorico_ingr_3      = Float.parseFloat ( jtxtTeorico_Ingr_3.getText () );
        } catch ( NumberFormatException ex ) {
            dialogoMensaje ( "Debe capturar un valor numerico valido" );
            jtxtTeorico_Ingr_3.requestFocus ();
            return false;
        }
        
        float real_ingr_1 = 0.0f;
        try {
            real_ingr_1      = Float.parseFloat ( jtxtReal_Ingr_1.getText () );
        } catch ( NumberFormatException ex ) {
            dialogoMensaje ( "Debe capturar un valor numerico valido" );
            jtxtReal_Ingr_1.requestFocus ();
            return false;
        }
        
        float real_ingr_2 = 0.0f;
        try {
            real_ingr_2     = Float.parseFloat ( jtxtReal_Ingr_2.getText () );
        } catch ( NumberFormatException ex ) {
            dialogoMensaje ( "Debe capturar un valor numerico valido" );
            jtxtReal_Ingr_2.requestFocus ();
            return false;
        }
        
        float real_ingr_3 = 0.0f;
        try {
            real_ingr_3      = Float.parseFloat(jtxtReal_Ingr_3.getText () );
        } catch ( NumberFormatException ex ) {
            dialogoMensaje ( "Debe capturar un valor numerico valido" );
            jtxtReal_Ingr_3.requestFocus ();
            return false;
        }
        
        modelo = new Modelo2 ( idoperacion, fecha, hora, idformula, idcamion, 
        teorico_ingr_1, teorico_ingr_2, teorico_ingr_3, real_ingr_1, real_ingr_2,
        real_ingr_3);
        return true;
    }
    
    //--------------------------------------------------------------------------
    
    private void dialogoMensaje ( String mensaje  ) {
        JOptionPane.showMessageDialog ( this, mensaje, "Error", JOptionPane.ERROR_MESSAGE );
    }    
    
    //--------------------------------------------------------------------------
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Modulo2EditarDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Modulo2EditarDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Modulo2EditarDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Modulo2EditarDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Modulo2EditarDialog dialog = new Modulo2EditarDialog(new javax.swing.JFrame(), null );
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JButton jbtnCancelar;
    private javax.swing.JButton jbtnGuardar;
    private javax.swing.JComboBox<Modelo1> jcboidcamion;
    private javax.swing.JComboBox<Modelo3> jcboidformula;
    private javax.swing.JLabel jlblLogo;
    private javax.swing.JTextField jtxtFecha;
    private javax.swing.JTextField jtxtHora;
    private javax.swing.JTextField jtxtIdOperacion;
    private javax.swing.JTextField jtxtReal_Ingr_1;
    private javax.swing.JTextField jtxtReal_Ingr_2;
    private javax.swing.JTextField jtxtReal_Ingr_3;
    private javax.swing.JTextField jtxtTeorico_Ingr_1;
    private javax.swing.JTextField jtxtTeorico_Ingr_2;
    private javax.swing.JTextField jtxtTeorico_Ingr_3;
    // End of variables declaration//GEN-END:variables
}
