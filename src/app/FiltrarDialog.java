/*------------------------------------------------------------------------------------------
:*                         TECNOLOGICO NACIONAL DE MEXICO
:*                               CAMPUS LA LAGUNA
:*                     INGENIERIA EN SISTEMAS COMPUTACIONALES
:*                       TOPICOS AVANZADOS DE PROGRAMACION "B"
:*
:*                   SEMESTRE: ENE-JUN/2022    HORA: 17-18 HRS
:*
:*            	   Clase que muestra una GUI para filtrar datos
:*        
:*  Archivo     : FiltrarDialog.java
:*  Autor(es)   : Efrain Montalvo Sánchez           20130810
:*              : Aranza Abigail Gassós Salazar     19130889
:*              : Tomás Alejandro Galván Gándara    20130770
:*  Fecha       : 08/May/2022
:*  Compilador  :  
:*  Descripción : Clase que muestra una Interfaz Gráfica de Usuario 
:*                en el que permite filtrar la información de los datos de la
:*                Base de Datos a gusto del Usuario, visualizándose en la tabla
:*                de la aplicación.
:*
:*  Ultima modif: 08/May/2022
:*  Fecha        Modifico                 Motivo
:*========================================================================================== 
:*   
:*------------------------------------------------------------------------------------------*/


package app;

import java.util.Vector;
import javax.swing.JOptionPane;

//------------------------------------------------------------------------------

public class FiltrarDialog extends javax.swing.JDialog {

    private PrincipalFrame      frmPrincipal;
    private Vector<String>      vecColumnas;
    private Vector<String>      vecColumnasBD;
    private Vector<String>      vecTipos;
    private boolean             inicializando = true;
    
    //--------------------------------------------------------------------------
    
    public FiltrarDialog(java.awt.Frame parent, boolean modal) {
        super ( parent, modal );
        initComponents();
        
        frmPrincipal   = (PrincipalFrame) parent;   
        vecColumnas    = frmPrincipal.getVecNombresColumnas  ();
        vecColumnasBD  = frmPrincipal.getVecNombresColumnasBD();
        vecTipos       = frmPrincipal.getVecTiposColumnas    ();
        
        jcboColumna.removeAllItems();
        for ( int i = 0; i < vecColumnas.size(); i++ ) {
            jcboColumna.addItem ( vecColumnas.get ( i ) );
        }
        
        cargarComparadores ( 0 );
        jcboColumna.setSelectedIndex ( 0 );
        jcboColumna.requestFocus     ();
        inicializando = false;
    }

    //--------------------------------------------------------------------------
    
    private void cargarComparadores ( int pos ) {
        jcboComparador.removeAllItems();
        if ( vecTipos.elementAt ( pos ).equals ( EjecutorSQL.STRING ) ) {
            jcboComparador.addItem ( "Sea igual a"   );
            jcboComparador.addItem ( "Empiece con"   );
            jcboComparador.addItem ( "Termine con"   );
            jcboComparador.addItem ( "Contenga"      );
            jcboComparador.addItem ( "Sea diferente" );
        } else if ( vecTipos.elementAt ( pos ).equals ( EjecutorSQL.INT   ) ||
                    vecTipos.elementAt ( pos ).equals ( EjecutorSQL.FLOAT ) ) {
            jcboComparador.addItem ( "="  );
            jcboComparador.addItem ( "<>" );
            jcboComparador.addItem ( ">"  );
            jcboComparador.addItem ( "<"  );
            jcboComparador.addItem ( ">=" );            
            jcboComparador.addItem ( "<=" );            
        }
        jcboComparador.setSelectedIndex ( 0 );
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jcboColumna = new javax.swing.JComboBox<String>();
        jLabel2 = new javax.swing.JLabel();
        jcboComparador = new javax.swing.JComboBox<String>();
        jLabel3 = new javax.swing.JLabel();
        jtxtValor = new javax.swing.JTextField();
        jbtnAceptar = new javax.swing.JButton();
        jbtnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Buscar");

        jLabel1.setText("Columna:");

        jcboColumna.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jcboColumna.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcboColumnaItemStateChanged(evt);
            }
        });

        jLabel2.setText("Comparador");

        jcboComparador.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel3.setText("Valor");

        jbtnAceptar.setText("Aceptar");
        jbtnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnAceptarActionPerformed(evt);
            }
        });

        jbtnCancelar.setText("Cancelar");
        jbtnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jcboColumna, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jcboComparador, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jtxtValor)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(149, 149, 149)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 149, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addGap(77, 77, 77))))
            .addGroup(layout.createSequentialGroup()
                .addGap(104, 104, 104)
                .addComponent(jbtnAceptar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jbtnCancelar)
                .addGap(113, 113, 113))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcboColumna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcboComparador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtxtValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnAceptar)
                    .addComponent(jbtnCancelar))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    //--------------------------------------------------------------------------
    
    private void jcboColumnaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcboColumnaItemStateChanged
        if ( ! inicializando ) {
            cargarComparadores ( jcboColumna.getSelectedIndex() );
        }
    }//GEN-LAST:event_jcboColumnaItemStateChanged

    //--------------------------------------------------------------------------
    
    private void jbtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCancelarActionPerformed
        dispose ();
    }//GEN-LAST:event_jbtnCancelarActionPerformed

    //--------------------------------------------------------------------------
    
    private void jbtnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnAceptarActionPerformed
        if ( jtxtValor.getText().trim().equals ( "" ) )   {
            JOptionPane.showMessageDialog ( this, "Proporcione un valor", "Buscar", JOptionPane.ERROR_MESSAGE );
            jtxtValor.requestFocus();
            return;
        }
        
        String sql     = "";
        String orderBy = "";
        if ( frmPrincipal.getModuloActual().equals ( PrincipalFrame.TIT_MODULO_1 ) ) {
            sql = frmPrincipal.getPropSentenciasSQL().getProperty     ( 
                        PrincipalFrame.CAMIONES_TODOS_SIN_ORDEN        );
            orderBy    = " order by idcamion";
        }
        if ( frmPrincipal.getModuloActual().equals ( PrincipalFrame.TIT_MODULO_2 ) ) {
            sql = frmPrincipal.getPropSentenciasSQL().getProperty     ( 
                        PrincipalFrame.OPERACIONES_TODAS_SIN_ORDEN        );
            orderBy    = " order by idoperacion";            
        }
        if ( frmPrincipal.getModuloActual().equals ( PrincipalFrame.TIT_MODULO_3 ) ) {
            sql = frmPrincipal.getPropSentenciasSQL().getProperty      ( 
                        PrincipalFrame.FORMULAS_TODAS_SIN_ORDEN          );
            orderBy    = " order by idformula";            
        }
        
        sql += " where O." + vecColumnasBD.elementAt ( jcboColumna.getSelectedIndex () ) + " ";//////
        
        // Determinamos el tipo y valor a sustituir en la consulta parametrizada
        String tipo  = vecTipos.elementAt ( jcboColumna.getSelectedIndex() );
        String valor = jtxtValor.getText();
        if ( jcboComparador.getSelectedItem().toString().equals      ( "Sea igual a"   ) )
            sql += " = ? ";
        else if ( jcboComparador.getSelectedItem().toString().equals ( "Empiece con"   ) ) {
            sql   += " like ? ";
            valor += "%";
        }
        else if ( jcboComparador.getSelectedItem().toString().equals ( "Termine con"   ) ) {
            sql   += " like ? ";
            valor  = "%" + valor;
        }
        else if ( jcboComparador.getSelectedItem().toString().equals ( "Contenga"      ) ) {       
            sql += " like ? ";
            valor = "%" + valor + "%";
        }
        else if ( jcboComparador.getSelectedItem().toString().equals ( "Sea diferente" ) )
            sql += " <> ? ";            
        else 
            sql += jcboComparador.getSelectedItem().toString () + " ? ";
        
        sql += orderBy;
        
        // Formamos los argumentos de la consulta parametrizada
        Object[][] args = { { tipo, valor } };
        // Hacemos desplegar el resultado de la consulta
        frmPrincipal.desplegarRegistros    ( sql, args ); 
        // Cerramos el dialog
        dispose ();
    }//GEN-LAST:event_jbtnAceptarActionPerformed
    
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
            java.util.logging.Logger.getLogger(FiltrarDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FiltrarDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FiltrarDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FiltrarDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FiltrarDialog dialog = new FiltrarDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jbtnAceptar;
    private javax.swing.JButton jbtnCancelar;
    private javax.swing.JComboBox<String> jcboColumna;
    private javax.swing.JComboBox<String> jcboComparador;
    private javax.swing.JTextField jtxtValor;
    // End of variables declaration//GEN-END:variables
}
