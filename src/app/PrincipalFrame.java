/*------------------------------------------------------------------------------------------
:*                         TECNOLOGICO NACIONAL DE MEXICO
:*                               CAMPUS LA LAGUNA
:*                     INGENIERIA EN SISTEMAS COMPUTACIONALES
:*                       TOPICOS AVANZADOS DE PROGRAMACION "B"
:*
:*                   SEMESTRE: ENE-JUN/2022    HORA: 17-18 HRS
:*
:*           Clase principal que muestra una GUI para ver una Base de Datos
:*        
:*  Archivo     : PrincipalFrame.java
:*  Autor(es)   : Efrain Montalvo Sánchez           20130810
:*              : Aranza Abigail Gassós Salazar     19130889
:*              : Tomás Alejandro Galván Gándara    20130770
:*  Fecha       : 08/May/2022
:*  Compilador  :  
:*  Descripción : Clase que muestra una Interfaz Gráfica de Usuario (GUI) 
:*                que permite al usuario poder crear, hacer modificaciones,
:*                filtrar, eliminar y ver una Base de Datos. 
:*                
:*  Ultima modif: 08/May/2022
:*  Fecha        Modifico                 Motivo
:*========================================================================================== 
:*   
:*------------------------------------------------------------------------------------------*/


package app;

import java.awt.Color;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Modelo1;
import modelo.Modelo2;
import modelo.Modelo3;
import jdbc.ConexionDBFrame;
import jdbc.ConexionDB;

//------------------------------------------------------------------------------

public class PrincipalFrame extends javax.swing.JFrame {
    public static final String NUEVO                     = "Nuevo";
    public static final String EDITAR                    = "Editar";
    
    public static final String TIT_FRAME                 = "Sistema de Dosificación";
    public static final String TIT_INICIO                = "TecLag Software";
    public static final String TIT_MODULO_1              = "Camiones"; 
    public static final String TIT_MODULO_2              = "Operaciones"; 
    public static final String TIT_MODULO_3              = "Formulas"; 
    
    public static final String TIT_MODULO_4              = "Dosificacion"; 
    
    public static final String CAMIONES_TODOS_POR_IDCAMION        = "camiones_todos_por_idcamion";
    public static final String CAMIONES_TODOS_SIN_ORDEN           = "camiones_todos_sin_orden";
    public static final String CAMIONES_ACTUALIZA_DATOS           = "camiones_actualiza_datos";
    public static final String CAMIONES_INSERTA_NUEVO             = "camiones_inserta_nuevo";
    public static final String FORMULAS_TODOS_POR_DESCRIPCION     = "formulas_todos_por_descripcion";
    public static final String FORMULAS_TODOS_POR_IDFORMULA     = "formulas_todos_por_idformula";
    public static final String FORMULAS_TODOS_SIN_ORDEN         = "formulas_todos_sin_orden";         
    public static final String FORMULAS_TODAS_SIN_ORDEN           = "formulas_todas_sin_orden";    
    public static final String FORMULAS_ACTUALIZA_DATOS           = "formulas_actualiza_datos";
    public static final String FORMULAS_INSERTA_NUEVO             = "formulas_inserta_nuevo";
    public static final String OPERACIONES_TODOS_POR_FECHA        = "operaciones_todos_por_fecha";
    public static final String OPERACIONES_TODOS_SIN_ORDEN      = "operaciones_todos_sin_orden";
    public static final String CAMIONES_ELIMINAR_X_IDCAMION       = "camiones_eliminar_x_idcamion";
    public static final String FORMULAS_ELIMINAR_X_IDFORMULA      = "formulas_eliminar_x_idformula";
    public static final String OPERACIONES_TODAS_POR_IDCAMION     = "operaciones_todas_por_idcamion"; 
    public static final String OPERACIONES_TODAS_SIN_ORDEN        = "operaciones_todas_sin_orden";
    public static final String OPERACIONES_ELIMINAR_X_IDOPERACION = "operaciones_eliminar_x_idcalific";
    public static final String OPERACIONES_ACTUALIZA_DATOS        = "operaciones_actualiza_datos";
    public static final String OPERACIONES_INSERTA_NUEVO          = "operaciones_inserta_nuevo";
    public static final String OPERACIONES_OBT_MAS_RECIENTE       = "operaciones_obt_max_id";
    
    public static final String OPERACIONES_OBT_INGREDIENTES       = "operaciones_obt_ingredientes";


    private String             moduloActual;
    private int                totRegistros;
    private Vector<String>     vecNombresColumnas;
    private Vector<String>     vecNombresColumnasBD;
    private Vector<String>     vecTiposColumnas;
    private DefaultTableModel  dtmPrincipal;
    private Properties         propSentenciasSQL;
    
    //--------------------------------------------------------------------------
    // Constructor
    
    public PrincipalFrame() {
        initComponents();
        
        this.setTitle                 ( TIT_FRAME );
        jlblMensajeDelSistema.setText ( "" );
        jtoolbPrincipal.setVisible    ( false );
        jpnlTabla.setVisible          ( false );
        jpnlLogo.setVisible           ( true  );
        jlblLeyendaPrincipal.setText  ( TIT_INICIO   );
        jlblBotonModulo1.setText      ( TIT_MODULO_1 );
        jlblBotonModulo2.setText      ( TIT_MODULO_2 );
        jlblBotonModulo3.setText      ( TIT_MODULO_3 );
        jlblBotonModulo4.setText      ( TIT_MODULO_4 );
        
        jmniReportesModulo1.setText   ( TIT_MODULO_1 );
        jmniReportesModulo2.setText   ( TIT_MODULO_2 );
        jmniReportesModulo3.setText   ( TIT_MODULO_3 );
               
        prepararSentenciasSQL         ();

        new ConexionDBFrame ( this ).setVisible (  true );
    }
    
    //--------------------------------------------------------------------------
    
    private void prepararSentenciasSQL () {
        propSentenciasSQL = new Properties ();
        
        propSentenciasSQL.put ( CAMIONES_TODOS_POR_IDCAMION,
                "select * from camiones order by idcamion"   );
        propSentenciasSQL.put ( CAMIONES_TODOS_SIN_ORDEN,
                "select * from camiones"   ); 
        propSentenciasSQL.put ( CAMIONES_ELIMINAR_X_IDCAMION,
                "delete from camiones where idcamion = ?"   ); 
        propSentenciasSQL.put ( CAMIONES_ACTUALIZA_DATOS,
                "update camiones set idcamion = ?, codigo = ?, descripcion = ?, capacidad = ? " +
                       "where idcamion = ?"  ); 
        propSentenciasSQL.put ( CAMIONES_INSERTA_NUEVO,
                "insert into Camiones values ( ?, ?, ?, ? )" ); 
        propSentenciasSQL.put ( FORMULAS_TODOS_POR_DESCRIPCION, 
                "select * from formulas order by descripcion" );
        propSentenciasSQL.put ( FORMULAS_TODOS_SIN_ORDEN,
                "select * from formulas"   );
        propSentenciasSQL.put ( FORMULAS_TODOS_POR_IDFORMULA,
                "select * from formulas order by idformula"   );
        propSentenciasSQL.put ( FORMULAS_TODAS_SIN_ORDEN,
                "select IDFORMULA, CODIGO, DESCRIPCION, CANT_INGREDIENTE_1, " +
                "CANT_INGREDIENTE_2, CANT_INGREDIENTE_3 " +
                "from Formulas" ); 
        propSentenciasSQL.put ( FORMULAS_ELIMINAR_X_IDFORMULA,
                "delete from formulas where idformula = ?"   ); 
        propSentenciasSQL.put ( FORMULAS_ACTUALIZA_DATOS,
                "update formulas set idformula = ?, codigo = ?, descripcion = ?,"
                        + " cant_ingrediente_1 = ?, cant_ingrediente_2 = ?,"
                        + " cant_ingrediente_3 = ? where idformula = ?"  ); 
        propSentenciasSQL.put ( FORMULAS_INSERTA_NUEVO,
                "insert into formulas values ( ?, ?, ?, ?, ?, ?)" );         
        propSentenciasSQL.put ( OPERACIONES_TODAS_POR_IDCAMION, 
                "select O.IDOPERACION, O.FECHA, O.HORA, O.IDFORMULA, O.IDCAMION," +
                       "O.TEORICO_INGR_1, O.TEORICO_INGR_2, O.TEORICO_INGR_3," + 
                       "O.REAL_INGR_1, O.REAL_INGR_2, O.REAL_INGR_3 " +
                "from Operaciones O, Camiones C, Formulas F " +
                "where O.IDCAMION = C.IDCAMION AND O.IDFORMULA = F.IDFORMULA " +
                "order by O.IDOPERACION" );
        propSentenciasSQL.put ( OPERACIONES_TODAS_SIN_ORDEN,
                "select O.IDOPERACION, O.FECHA, O.HORA, F.IDFORMULA, " +
                       "C.IDCAMION, O.TEORICO_INGR_1, O.TEORICO_INGR_2, O.TEORICO_INGR_3, " +
                       "O.REAL_INGR_1, O.REAL_INGR_2, O.REAL_INGR_3 " +
                "from Operaciones O join Camiones C on O.IDCAMION = C.IDCAMION " +
                "join Formulas F on O.IDFORMULA = F.IDFORMULA" );         
        propSentenciasSQL.put ( OPERACIONES_ELIMINAR_X_IDOPERACION,
                "delete from Operaciones where IDOPERACION = ?"   );     
        propSentenciasSQL.put ( OPERACIONES_ACTUALIZA_DATOS,
                "update Operaciones set IDOPERACION = ?, FECHA = ?, HORA = ?," +
                              " IDFORMULA = ?, IDCAMION = ?, TEORICO_INGR_1 = ?, TEORICO_INGR_2 = ?," +
                              " TEORICO_INGR_3 = ?, REAL_INGR_1 = ?, REAL_INGR_2 = ?, REAL_INGR_3 = ? " +
                              "where IDOPERACION = ?" );
        propSentenciasSQL.put ( OPERACIONES_INSERTA_NUEVO,
                "insert into Operaciones values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )" );        
        propSentenciasSQL.put ( OPERACIONES_OBT_MAS_RECIENTE,
                "select max ( FECHA ) as mas_reciente from Operaciones" );
        
        propSentenciasSQL.put ( OPERACIONES_OBT_INGREDIENTES,
                "SELECT TEORICO_INGR_1, TEORICO_INGR_2, TEORICO_INGR_3," +
                        " REAL_INGR_1, REAL_INGR_2, REAL_INGR_3" +
                        " FROM OPERACIONES WHERE IDFORMULA = ? AND IDCAMION = ? ");
    }    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpopmPrincipal = new javax.swing.JPopupMenu();
        jmniNuevo = new javax.swing.JMenuItem();
        jmniEditar = new javax.swing.JMenuItem();
        jmniEliminar = new javax.swing.JMenuItem();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jbtnModulo1 = new javax.swing.JButton();
        jlblBotonModulo1 = new javax.swing.JLabel();
        jbtnModulo2 = new javax.swing.JButton();
        jlblBotonModulo2 = new javax.swing.JLabel();
        jbtnModulo3 = new javax.swing.JButton();
        jlblBotonModulo3 = new javax.swing.JLabel();
        jbtnModulo4 = new javax.swing.JButton();
        jlblBotonModulo4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jlblLeyendaPrincipal = new javax.swing.JLabel();
        jpnlTabla = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtblPrincipal = new javax.swing.JTable();
        jtoolbPrincipal = new javax.swing.JToolBar();
        jbtnNuevo = new javax.swing.JButton();
        jbtnEditar = new javax.swing.JButton();
        jbtnEliminar = new javax.swing.JButton();
        jbtnInicio = new javax.swing.JButton();
        jbtnFiltrar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        jLabel2 = new javax.swing.JLabel();
        jlblEstatusConexion = new javax.swing.JLabel();
        jToolBar2 = new javax.swing.JToolBar();
        jlblMensajeDelSistema = new javax.swing.JLabel();
        jpnlLogo = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jmbrPrincipal = new javax.swing.JMenuBar();
        jmniEstatusConexion = new javax.swing.JMenu();
        jmniSalir = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jmniReportesModulo1 = new javax.swing.JMenuItem();
        jmniReportesModulo2 = new javax.swing.JMenuItem();
        jmniReportesModulo3 = new javax.swing.JMenuItem();
        jmniRecalcPromedios = new javax.swing.JMenuItem();
        jmnuAyuda = new javax.swing.JMenu();
        jmniAcercaDe = new javax.swing.JMenuItem();

        jmniNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/filenew.gif"))); // NOI18N
        jmniNuevo.setText("Nuevo");
        jpopmPrincipal.add(jmniNuevo);

        jmniEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/fileopen.gif"))); // NOI18N
        jmniEditar.setText("Editar");
        jpopmPrincipal.add(jmniEditar);

        jmniEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/editcut.gif"))); // NOI18N
        jmniEliminar.setText("Eliminar");
        jpopmPrincipal.add(jmniEliminar);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema de Calificaciones");
        setBackground(new java.awt.Color(255, 255, 255));
        setExtendedState(6);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jbtnModulo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/btnmodulo1.jpg"))); // NOI18N
        jbtnModulo1.setToolTipText("Alumnos");
        jbtnModulo1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jbtnModulo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnModulo1ActionPerformed(evt);
            }
        });

        jlblBotonModulo1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblBotonModulo1.setText("Modulo 1");

        jbtnModulo2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/btnModulo2.jpg"))); // NOI18N
        jbtnModulo2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnModulo2ActionPerformed(evt);
            }
        });

        jlblBotonModulo2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblBotonModulo2.setText("Modulo 2");

        jbtnModulo3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/btnmodulo3.jpg"))); // NOI18N
        jbtnModulo3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnModulo3ActionPerformed(evt);
            }
        });

        jlblBotonModulo3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblBotonModulo3.setText("Modulo 3");

        jbtnModulo4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/btnModulo4.png"))); // NOI18N
        jbtnModulo4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnModulo4ActionPerformed(evt);
            }
        });

        jlblBotonModulo4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblBotonModulo4.setText("Modulo 4");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jlblBotonModulo1, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                            .addComponent(jlblBotonModulo2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jlblBotonModulo3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jlblBotonModulo4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(jbtnModulo4, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jbtnModulo1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jbtnModulo2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jbtnModulo3, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jbtnModulo1)
                .addGap(3, 3, 3)
                .addComponent(jlblBotonModulo1)
                .addGap(33, 33, 33)
                .addComponent(jbtnModulo2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlblBotonModulo2)
                .addGap(42, 42, 42)
                .addComponent(jbtnModulo3, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jlblBotonModulo3)
                .addGap(42, 42, 42)
                .addComponent(jbtnModulo4, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jlblBotonModulo4)
                .addContainerGap(205, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel1);

        jPanel2.setBackground(new java.awt.Color(0, 0, 153));

        jlblLeyendaPrincipal.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jlblLeyendaPrincipal.setForeground(new java.awt.Color(255, 255, 255));
        jlblLeyendaPrincipal.setText("TecLag Software");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jlblLeyendaPrincipal)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jlblLeyendaPrincipal)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpnlTabla.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jtblPrincipal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtblPrincipal.setComponentPopupMenu(jpopmPrincipal);
        jtblPrincipal.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(jtblPrincipal);

        javax.swing.GroupLayout jpnlTablaLayout = new javax.swing.GroupLayout(jpnlTabla);
        jpnlTabla.setLayout(jpnlTablaLayout);
        jpnlTablaLayout.setHorizontalGroup(
            jpnlTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );
        jpnlTablaLayout.setVerticalGroup(
            jpnlTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
        );

        jtoolbPrincipal.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jtoolbPrincipal.setFloatable(false);
        jtoolbPrincipal.setRollover(true);

        jbtnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/filenew.gif"))); // NOI18N
        jbtnNuevo.setText("Nuevo");
        jbtnNuevo.setToolTipText("Nuevo");
        jbtnNuevo.setFocusable(false);
        jbtnNuevo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbtnNuevo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jbtnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnNuevoActionPerformed(evt);
            }
        });
        jtoolbPrincipal.add(jbtnNuevo);

        jbtnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/fileopen.gif"))); // NOI18N
        jbtnEditar.setText("Editar");
        jbtnEditar.setToolTipText("Modificar");
        jbtnEditar.setFocusable(false);
        jbtnEditar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbtnEditar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jbtnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnEditarActionPerformed(evt);
            }
        });
        jtoolbPrincipal.add(jbtnEditar);

        jbtnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/editcut.gif"))); // NOI18N
        jbtnEliminar.setText("Eliminar");
        jbtnEliminar.setToolTipText("Eliminar");
        jbtnEliminar.setFocusable(false);
        jbtnEliminar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbtnEliminar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jbtnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnEliminarActionPerformed(evt);
            }
        });
        jtoolbPrincipal.add(jbtnEliminar);

        jbtnInicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/gohome.gif"))); // NOI18N
        jbtnInicio.setText("Inicio");
        jbtnInicio.setFocusable(false);
        jbtnInicio.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbtnInicio.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jbtnInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnInicioActionPerformed(evt);
            }
        });
        jtoolbPrincipal.add(jbtnInicio);

        jbtnFiltrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/buscar.jpg"))); // NOI18N
        jbtnFiltrar.setText("Filtrar");
        jbtnFiltrar.setToolTipText("Filtrar");
        jbtnFiltrar.setFocusable(false);
        jbtnFiltrar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbtnFiltrar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jbtnFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnFiltrarActionPerformed(evt);
            }
        });
        jtoolbPrincipal.add(jbtnFiltrar);

        jToolBar1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        jLabel2.setText("Estatus de la conexion:");
        jToolBar1.add(jLabel2);

        jlblEstatusConexion.setText("<estatus>");
        jToolBar1.add(jlblEstatusConexion);

        jToolBar2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jToolBar2.setFloatable(false);
        jToolBar2.setRollover(true);

        jlblMensajeDelSistema.setText("<mensajes del sistema>");
        jToolBar2.add(jlblMensajeDelSistema);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToolBar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(0, 8, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToolBar2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/logosistemaDosificacion.jpg"))); // NOI18N

        javax.swing.GroupLayout jpnlLogoLayout = new javax.swing.GroupLayout(jpnlLogo);
        jpnlLogo.setLayout(jpnlLogoLayout);
        jpnlLogoLayout.setHorizontalGroup(
            jpnlLogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 743, Short.MAX_VALUE)
        );
        jpnlLogoLayout.setVerticalGroup(
            jpnlLogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
        );

        jmniEstatusConexion.setText("Archivo");

        jmniSalir.setText("Salir");
        jmniSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmniSalirActionPerformed(evt);
            }
        });
        jmniEstatusConexion.add(jmniSalir);

        jmbrPrincipal.add(jmniEstatusConexion);

        jMenu1.setText("Herramientas");

        jMenu2.setText("Reportes");

        jmniReportesModulo1.setText("Modulo1");
        jMenu2.add(jmniReportesModulo1);

        jmniReportesModulo2.setText("Modulo2");
        jMenu2.add(jmniReportesModulo2);

        jmniReportesModulo3.setText("Modulo3");
        jMenu2.add(jmniReportesModulo3);

        jMenu1.add(jMenu2);

        jmniRecalcPromedios.setText("Recalcular promedios");
        jmniRecalcPromedios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmniRecalcPromediosActionPerformed(evt);
            }
        });
        jMenu1.add(jmniRecalcPromedios);

        jmbrPrincipal.add(jMenu1);

        jmnuAyuda.setText("Ayuda");

        jmniAcercaDe.setText("Acerca de...");
        jmniAcercaDe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmniAcercaDeActionPerformed(evt);
            }
        });
        jmnuAyuda.add(jmniAcercaDe);

        jmbrPrincipal.add(jmnuAyuda);

        setJMenuBar(jmbrPrincipal);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(1, 1, 1))
                    .addComponent(jpnlTabla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpnlLogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addComponent(jtoolbPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jtoolbPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jpnlTabla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jpnlLogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    //--------------------------------------------------------------------------
    
    private void jmniSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmniSalirActionPerformed
        ConexionDB.getInstancia().desconectar();
        System.exit ( 0 );
    }//GEN-LAST:event_jmniSalirActionPerformed

    //--------------------------------------------------------------------------
    
    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        if ( ConexionDB.getInstancia().conectado() ) {
            jlblEstatusConexion.setText ( "Conectado" );
            jlblEstatusConexion.setForeground ( Color.blue );
        } else {
            jlblEstatusConexion.setText ( "Desconectado" );
            jlblEstatusConexion.setForeground ( Color.red );
        }
    }//GEN-LAST:event_formWindowActivated

    //--------------------------------------------------------------------------
    
    private void jbtnModulo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnModulo1ActionPerformed
        String sql             = propSentenciasSQL.getProperty ( CAMIONES_TODOS_POR_IDCAMION );
        desplegarTablaDelModuloTodos ( TIT_MODULO_1, sql );
    }//GEN-LAST:event_jbtnModulo1ActionPerformed

    //--------------------------------------------------------------------------    

    private void jbtnInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnInicioActionPerformed
        jtoolbPrincipal.setVisible( false );
        jpnlTabla.setVisible      ( false );
        jpnlLogo.setVisible       ( true  );

        jlblLeyendaPrincipal.setText ( TIT_INICIO );
        mostrarMensajeDelSistema  ( "" );
        moduloActual = "";
    }//GEN-LAST:event_jbtnInicioActionPerformed

    //--------------------------------------------------------------------------
    
    private void jbtnModulo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnModulo2ActionPerformed
        String sql             = propSentenciasSQL.getProperty ( OPERACIONES_TODAS_POR_IDCAMION );
        desplegarTablaDelModuloTodos ( TIT_MODULO_2, sql );
    }//GEN-LAST:event_jbtnModulo2ActionPerformed

    //--------------------------------------------------------------------------
    
    private void jbtnModulo3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnModulo3ActionPerformed
        String sql            = propSentenciasSQL.getProperty ( FORMULAS_TODOS_POR_IDFORMULA );
        desplegarTablaDelModuloTodos ( TIT_MODULO_3, sql );
    }//GEN-LAST:event_jbtnModulo3ActionPerformed
  
    //--------------------------------------------------------------------------
    
    private void jbtnFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnFiltrarActionPerformed
        FiltrarDialog buscarDialog = new FiltrarDialog ( this, true );
        buscarDialog.setVisible ( true );
    }//GEN-LAST:event_jbtnFiltrarActionPerformed

    //--------------------------------------------------------------------------
    
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        jmniSalir.doClick();
    }//GEN-LAST:event_formWindowClosing

    //--------------------------------------------------------------------------
    
    private void jbtnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnEliminarActionPerformed
        int pos = jtblPrincipal.getSelectedRow();
        if ( pos == -1 ) {
            JOptionPane.showMessageDialog ( 
                this, "No hay un registro seleccionado", "Eliminar", JOptionPane.ERROR_MESSAGE );
            return; 
        } 
        int confirma = JOptionPane.showConfirmDialog ( 
                            this, 
                            "Eliminar el registro seleccionado ?",
                            "Eliminar",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE  );
        if ( confirma == JOptionPane.NO_OPTION )
            return;
        
        String valorLlavePrim = jtblPrincipal.getValueAt ( pos, 0 ).toString();
        String tipoLlavePrim  = vecTiposColumnas.elementAt ( 0 );
        String sql            = "";
        JButton jbtnModulo    = null;
        
        if             ( moduloActual == TIT_MODULO_1 ) {
            sql = propSentenciasSQL.getProperty ( CAMIONES_ELIMINAR_X_IDCAMION  );
            jbtnModulo = jbtnModulo1;
        } else  if     ( moduloActual == TIT_MODULO_2 ) {
            sql = propSentenciasSQL.getProperty (  OPERACIONES_ELIMINAR_X_IDOPERACION );
            jbtnModulo = jbtnModulo2;
        } else  if     ( moduloActual == TIT_MODULO_3 ) {
            sql = propSentenciasSQL.getProperty ( FORMULAS_ELIMINAR_X_IDFORMULA );
            jbtnModulo = jbtnModulo3;
        }

        Object [][] args = { { tipoLlavePrim, valorLlavePrim }  };
        try {
            int regs = EjecutorSQL.sqlEjecutar ( sql, args );
            if ( regs == 1 ) {
                jbtnModulo.doClick();
                JOptionPane.showMessageDialog ( 
                    this, "El registro ha sido eliminado", "Eliminar",
                    JOptionPane.INFORMATION_MESSAGE );
            }
        } catch ( SQLIntegrityConstraintViolationException ex ) {
            JOptionPane.showMessageDialog ( 
                    this, 
                    "No se puede eliminar el registro actual porque tiene " +
                    "registros asociados en otras tablas", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE );
        } catch ( SQLException ex ) {
            JOptionPane.showMessageDialog ( 
                    this, ex, "Error", JOptionPane.ERROR_MESSAGE );
        }
    }//GEN-LAST:event_jbtnEliminarActionPerformed

    //--------------------------------------------------------------------------
    
    private void jbtnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnEditarActionPerformed
        int pos = jtblPrincipal.getSelectedRow();
        if ( pos == -1 ) {
            JOptionPane.showMessageDialog ( 
                this, "No hay un registro seleccionado", "Eliminar", JOptionPane.ERROR_MESSAGE );
            return; 
        } 
        if        ( moduloActual.equals ( TIT_MODULO_1 ) ) {
            int    idcamion    = Integer.parseInt ( jtblPrincipal.getValueAt ( pos, 0 ).toString () );
            String codigo      = jtblPrincipal.getValueAt ( pos, 1 ).toString ();
            String descripcion = jtblPrincipal.getValueAt ( pos, 2 ).toString ();
            float  capacidad   = Float.parseFloat ( jtblPrincipal.getValueAt ( pos, 3 ).toString () );
            
            Modelo1 modelo   = new Modelo1 ( idcamion, codigo, descripcion, capacidad );
            
            Modulo1EditarDialog dialog = new Modulo1EditarDialog ( this, modelo );
            dialog.setVisible ( true );
        } else if ( moduloActual.equals ( TIT_MODULO_2 ) ) {
            int    idoperacion       = Integer.parseInt ( jtblPrincipal.getValueAt ( pos, 0 ).toString () );
            String fecha             = jtblPrincipal.getValueAt ( pos, 1 ).toString ();
            String hora              = jtblPrincipal.getValueAt ( pos, 2 ).toString ();
            int    idformula         = Integer.parseInt ( jtblPrincipal.getValueAt ( pos, 3 ).toString () );                         
            int    idcamion          = Integer.parseInt ( jtblPrincipal.getValueAt ( pos, 4 ).toString () );
            float    teorico_ingr_1  = Float.parseFloat ( jtblPrincipal.getValueAt ( pos, 5 ).toString () );
            float    teorico_ingr_2  = Float.parseFloat ( jtblPrincipal.getValueAt ( pos, 6 ).toString () );
            float    teorico_ingr_3  = Float.parseFloat ( jtblPrincipal.getValueAt ( pos, 7 ).toString () );
            float    real_ingr_1     = Float.parseFloat ( jtblPrincipal.getValueAt ( pos, 8 ).toString () );
            float    real_ingr_2     = Float.parseFloat ( jtblPrincipal.getValueAt ( pos, 9 ).toString () );
            float    real_ingr_3     = Float.parseFloat ( jtblPrincipal.getValueAt ( pos, 10 ).toString () );
            
            Modelo2 modelo   = new Modelo2 ( idoperacion, fecha, hora, idformula, idcamion, teorico_ingr_1, teorico_ingr_2, teorico_ingr_3,
                                            real_ingr_1, real_ingr_2, real_ingr_3 );
            
            Modulo2EditarDialog dialog = new Modulo2EditarDialog ( this, modelo );
            dialog.setVisible ( true );            
        } else if ( moduloActual.equals ( TIT_MODULO_3 ) ) {
            int    idformula        = Integer.parseInt ( 
                                      jtblPrincipal.getValueAt ( pos, 0 ).toString() );
            String codigo           = jtblPrincipal.getValueAt ( pos, 1 ).toString();
            String descripcion      = jtblPrincipal.getValueAt ( pos, 2 ).toString();
            int cant_ingrediente_1  = Integer.parseInt (jtblPrincipal.getValueAt ( pos, 3 ).toString() );
            int cant_ingrediente_2  = Integer.parseInt (jtblPrincipal.getValueAt ( pos, 4 ).toString() );
            int cant_ingrediente_3  = Integer.parseInt ( jtblPrincipal.getValueAt( pos, 5 ).toString() );

            Modelo3 modelo   = new Modelo3 ( idformula, codigo, descripcion, 
                                             cant_ingrediente_1, cant_ingrediente_2 ,cant_ingrediente_3 );            
            
            Modulo3EditarDialog dialog = new Modulo3EditarDialog ( this, modelo );
            dialog.setVisible ( true );            
        }
    }//GEN-LAST:event_jbtnEditarActionPerformed

    //--------------------------------------------------------------------------
    
    private void jbtnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnNuevoActionPerformed
        if        ( moduloActual.equals ( TIT_MODULO_1 ) ) {
            Modulo1EditarDialog dialog = new Modulo1EditarDialog ( this, null );
            dialog.setVisible ( true );
        } else if ( moduloActual.equals ( TIT_MODULO_2 ) ) {
            Modulo2EditarDialog dialog = new Modulo2EditarDialog ( this, null );
            dialog.setVisible ( true );            
        } else if ( moduloActual.equals ( TIT_MODULO_3 ) ) {
            Modulo3EditarDialog dialog = new Modulo3EditarDialog ( this, null );
            dialog.setVisible ( true );            
        }        
    }//GEN-LAST:event_jbtnNuevoActionPerformed

    //--------------------------------------------------------------------------
    
    private void jmniRecalcPromediosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmniRecalcPromediosActionPerformed

    }//GEN-LAST:event_jmniRecalcPromediosActionPerformed

    //--------------------------------------------------------------------------
    
    private void jmniAcercaDeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmniAcercaDeActionPerformed
      
        AcercaDeDosificacion dialog = new AcercaDeDosificacion ( this, true );
            dialog.setVisible ( true );
            
    }//GEN-LAST:event_jmniAcercaDeActionPerformed

    //--------------------------------------------------------------------------
    
    private void jbtnModulo4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnModulo4ActionPerformed

        // TODO add your handling code here:
        
        Modulo4Dosificacion dialog = new Modulo4Dosificacion ( this, null );
            dialog.setVisible ( true );
        
    }//GEN-LAST:event_jbtnModulo4ActionPerformed

    //--------------------------------------------------------------------------
    
    private void desplegarTablaDelModuloTodos ( String modulo, String sql ) {
        prepararVistaDelModulo   ( modulo );
        desplegarRegistros       ( sql, null );         
    }
    
    //--------------------------------------------------------------------------
    
    private void   prepararVistaDelModulo ( String modulo ) {  
        moduloActual                 = modulo;
        jtoolbPrincipal.setVisible   ( true  );
        jpnlTabla.setVisible         ( true  );
        jpnlLogo.setVisible          ( false );
        
        jlblLeyendaPrincipal.setText ( modulo );

        determinarNombresColumnas    ( modulo );
        
        dtmPrincipal = new DefaultTableModel ( vecNombresColumnas, 0 );
        jtblPrincipal.setModel       ( dtmPrincipal );   
    }
    
    //--------------------------------------------------------------------------    
    
    private void determinarNombresColumnas ( String modulo ) {
        vecNombresColumnas   = new Vector<String> ();
        vecNombresColumnasBD = new Vector<String> ();
        vecTiposColumnas     = new Vector<String> ();

        if        ( modulo.equals ( TIT_MODULO_1 ) ) {
            vecNombresColumnas.add    ( "ID Camion"   );
            vecNombresColumnas.add    ( "Codigo"      );
            vecNombresColumnas.add    ( "Descripcion" );
            vecNombresColumnas.add    ( "Capacidad"   );
           
            
            vecNombresColumnasBD.add  ( "idcamion"    );           
            vecNombresColumnasBD.add  ( "codigo"      );
            vecNombresColumnasBD.add  ( "descripcion" );
            vecNombresColumnasBD.add  ( "capacidad"   );
            
            
            vecTiposColumnas.add      ( EjecutorSQL.INT );
            vecTiposColumnas.add      ( EjecutorSQL.STRING );
            vecTiposColumnas.add      ( EjecutorSQL.STRING );
            vecTiposColumnas.add      ( EjecutorSQL.FLOAT  );
        } else if ( modulo.equals ( TIT_MODULO_2 ) ) {
            vecNombresColumnas.add    ( "ID Operacion" );
            vecNombresColumnas.add    ( "Fecha"        );
            vecNombresColumnas.add    ( "Hora"         );
            vecNombresColumnas.add    ( "ID Formula"   );
            vecNombresColumnas.add    ( "ID Camion"    );
            vecNombresColumnas.add    ( "Teorico Ingr 1"   );
            vecNombresColumnas.add    ( "Teorico Ingr 2"   );
            vecNombresColumnas.add    ( "Teorico Ingr 3"   );
            vecNombresColumnas.add    ( "Real Ingr 1"      );
            vecNombresColumnas.add    ( "Real Ingr 2"      );
            vecNombresColumnas.add    ( "Real Ingr 3"      );
            
            vecNombresColumnasBD.add  ( "idoperacion" );           
            vecNombresColumnasBD.add  ( "fecha"       );
            vecNombresColumnasBD.add  ( "hora"        );
            vecNombresColumnasBD.add  ( "idformula"   );
            vecNombresColumnasBD.add  ( "idcamion"    );
            vecNombresColumnasBD.add  ( "teorico_ingr_1"  );
            vecNombresColumnasBD.add  ( "teorico_ingr_2"  );
            vecNombresColumnasBD.add  ( "teorico_ingr_3"  );
            vecNombresColumnasBD.add  ( "real_ingr_1" );
            vecNombresColumnasBD.add  ( "real_ingr_2" );
            vecNombresColumnasBD.add  ( "real_ingr_3" );
            
            vecTiposColumnas.add      ( EjecutorSQL.INT    );
            vecTiposColumnas.add      ( EjecutorSQL.STRING );
            vecTiposColumnas.add      ( EjecutorSQL.STRING );
            vecTiposColumnas.add      ( EjecutorSQL.INT    );
            vecTiposColumnas.add      ( EjecutorSQL.INT    );
            vecTiposColumnas.add      ( EjecutorSQL.FLOAT    );
            vecTiposColumnas.add      ( EjecutorSQL.FLOAT    );
            vecTiposColumnas.add      ( EjecutorSQL.FLOAT    );
            vecTiposColumnas.add      ( EjecutorSQL.FLOAT    );
            vecTiposColumnas.add      ( EjecutorSQL.FLOAT    );
            vecTiposColumnas.add      ( EjecutorSQL.FLOAT    );
            
        } else if ( modulo.equals ( TIT_MODULO_3 ) ) {
            vecNombresColumnas.add    ( "ID Formula"   );
            vecNombresColumnas.add    ( "Codigo"       );
            vecNombresColumnas.add    ( "Descripcion"  );
            vecNombresColumnas.add    ( "Cant Ingr 1"  );
            vecNombresColumnas.add    ( "Cant Ingr 2"  );
            vecNombresColumnas.add    ( "Cant Ingr 3"  );
            

            vecNombresColumnasBD.add  ( "idformula"          );
            vecNombresColumnasBD.add  ( "codigo"             );
            vecNombresColumnasBD.add  ( "descripcion"         );
            vecNombresColumnasBD.add  ( "cant_ingrediente_1" );
            vecNombresColumnasBD.add  ( "cant_ingrediente_2" );
            vecNombresColumnasBD.add  ( "cant_ingrediente_3" );
            
            
            vecTiposColumnas.add      ( EjecutorSQL.INT    );
            vecTiposColumnas.add      ( EjecutorSQL.STRING );
            vecTiposColumnas.add      ( EjecutorSQL.STRING );
            vecTiposColumnas.add      ( EjecutorSQL.INT    );               
            vecTiposColumnas.add      ( EjecutorSQL.INT    ); 
            vecTiposColumnas.add      ( EjecutorSQL.INT    );   
        }
    }

    //--------------------------------------------------------------------------
    
    public void desplegarRegistros ( String sql, Object [][] args ) {
        ResultSet rs;
        try {
            rs = EjecutorSQL.sqlQuery ( sql, args );
            
            dtmPrincipal = new DefaultTableModel ( vecNombresColumnas, 0 );
            while ( rs.next () ) {
                Object [] fila = crearFila ( rs );
                dtmPrincipal.addRow ( fila );
            }
            rs.close ();
            jtblPrincipal.setModel ( dtmPrincipal );
            totRegistros = dtmPrincipal.getRowCount ();
            mostrarMensajeDelSistema ( totRegistros + " registros" );
        } catch ( SQLException ex ) {
            JOptionPane.showMessageDialog ( this, ex, "Error", JOptionPane.ERROR_MESSAGE );
        }
    }
         
    //--------------------------------------------------------------------------
    
    private Object [] crearFila ( ResultSet rs ) throws SQLException {
       
        if ( moduloActual.equals ( TIT_MODULO_1 ) ) {
            int idcamion       = rs.getInt    ( "idcamion"    );
            String codigo      = rs.getString ( "codigo"      );
            String descripcion = rs.getString ( "descripcion" );
            float  capacidad   = rs.getFloat  ( "capacidad"   );

            Object [] fila = { idcamion, codigo, descripcion, capacidad };   
            return fila;
            
       } else if ( moduloActual.equals ( TIT_MODULO_2 ) ) {
            int    idoperacion       = rs.getInt ( "idoperacion"      );
            String fecha             = rs.getString ( "fecha"            );
            String hora              = rs.getString ( "hora"             );
            int    idformula         = rs.getInt    ( "idformula"        );
            int    idcamion          = rs.getInt    ( "idcamion"         );
            float  teorico_ingr_1  = rs.getFloat    ( "teorico_ingr_1"   );
            float  teorico_ingr_2  = rs.getFloat    ( "teorico_ingr_2"   );
            float  teorico_ingr_3  = rs.getFloat    ( "teorico_ingr_3"   );
            float  real_ingr_1     = rs.getFloat    ( "real_ingr_1"      );
            float  real_ingr_2     = rs.getFloat    ( "real_ingr_2"      );
            float  real_ingr_3     = rs.getFloat    ( "real_ingr_3"      );

            Object [] fila = { idoperacion, fecha, hora, idformula, idcamion,
                               teorico_ingr_1, teorico_ingr_2, teorico_ingr_3,
                               real_ingr_1, real_ingr_2, real_ingr_3 };           
            return fila;
        } else if ( moduloActual.equals ( TIT_MODULO_3 ) ) {
            int    idformula           = rs.getInt    ( "idformula"          );
            String codigo              = rs.getString ( "codigo"             );
            String descripcion         = rs.getString ( "descripcion"        );
            int    cant_ingrediente_1  = rs.getInt    ( "cant_ingrediente_1" );
            int    cant_ingrediente_2  = rs.getInt    ( "cant_ingrediente_2" );
            int    cant_ingrediente_3  = rs.getInt    ( "cant_ingrediente_3" );
            
            Object [] fila = { idformula, codigo, descripcion, cant_ingrediente_1,
                                cant_ingrediente_2, cant_ingrediente_3 };           
            return fila;
        } else
            return null;
    }    
    
    //--------------------------------------------------------------------------
    
    private void mostrarMensajeDelSistema ( String mensaje ) {
        jlblMensajeDelSistema.setText ( mensaje );
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
            java.util.logging.Logger.getLogger(PrincipalFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PrincipalFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PrincipalFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PrincipalFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PrincipalFrame().setVisible(false);
            }
        });
    }

    //--------------------------------------------------------------------------
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JButton jbtnEditar;
    private javax.swing.JButton jbtnEliminar;
    private javax.swing.JButton jbtnFiltrar;
    private javax.swing.JButton jbtnInicio;
    private javax.swing.JButton jbtnModulo1;
    private javax.swing.JButton jbtnModulo2;
    private javax.swing.JButton jbtnModulo3;
    private javax.swing.JButton jbtnModulo4;
    private javax.swing.JButton jbtnNuevo;
    private javax.swing.JLabel jlblBotonModulo1;
    private javax.swing.JLabel jlblBotonModulo2;
    private javax.swing.JLabel jlblBotonModulo3;
    private javax.swing.JLabel jlblBotonModulo4;
    private javax.swing.JLabel jlblEstatusConexion;
    private javax.swing.JLabel jlblLeyendaPrincipal;
    private javax.swing.JLabel jlblMensajeDelSistema;
    private javax.swing.JMenuBar jmbrPrincipal;
    private javax.swing.JMenuItem jmniAcercaDe;
    private javax.swing.JMenuItem jmniEditar;
    private javax.swing.JMenuItem jmniEliminar;
    private javax.swing.JMenu jmniEstatusConexion;
    private javax.swing.JMenuItem jmniNuevo;
    private javax.swing.JMenuItem jmniRecalcPromedios;
    private javax.swing.JMenuItem jmniReportesModulo1;
    private javax.swing.JMenuItem jmniReportesModulo2;
    private javax.swing.JMenuItem jmniReportesModulo3;
    private javax.swing.JMenuItem jmniSalir;
    private javax.swing.JMenu jmnuAyuda;
    private javax.swing.JPanel jpnlLogo;
    private javax.swing.JPanel jpnlTabla;
    private javax.swing.JPopupMenu jpopmPrincipal;
    private javax.swing.JTable jtblPrincipal;
    private javax.swing.JToolBar jtoolbPrincipal;
    // End of variables declaration//GEN-END:variables

    
    //--------------------------------------------------------------------------

    public Vector<String> getVecNombresColumnas() {
        return vecNombresColumnas;
    }

    //--------------------------------------------------------------------------
    
    public void setVecNombresColumnas(Vector<String> vecNombresColumnas) {
        this.vecNombresColumnas = vecNombresColumnas;
    }

    //--------------------------------------------------------------------------
    
    public Vector<String> getVecTiposColumnas() {
        return vecTiposColumnas;
    }

    //--------------------------------------------------------------------------
    
    public void setVecTiposColumnas(Vector<String> vecTiposColumnas) {
        this.vecTiposColumnas = vecTiposColumnas;
    }

    //--------------------------------------------------------------------------
    
    public Properties getPropSentenciasSQL() {
        return propSentenciasSQL;
    }

    //--------------------------------------------------------------------------
    
    public void setPropSentenciasSQL(Properties propSentenciasSQL) {
        this.propSentenciasSQL = propSentenciasSQL;
    }

    //--------------------------------------------------------------------------
    
    public String getModuloActual() {
        return moduloActual;
    }

    public void setModuloActual(String moduloActual) {
        this.moduloActual = moduloActual;
    }

    //--------------------------------------------------------------------------
    
    public Vector<String> getVecNombresColumnasBD() {
        return vecNombresColumnasBD;
    }

    //--------------------------------------------------------------------------
    
    public void setVecNombresColumnasBD(Vector<String> vecNombresColumnasBD) {
        this.vecNombresColumnasBD = vecNombresColumnasBD;
    }    
    
    //--------------------------------------------------------------------------
    
    public JButton getJbtnModulo1() {
        return jbtnModulo1;
    }

    //--------------------------------------------------------------------------
    
    public JButton getJbtnModulo2() {
        return jbtnModulo2;
    }

    //--------------------------------------------------------------------------
    
    public JButton getJbtnModulo3() {
        return jbtnModulo3;
    }
    
    
}

