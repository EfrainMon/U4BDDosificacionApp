/*------------------------------------------------------------------------------------------
:*                         TECNOLOGICO NACIONAL DE MEXICO
:*                               CAMPUS LA LAGUNA
:*                     INGENIERIA EN SISTEMAS COMPUTACIONALES
:*                       TOPICOS AVANZADOS DE PROGRAMACION "B"
:*
:*                   SEMESTRE: ENE-JUN/2022    HORA: 17-18 HRS
:*
:*           Clase que muestra una GUI para hacer cambios en la tabla CAMIONES
:*        
:*  Archivo     : Modulo1EditarDialog.java
:*  Autor(es)   : Efrain Montalvo Sánchez           20130810
:*              : Aranza Abigail Gassós Salazar     19130889
:*              : Tomás Alejandro Galván Gándara    20130770
:*  Fecha       : 08/May/2022
:*  Compilador  :  
:*  Descripción : Clase que muestra una Interfaz Gráfica de Usuario (GUI) 
:*                en el que se le permite al usuario acceder a los campos
:*                de la tabla CAMIONES de la Base de Datos, permitiéndole
:*                crear nueva información y/o editarla a gusto del usuario.
:*
:*  Ultima modif: 08/May/2022
:*  Fecha        Modifico                 Motivo
:*========================================================================================== 
:*   
:*------------------------------------------------------------------------------------------*/


package app;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import mx.tecnm.util.Imagenes;
import modelo.Modelo1;


public class Modulo1EditarDialog extends javax.swing.JDialog {

    private PrincipalFrame    frmPrincipal;
    private Modelo1           modelo;
    private String            accion;
    private Vector<String>    vecTiposColumnas;
    private String            sql;

    //--------------------------------------------------------------------------
    
    public Modulo1EditarDialog(java.awt.Frame parent, Modelo1 modelo ) {
        super ( parent, true );
        initComponents();
        
        frmPrincipal     = ( PrincipalFrame ) parent;
        this.modelo      = modelo;
        vecTiposColumnas = frmPrincipal.getVecTiposColumnas ();
        
        accion = ( modelo == null )? PrincipalFrame.NUEVO : PrincipalFrame.EDITAR;
        setTitle ( accion );
        
        jlblLogo.setIcon ( Imagenes.escalarImagen ( jlblLogo.getIcon (), 105, 105 ) );
        inicializarFormulario ();
    }
    
    //--------------------------------------------------------------------------
    
    private void inicializarFormulario () {
        if        ( accion.equals ( PrincipalFrame.NUEVO ) ) {
            jtxtIDCamion.requestFocus ();
        } else if ( accion.equals ( PrincipalFrame.EDITAR ) ) {
            jtxtIDCamion.setText    (String.valueOf(modelo.getIDCAMION()) );
            jtxtCodigo.setText      ( modelo.getCODIGO());
            jtxtDescripcion.setText ( modelo.getDESCRIPCION());
            jtxtCapacidad.setText  ( modelo.getCAPACIDAD()+ "" );
            
            jtxtCodigo.requestFocus   ();
            jtxtIDCamion.setEditable ( false );
        }
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jtxtIDCamion = new javax.swing.JTextField();
        jtxtCodigo = new javax.swing.JTextField();
        jtxtDescripcion = new javax.swing.JTextField();
        jlblLogo = new javax.swing.JLabel();
        jbtnGuardar = new javax.swing.JButton();
        jbtnCancelar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jtxtCapacidad = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("ID Camion");

        jLabel2.setText("Codigo");

        jLabel3.setText("Descripcion");

        jlblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/btnModulo1.jpg"))); // NOI18N
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

        jLabel6.setText("Capacidad");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jtxtDescripcion, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                            .addComponent(jtxtCodigo)
                            .addComponent(jtxtIDCamion)
                            .addComponent(jtxtCapacidad))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jlblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(130, 130, 130)
                .addComponent(jbtnGuardar)
                .addGap(97, 97, 97)
                .addComponent(jbtnCancelar)
                .addContainerGap(149, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jtxtIDCamion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(13, 13, 13)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jtxtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jtxtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jtxtCapacidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jlblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
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
                              PrincipalFrame.CAMIONES_INSERTA_NUEVO );
          args = new Object [][] {
                   { vecTiposColumnas.elementAt ( 0 ), modelo.getIDCAMION()},
                   { vecTiposColumnas.elementAt ( 1 ), modelo.getCODIGO()}, 
                   { vecTiposColumnas.elementAt ( 2 ), modelo.getDESCRIPCION()},
                   { vecTiposColumnas.elementAt ( 3 ), modelo.getCAPACIDAD()}
                };
                    
        } else if ( accion.equals ( PrincipalFrame.EDITAR ) ) {
          mensaje  = "El registro ha sido actualizado.";
          sql = frmPrincipal.getPropSentenciasSQL().getProperty (
                              PrincipalFrame.CAMIONES_ACTUALIZA_DATOS );
          args = new Object [][] {
                   { vecTiposColumnas.elementAt ( 0 ), modelo.getIDCAMION() },
                   { vecTiposColumnas.elementAt ( 1 ), modelo.getCODIGO() }, 
                   { vecTiposColumnas.elementAt ( 2 ), modelo.getDESCRIPCION() },
                   { vecTiposColumnas.elementAt ( 3 ), modelo.getCAPACIDAD() },                   
                   { vecTiposColumnas.elementAt ( 0 ), modelo.getIDCAMION() }                   
                };            
        }
        
        try {
            int regs = EjecutorSQL.sqlEjecutar ( sql, args );
            if ( regs == 1 ) {
                frmPrincipal.getJbtnModulo1 ().doClick();
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
    
        int idcamion = 0;
        try {
            idcamion      = Integer.parseInt ( jtxtIDCamion.getText () );
        } catch ( NumberFormatException ex ) {
            dialogoMensaje ( "Debe capturar un valor numerico valido" );
            jtxtIDCamion.requestFocus ();
            return false;
        }
        
        String codigo    = jtxtCodigo.getText    ();
        if ( codigo.trim ().equals ( "" ) ) {
            dialogoMensaje ( "No se permite un valor en blanco" );
            jtxtCodigo.requestFocus ();
            return false;
        }
        
        String descripcion    = jtxtDescripcion.getText    ();
        if ( descripcion.trim ().equals ( "" ) ) {
            dialogoMensaje ( "No se permite un valor en blanco" );
            jtxtDescripcion.requestFocus ();
            return false;
        }
        float capacidad;
        try {
            capacidad = Float.parseFloat ( jtxtCapacidad.getText () );
        } catch ( NumberFormatException ex ) {
            dialogoMensaje ( "Debe capturar un valor numerico valido" );
            jtxtCapacidad.requestFocus ();
            return false;
        } 
                    

        
        modelo = new Modelo1 ( idcamion, codigo, descripcion, capacidad );
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
            java.util.logging.Logger.getLogger(Modulo1EditarDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Modulo1EditarDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Modulo1EditarDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Modulo1EditarDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Modulo1EditarDialog dialog = new Modulo1EditarDialog(new javax.swing.JFrame(), null );
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JButton jbtnCancelar;
    private javax.swing.JButton jbtnGuardar;
    private javax.swing.JLabel jlblLogo;
    private javax.swing.JTextField jtxtCapacidad;
    private javax.swing.JTextField jtxtCodigo;
    private javax.swing.JTextField jtxtDescripcion;
    private javax.swing.JTextField jtxtIDCamion;
    // End of variables declaration//GEN-END:variables
}
