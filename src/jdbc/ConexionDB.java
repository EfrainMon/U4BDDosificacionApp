/*------------------------------------------------------------------------------------------
:*                         TECNOLOGICO NACIONAL DE MEXICO
:*                               CAMPUS LA LAGUNA
:*                     INGENIERIA EN SISTEMAS COMPUTACIONALES
:*                       TOPICOS AVANZADOS DE PROGRAMACION "B"
:*
:*                   SEMESTRE: ENE-JUN/2022    HORA: 17-18 HRS
:*
:*                  Clase para hacer conexion a una Base de Datos
:*        
:*  Archivo     : ConexionDB.java
:*  Autor(es)   : Efrain Montalvo Sánchez           20130810
:*              : Aranza Abigail Gassós Salazar     19130889
:*              : Tomás Alejandro Galván Gándara    20130770
:*  Fecha       : 08/May/2022
:*  Compilador  :  
:*  Descripción : Clase que permite hacer una conexión a una Base de Datos. 
:*                
:*  Ultima modif: 08/May/2022
:*  Fecha        Modifico                 Motivo
:*========================================================================================== 
:*   
:*------------------------------------------------------------------------------------------*/


package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//---------------------------------------------------------------------------

public class ConexionDB {
  
   public static final String JAVADB    = "Java DB";
   public static final String SQLSERVER = "SQL Server";
   public static final String MYSQL     = "MySQL";
   public static final String ORACLEXE  = "Oracle XE";
   public static final String ACCESS    = "MS Access";
    
   private static ConexionDB instancia = null;
   private Connection conexion;   
  
   private static String driverJDBC     = "org.apache.derby.jdbc.ClientDriver";
   private static String urlConexion    = "jdbc:derby://localhost:1527/";
   private static String servidor       = "";
   private static String puerto         = "";
   private static String baseDeDatos    = "DosificacionDB";
   private static String usuario        = "tap";
   private static String contrasena     = "2022";   
   
   private ConexionDB () {
   }

   //---------------------------------------------------------------------------
   
   public static ConexionDB getInstancia () {
       if ( instancia == null )
           instancia = new ConexionDB ();
       return instancia;
   }
   
   //---------------------------------------------------------------------------
   
   public void conectar ( String dbms, String servidor, String puerto, String bd,
                          String usuario, String contrasena ) {
   
       ConexionDB.servidor    = servidor;
       ConexionDB.puerto      = puerto;
       ConexionDB.baseDeDatos = bd;
       ConexionDB.usuario     = usuario;
       ConexionDB.contrasena  = contrasena;
       
       // Determinar el Driver JDBC y la URL de Conexion
       if        ( dbms.equals ( JAVADB ) ) {
           driverJDBC     = "org.apache.derby.jdbc.ClientDriver";
           urlConexion    = "jdbc:derby://" + servidor + ":" + puerto + "/"; 
       } else if ( dbms.equals ( SQLSERVER ) ) {
       } else if ( dbms.equals ( MYSQL ) ) {
       } else if ( dbms.equals ( ORACLEXE ) ) {
       } else if ( dbms.equals ( ACCESS ) ) {
       }
       
       // Intentar hacer la conexion con la BD
       conexion = null;
       try {
           // Realizar la conexion a la BD
           Class.forName ( driverJDBC );
           conexion = DriverManager.getConnection ( 
                             urlConexion + baseDeDatos, usuario, contrasena );           
       } catch ( Exception ex ) {
           System.out.println ( ex );
       }       
   }
   
   //---------------------------------------------------------------------------
   
   public Connection getConexion () {
       return conexion;
   }
   
   //---------------------------------------------------------------------------
   
   public void desconectar () {
       if ( conexion != null ) {
           try {       
               conexion.close ();
           } catch (SQLException ex) {
               System.out.println ( ex );
           }
       }
   }
   
   //---------------------------------------------------------------------------
   
   public boolean conectado () {
       if ( conexion != null ) 
           return true;
       return false;
   }
}
