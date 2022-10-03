/*------------------------------------------------------------------------------------------
:*                         TECNOLOGICO NACIONAL DE MEXICO
:*                               CAMPUS LA LAGUNA
:*                     INGENIERIA EN SISTEMAS COMPUTACIONALES
:*                       TOPICOS AVANZADOS DE PROGRAMACION "B"
:*
:*                   SEMESTRE: ENE-JUN/2022    HORA: 17-18 HRS
:*
:*           Clase que muestra una GUI para hacer cambios en la tabla FORMULAS
:*        
:*  Archivo     : Modulo3EditarDialog.java
:*  Autor(es)   : Efrain Montalvo Sánchez           20130810
:*              : Aranza Abigail Gassós Salazar     19130889
:*              : Tomás Alejandro Galván Gándara    20130770
:*  Fecha       : 08/May/2022
:*  Compilador  :  
:*  Descripción : Clase que muestra una Interfaz Gráfica de Usuario (GUI) 
:*                en el que se le permite al usuario acceder a los campos
:*                de la tabla FORMULAS de la Base de Datos, permitiéndole
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
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.*;
import mx.tecnm.util.Imagenes;

    //--------------------------------------------------------------------------

public class Modulo3EditarDialog extends javax.swing.JDialog {
    private PrincipalFrame    frmPrincipal;
    private Modelo3           modelo;
    private String            accion;
    private Vector<String>    vecTiposColumnas;
    private String            sql;
    
    //--------------------------------------------------------------------------
    
    public Modulo3EditarDialog ( java.awt.Frame parent, Modelo3 modelo ) {
        super ( parent, true );
        initComponents  ();
        
        frmPrincipal     = (PrincipalFrame) parent;
        this.modelo      = modelo;
        vecTiposColumnas = frmPrincipal.getVecTiposColumnas ();
        
        accion = ( modelo == null )? PrincipalFrame.NUEVO : PrincipalFrame.EDITAR;
        setTitle ( accion );
        
        jlblLogo.setIcon ( Imagenes.escalarImagen ( jlblLogo.getIcon (), 128, 118 ) );
        
        inicializarFormulario  ();        
    }
        
    //--------------------------------------------------------------------------
    
    private void inicializarFormulario () {
        if        ( accion.equals ( PrincipalFrame.NUEVO ) ) {
            jtxtIdFormula.requestFocus ();
        } else if ( accion.equals ( PrincipalFrame.EDITAR ) ) {
            jtxtIdFormula.setText      ( modelo.getIDFORMULA() + "");
            jtxtCodigo.setText         ( modelo.getCODIGO());
            jtxtDescripcion.setText    ( modelo.getDESCRIPCION() );
            jtxtCant_Ingr_1.setText    ( String.valueOf (modelo.getCANT_INGREDIENTE_1()) );
            jtxtCant_Ingr_2.setText    ( String.valueOf (modelo.getCANT_INGREDIENTE_2()) );
            jtxtCant_Ingr_3.setText    ( String.valueOf (modelo.getCANT_INGREDIENTE_3()) );
        }        
        jtxtCodigo.requestFocus ();
    }
    
    //--------------------------------------------------------------------------
    
    private void dialogoMensaje ( String mensaje  ) {
        JOptionPane.showMessageDialog ( this, mensaje, "Error", JOptionPane.ERROR_MESSAGE );
    }        
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jlblLogo = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jbtnGuardar = new javax.swing.JButton();
        jbtnCancelar = new javax.swing.JButton();
        jtxtIdFormula = new javax.swing.JTextField();
        jtxtCodigo = new javax.swing.JTextField();
        jtxtDescripcion = new javax.swing.JTextField();
        jtxtCant_Ingr_1 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jtxtCant_Ingr_2 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jtxtCant_Ingr_3 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("IDFormula");

        jLabel2.setText("Codigo");

        jlblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/btnModulo3.jpg"))); // NOI18N
        jlblLogo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel4.setText("Descripcion");

        jLabel6.setText("Cant.Ingr.1");

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

        jLabel7.setText("Cant.Ingr.2");

        jLabel8.setText("Cant.Ingr.3");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(111, 111, 111)
                        .addComponent(jbtnGuardar)
                        .addGap(116, 116, 116)
                        .addComponent(jbtnCancelar))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6)
                            .addComponent(jLabel4)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jtxtCant_Ingr_3, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jtxtCant_Ingr_2, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jtxtCant_Ingr_1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtxtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jtxtCodigo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jtxtIdFormula, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 176, Short.MAX_VALUE)
                        .addComponent(jlblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(jlblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtxtIdFormula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtxtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtxtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtxtCant_Ingr_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtxtCant_Ingr_2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtxtCant_Ingr_3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
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
                              PrincipalFrame.FORMULAS_INSERTA_NUEVO );
          args = new Object [][] {
                   { vecTiposColumnas.elementAt ( 0 ), modelo.getIDFORMULA() },
                   { vecTiposColumnas.elementAt ( 1 ), modelo.getCODIGO() }, 
                   { vecTiposColumnas.elementAt ( 2 ), modelo.getDESCRIPCION() },
                   { vecTiposColumnas.elementAt ( 3 ), modelo.getCANT_INGREDIENTE_1() },
                   { vecTiposColumnas.elementAt ( 4 ), modelo.getCANT_INGREDIENTE_2() },
                   { vecTiposColumnas.elementAt ( 5 ), modelo.getCANT_INGREDIENTE_3() }
                };
                    
        } else if ( accion.equals ( PrincipalFrame.EDITAR ) ) {
          mensaje  = "El registro ha sido actualizado.";
          sql = frmPrincipal.getPropSentenciasSQL().getProperty (
                              PrincipalFrame.FORMULAS_ACTUALIZA_DATOS );
          args = new Object [][] {
                   { vecTiposColumnas.elementAt ( 0 ), modelo.getIDFORMULA() },
                   { vecTiposColumnas.elementAt ( 1 ), modelo.getCODIGO() }, 
                   { vecTiposColumnas.elementAt ( 2 ), modelo.getDESCRIPCION() },
                   { vecTiposColumnas.elementAt ( 3 ), modelo.getCANT_INGREDIENTE_1() },
                   { vecTiposColumnas.elementAt ( 4 ), modelo.getCANT_INGREDIENTE_2() },
                   { vecTiposColumnas.elementAt ( 5 ), modelo.getCANT_INGREDIENTE_3() },               
                   { vecTiposColumnas.elementAt ( 0 ), modelo.getIDFORMULA() }               
                };            
        }
        
        try {
            int regs = EjecutorSQL.sqlEjecutar ( sql, args );
            if ( regs == 1 ) {
                frmPrincipal.getJbtnModulo3 ().doClick();
                JOptionPane.showMessageDialog ( 
                        this,
                        mensaje,
                        accion,
                        JOptionPane.INFORMATION_MESSAGE );
            }
        } catch ( SQLException ex ) {
            dialogoMensaje ( ex.toString () );
        }
        dispose ();
    }//GEN-LAST:event_jbtnGuardarActionPerformed

    //--------------------------------------------------------------------------
    
    private boolean validarDatos () {
        
        int idformula = 0;
        try {
            idformula      = Integer.parseInt ( jtxtIdFormula.getText () );
        } catch ( NumberFormatException ex ) {
            dialogoMensaje ( "Debe capturar un valor numerico valido" );
            jtxtIdFormula.requestFocus ();
            return false;
        }

        jtxtIdFormula.getText ();
                if (idformula == 0)//( idformula.trim ().equals ( "" ) ) 
                {
                    dialogoMensaje ( "No se permite un valor en blanco" );
                    jtxtIdFormula.requestFocus ();
                    return false;
                }
        
        String codigo = jtxtCodigo.getText ();
        if ( codigo.trim ().equals ( "" ) ) {
            dialogoMensaje ( "No se permite un valor en blanco" );
            jtxtCodigo.requestFocus ();
            return false;
        }
        
        String descripcion = jtxtDescripcion.getText ();
        if ( descripcion.trim ().equals ( "" ) ) {
            dialogoMensaje ( "No se permite un valor en blanco" );
            jtxtDescripcion.requestFocus ();
            return false;
        }
        
        int cant_ingr_1 = 0;
        try {
            cant_ingr_1      = Integer.parseInt ( jtxtCant_Ingr_1.getText () );
        } catch ( NumberFormatException ex ) {
            dialogoMensaje ( "Debe capturar un valor numerico valido" );
            jtxtCant_Ingr_1.requestFocus ();
            return false;
        }
        
        int cant_ingr_2 = 0;
        try {
            cant_ingr_2      = Integer.parseInt ( jtxtCant_Ingr_2.getText () );
        } catch ( NumberFormatException ex ) {
            dialogoMensaje ( "Debe capturar un valor numerico valido" );
            jtxtCant_Ingr_2.requestFocus ();
            return false;
        }
        
        int cant_ingr_3 = 0;
        try {
            cant_ingr_3      = Integer.parseInt ( jtxtCant_Ingr_3.getText () );
        } catch ( NumberFormatException ex ) {
            dialogoMensaje ( "Debe capturar un valor numerico valido" );
            jtxtCant_Ingr_3.requestFocus ();
            return false;
        }
        
        modelo = new Modelo3 ( idformula, codigo, descripcion, cant_ingr_1,
        cant_ingr_2, cant_ingr_3 );
        return true;
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
            java.util.logging.Logger.getLogger(Modulo3EditarDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Modulo3EditarDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Modulo3EditarDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Modulo3EditarDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Modulo3EditarDialog dialog = new Modulo3EditarDialog(new javax.swing.JFrame(), null );
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

    //--------------------------------------------------------------------------
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JButton jbtnCancelar;
    private javax.swing.JButton jbtnGuardar;
    private javax.swing.JLabel jlblLogo;
    private javax.swing.JTextField jtxtCant_Ingr_1;
    private javax.swing.JTextField jtxtCant_Ingr_2;
    private javax.swing.JTextField jtxtCant_Ingr_3;
    private javax.swing.JTextField jtxtCodigo;
    private javax.swing.JTextField jtxtDescripcion;
    private javax.swing.JTextField jtxtIdFormula;
    // End of variables declaration//GEN-END:variables
}
