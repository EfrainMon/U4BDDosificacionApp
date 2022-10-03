/*------------------------------------------------------------------------------------------
:*                         TECNOLOGICO NACIONAL DE MEXICO
:*                               CAMPUS LA LAGUNA
:*                     INGENIERIA EN SISTEMAS COMPUTACIONALES
:*                       TOPICOS AVANZADOS DE PROGRAMACION "B"
:*
:*                   SEMESTRE: ENE-JUN/2022    HORA: 17-18 HRS
:*
:*           Clase que muestra una GUI de simulación de dosificación
:*        
:*  Archivo     : Modulo4Dosificacion.java
:*  Autor(es)   : Efrain Montalvo Sánchez           20130810
:*              : Aranza Abigail Gassós Salazar     19130889
:*              : Tomás Alejandro Galván Gándara    20130770
:*  Fecha       : 14/May/2022
:*  Compilador  :  
:*  Descripción : Clase que muestra una Interfaz Gráfica de Usuario (GUI) 
:*                en el que permite hacer una simulación de carga/descarga
:*                de materiales a una báscula en base a los datos de las  
:*                tablas que se tienen en la base de Datos DosificacionDB.
:*  Ultima modif: 14/May/2022
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
import modelo.Modelo1;
import modelo.Modelo2;
import modelo.Modelo3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import mx.tecnm.beans.JBascula;
import mx.tecnm.beans.JIndicador;
import static mx.tecnm.beans.JIndicador.EDO_TERMINADO;


    //--------------------------------------------------------------------------

public class Modulo4Dosificacion extends javax.swing.JFrame {

    private PrincipalFrame    frmPrincipal;
    private Modelo2           modelo;
    private Vector<String>    vecTiposColumnas;
    private String            sql;
    //Valor usado para el botón Es Todo.
    int x = 1;
    
    //--------------------------------------------------------------------------
    
    public Modulo4Dosificacion( java.awt.Frame parent, Modelo2 modelo  ) {
        //super ( parent, true );
        initComponents();
        
        frmPrincipal     = (PrincipalFrame) parent;
        this.modelo      = modelo;
        vecTiposColumnas = frmPrincipal.getVecTiposColumnas ();
        
        jbtnEsTodo.setEnabled(false);
        
        llenarCombo1DesdeTabla ();
        llenarCombo2DesdeTabla ();
        
            }

    //-------------------------------------------------------------------------
    
    private esTodoThread simIndicador1;
    private esTodoThread simIndicador2;
    private esTodoThread simIndicador3;
    
    class esTodoThread extends Thread {

        private JIndicador jIndicador = null;

        public esTodoThread () {
            this.jIndicador = jIndicador;
        }

        @Override
        public void run() {
            while ( x == 1 ) {
                if (  ( jIndicador1.getEstadoActual().equals( "Terminado" ) || jIndicador1.getEstadoActual().equals( "Cancelado" ) ) &&
                      ( jIndicador2.getEstadoActual().equals( "Terminado" ) || jIndicador2.getEstadoActual().equals( "Cancelado" ) ) &&
                      ( jIndicador3.getEstadoActual().equals( "Terminado" ) || jIndicador3.getEstadoActual().equals( "Cancelado" ) ) )
                {
                    jbtnEsTodo.setEnabled( true );
                    x = 0;
                } else {
                    jbtnEsTodo.setEnabled( false );
                }
            }
            }
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
            
            
            jcboCamionM4.addItem ( new Modelo1 ( 0, "", "--Seleccione--", 0.0f ) );
            while ( rs.next () ) {
                
                int idcamion        = rs.getInt    ( "idcamion"     );
                String codigo       = rs.getString ( "codigo"       );
                String descripcion  = rs.getString ( "descripcion"  );
                                
                modelo1 = new Modelo1 ( idcamion, codigo, descripcion, 0.0f );
                jcboCamionM4.addItem  (modelo1);
                                
                if ( modelo != null    &&
                     idcamion == ( modelo.getIDCAMION()) ) {
                    int index = jcboCamionM4.getModel ().getSize();
                    jcboCamionM4.setSelectedIndex ( index - 1 );
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
            
            jcboFormulaM4.addItem ( new Modelo3 ( 0, "", "--Seleccione--", 0, 0, 0 ) );
            while ( rs.next () ) {
                
                int idformula           = rs.getInt    ( "idformula" );
                String codigo           = rs.getString ( "codigo"  );
                String descripcion      = rs.getString ( "descripcion"    );
                
                modelo3 = new Modelo3 ( idformula, codigo, descripcion, 0, 0, 0 );
                jcboFormulaM4.addItem ( modelo3 );
                
                if ( modelo != null    &&
                     idformula == ( modelo.getIDFORMULA()) ) {
                    int index = jcboFormulaM4.getModel ().getSize();
                    jcboFormulaM4.setSelectedIndex ( index - 1 );
                }                
            }
            rs.close ();
            
        } catch ( SQLException ex ) {
            dialogoMensaje ( ex.toString () );             
        }        
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jcboFormulaM4 = new javax.swing.JComboBox<>();
        jcboCamionM4 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldCantidad = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jlblIngrTeorico1 = new javax.swing.JLabel();
        jlblIngrTeorico2 = new javax.swing.JLabel();
        jlblIngrTeorico3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jlblIngrReal1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jlblIngrReal2 = new javax.swing.JLabel();
        jlblIngrReal3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jBascula1 = new mx.tecnm.beans.JBascula();
        jBascula2 = new mx.tecnm.beans.JBascula();
        jBascula3 = new mx.tecnm.beans.JBascula();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jbtnIniciar = new javax.swing.JButton();
        jbtnEsTodo = new javax.swing.JButton();
        jIndicador1 = new mx.tecnm.beans.JIndicador();
        jIndicador2 = new mx.tecnm.beans.JIndicador();
        jIndicador3 = new mx.tecnm.beans.JIndicador();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Dosificacion");

        jLabel1.setText("Cantidad a Despachar:");

        jTextFieldCantidad.setText("1");
        jTextFieldCantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCantidadActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Ingredientes"));

        jLabel2.setText("Ingr. 1:");

        jLabel3.setText("Ingr. 2:");

        jLabel4.setText("Ingr. 3:");

        jlblIngrTeorico1.setText(" ");

        jlblIngrTeorico2.setText(" ");

        jlblIngrTeorico3.setText(" ");

        jLabel8.setText("Teóricos");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jlblIngrTeorico1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(jlblIngrTeorico3, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jlblIngrTeorico2, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jLabel8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel8)
                .addGap(9, 9, 9)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jlblIngrTeorico1))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jlblIngrTeorico2))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jlblIngrTeorico3))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Ingredientes"));

        jLabel6.setText("Ingr. 2:");

        jLabel7.setText("Ingr. 3:");

        jlblIngrReal1.setText(" ");

        jLabel5.setText("Ingr. 1:");

        jlblIngrReal2.setText(" ");

        jlblIngrReal3.setText(" ");

        jLabel9.setText("Reales");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(jlblIngrReal1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(jlblIngrReal3, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(jlblIngrReal2, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(jLabel9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel9)
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jlblIngrReal1))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jlblIngrReal2))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jlblIngrReal3))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jBascula1.setTitulo("Cemento");

        jBascula2.setTitulo("Arena");

        jBascula3.setTitulo("Cal");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setText("Formula:");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setText("Camion:");

        jbtnIniciar.setText("Iniciar");
        jbtnIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnIniciarActionPerformed(evt);
            }
        });

        jbtnEsTodo.setText("Es Todo");
        jbtnEsTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnEsTodoActionPerformed(evt);
            }
        });

        jIndicador1.setIndicador(jIndicador1);
        jIndicador1.setBascula(jBascula1);
        jIndicador1.setTitulo("Cemento");

        jIndicador2.setIndicador(jIndicador2);
        jIndicador2.setBascula(jBascula2);
        jIndicador2.setTitulo("Arena");

        jIndicador3.setIndicador(jIndicador3);
        jIndicador3.setBascula(jBascula3);
        jIndicador3.setTitulo("Cal");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(jbtnIniciar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbtnEsTodo)
                        .addGap(75, 75, 75))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jcboFormulaM4, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jcboCamionM4, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(61, 61, 61)
                                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(103, 103, 103)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextFieldCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(34, 34, 34)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBascula1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jIndicador1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBascula2, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jIndicador2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jIndicador3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBascula3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jIndicador1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jIndicador3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel15))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jcboFormulaM4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jcboCamionM4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 94, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jIndicador2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBascula1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBascula2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBascula3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jbtnIniciar)
                            .addComponent(jbtnEsTodo))))
                .addGap(0, 7, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    //--------------------------------------------------------------------------
    
    private void jbtnEsTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnEsTodoActionPerformed

        // Se colocan los valores reales en el apartado de Ingredientes reales.
        
        jlblIngrReal1.setText( String.valueOf( jIndicador1.getValorReal () ) );
        jlblIngrReal2.setText( String.valueOf( jIndicador2.getValorReal () ) );
        jlblIngrReal3.setText( String.valueOf( jIndicador3.getValorReal () ) );
        
        // PASO 3
        
        //Se guardan los datos en la tabla operaciones. 
        
        try {
        
        Class.forName( "org.apache.derby.jdbc.ClientDriver" );
            
        String URL = "jdbc:derby://localhost:1527/DosificacionDB";
        Connection conn = DriverManager.getConnection( URL, "tap", "2022" );
        
        Statement stmt = conn.createStatement ();
        
        String idCamion = String.valueOf( jcboCamionM4.getSelectedItem().toString().charAt( 0 ) );
        String idFormula = String.valueOf( jcboFormulaM4.getSelectedItem().toString().charAt( 0 ) );
        
        float teorico_ingr_1 = Float.parseFloat( jlblIngrTeorico1.getText () );
        float teorico_ingr_2 = Float.parseFloat( jlblIngrTeorico2.getText () );
        float teorico_ingr_3 = Float.parseFloat( jlblIngrTeorico3.getText () );
        
        float real_ingr_1 = Float.parseFloat( jlblIngrReal1.getText () );
        float real_ingr_2 = Float.parseFloat( jlblIngrReal2.getText () );
        float real_ingr_3 = Float.parseFloat( jlblIngrReal3.getText () );
        
        
        sql = "INSERT INTO OPERACIONES ( IDOPERACION, FECHA, HORA, IDFORMULA, IDCAMION," +
                        " TEORICO_INGR_1, TEORICO_INGR_2, TEORICO_INGR_3, " +  
                        " REAL_INGR_1, REAL_INGR_2, REAL_INGR_3 ) VALUES " +
                        "( ( SELECT IDOPERACION +1 FROM OPERACIONES ORDER BY IDOPERACION DESC FETCH FIRST ROW ONLY ), " +
                        " CURRENT_DATE, CURRENT_TIME, " + idFormula + ", " + idCamion + ", " + teorico_ingr_1 +
                        ", " + teorico_ingr_2 + ", " + teorico_ingr_3 + ", " + real_ingr_1 + ", " + real_ingr_2 +
                        ", " + real_ingr_3 + " )";
        
        stmt.execute( sql );
        
            JOptionPane.showMessageDialog(
                    this,
                    "Se guardó la operación correctamente.",
                    "Se han guardado los datos.",
                    JOptionPane.INFORMATION_MESSAGE);
        
        } catch (SQLException ex) {
            Logger.getLogger(Modulo4Dosificacion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Modulo4Dosificacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // PASO 4
        
        // Borra toda la información para que quede como si recién se hubiera
        // abierto la dosificación.
        
        jIndicador1.detenerHilos();
        jIndicador2.detenerHilos();
        jIndicador3.detenerHilos();
        
        jlblIngrTeorico1.setText( "" );
        jlblIngrTeorico2.setText( "" );
        jlblIngrTeorico3.setText( "" );
        
        jlblIngrReal1.setText( "" );
        jlblIngrReal2.setText( "" );
        jlblIngrReal3.setText( "" );
        
        jTextFieldCantidad.setText( "1" );
        
        jIndicador1.setPeso( 0.0f );
        jIndicador1.setSetpoint( 0 );
        jIndicador1.setReal( 0 );
        jIndicador1.SetEstado( "Listo" );
        
        jIndicador2.setPeso( 0.0f );
        jIndicador2.setSetpoint( 0 );
        jIndicador2.setReal( 0 );
        jIndicador2.SetEstado( "Listo" );
        
        jIndicador3.setPeso( 0.0f );
        jIndicador3.setSetpoint( 0 );
        jIndicador3.setReal( 0 );
        jIndicador3.SetEstado( "Listo" );

        jcboFormulaM4.setSelectedIndex(0);
        jcboCamionM4.setSelectedIndex(0);
        
        jIndicador1.setEstadoBotones( false );
        jIndicador2.setEstadoBotones( false );
        jIndicador3.setEstadoBotones( false );
        
        jbtnEsTodo.setEnabled( false );
        x = 1;
        
        jBascula1.limpiarLista();
        jBascula2.limpiarLista();
        jBascula3.limpiarLista();
                
        
    }//GEN-LAST:event_jbtnEsTodoActionPerformed

    //--------------------------------------------------------------------------
    
    private void jTextFieldCantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCantidadActionPerformed
        
        ResultSet rs;
        
        float cant_ingrediente_1 = 0.0f;
        float cant_ingrediente_2 = 0.0f;
        float cant_ingrediente_3 = 0.0f;
        
       // Toma los ID del Camión y las Fórmulas necesario para el Query en la base de datos.   
       String idCamion = String.valueOf( jcboCamionM4.getSelectedItem().toString().charAt( 0 ) );
       String idFormula = String.valueOf( jcboFormulaM4.getSelectedItem().toString().charAt( 0 ) );
       
       // Se usan los valores que se obtuvieron del ComboBox para buscar los 
       // datos que haya en ese renglón.
       sql = "SELECT CANT_INGREDIENTE_1, CANT_INGREDIENTE_2, CANT_INGREDIENTE_3"
             + " FROM FORMULAS WHERE IDFORMULA = " + idFormula;
       
        try {
            rs = EjecutorSQL.sqlQuery ( sql );
            
            while ( rs.next () ) {
                
                cant_ingrediente_1   = rs.getFloat  ( "cant_ingrediente_1" );
                cant_ingrediente_2   = rs.getFloat  ( "cant_ingrediente_2" );
                cant_ingrediente_3   = rs.getFloat  ( "cant_ingrediente_3" );
                
            }
            
            // Se les asigna los valores de los ingredientes obtenidos de la base
            // de datos a los labels pulsando la tecla "ENTER".

            try {
            
            jlblIngrTeorico1.setText( String.valueOf( cant_ingrediente_1 * Integer.parseInt( jTextFieldCantidad.getText() )) );
            jlblIngrTeorico2.setText( String.valueOf( cant_ingrediente_2 * Integer.parseInt( jTextFieldCantidad.getText() )) );
            jlblIngrTeorico3.setText( String.valueOf( cant_ingrediente_3 * Integer.parseInt( jTextFieldCantidad.getText() )) );
            
            } catch ( NumberFormatException ex ) {
                JOptionPane.showMessageDialog(
                        this,
                        "Asegúrese de solo usar valores numéricos y no tan grandes."
                        ,
                        "Error al establecer cantidad",
                        JOptionPane.ERROR_MESSAGE);
            }          
            
            // Se crea una condición por si no existe una operacion con el ID Camion
            // y ID Formula ingresados.
            if (     String.valueOf( cant_ingrediente_1 ).equals( "0.0" )
                  && String.valueOf( cant_ingrediente_2 ).equals( "0.0" ) 
                  && String.valueOf( cant_ingrediente_3 ).equals( "0.0" ) 
               ) 
            
            {
                
                JOptionPane.showMessageDialog(
                        this,
                        "Primero seleccione una formula a despachar de la"
                      + " Base de Datos. \n\n"
                      + "                           Compruebe la información.",
                        "Error al obtener información",
                        JOptionPane.ERROR_MESSAGE);
                
            }

        } catch ( SQLException ex ) {
            dialogoMensaje ( ex.toString () );             
        }
        
        
    }//GEN-LAST:event_jTextFieldCantidadActionPerformed

    
    //--------------------------------------------------------------------------
    
    private void jbtnIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnIniciarActionPerformed
                
        simIndicador1 = new esTodoThread ();
        
        simIndicador1.start();
        
        try {
        
        float teorico_ingr_1 = Float.parseFloat( jlblIngrTeorico1.getText() );
        float teorico_ingr_2 = Float.parseFloat( jlblIngrTeorico2.getText() );
        float teorico_ingr_3 = Float.parseFloat( jlblIngrTeorico3.getText() );
        
        jIndicador1.setSetpoint(teorico_ingr_1);
        jIndicador2.setSetpoint(teorico_ingr_2);
        jIndicador3.setSetpoint(teorico_ingr_3);
        
        } catch( NumberFormatException ex ) {
            
            JOptionPane.showMessageDialog(
                        this,
                        "Confirme la cantidad a despachar pulsando ENTER al "
                        + "colocar la cantidad a despachar.",
                        "Error al obtener información",
                        JOptionPane.ERROR_MESSAGE);

        }
                
    }//GEN-LAST:event_jbtnIniciarActionPerformed

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Modulo4Dosificacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Modulo4Dosificacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Modulo4Dosificacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Modulo4Dosificacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PrincipalFrame().setVisible(false);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private mx.tecnm.beans.JBascula jBascula1;
    private mx.tecnm.beans.JBascula jBascula2;
    private mx.tecnm.beans.JBascula jBascula3;
    private mx.tecnm.beans.JIndicador jIndicador1;
    private mx.tecnm.beans.JIndicador jIndicador2;
    private mx.tecnm.beans.JIndicador jIndicador3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField jTextFieldCantidad;
    private javax.swing.JButton jbtnEsTodo;
    private javax.swing.JButton jbtnIniciar;
    private javax.swing.JComboBox<Modelo1> jcboCamionM4;
    private javax.swing.JComboBox<Modelo3> jcboFormulaM4;
    private javax.swing.JLabel jlblIngrReal1;
    private javax.swing.JLabel jlblIngrReal2;
    private javax.swing.JLabel jlblIngrReal3;
    private javax.swing.JLabel jlblIngrTeorico1;
    private javax.swing.JLabel jlblIngrTeorico2;
    private javax.swing.JLabel jlblIngrTeorico3;
    // End of variables declaration//GEN-END:variables
}
