/*------------------------------------------------------------------------------------------
:*                         TECNOLOGICO NACIONAL DE MEXICO
:*                               CAMPUS LA LAGUNA
:*                     INGENIERIA EN SISTEMAS COMPUTACIONALES
:*                       TOPICOS AVANZADOS DE PROGRAMACION "B"
:*
:*                   SEMESTRE: ENE-JUN/2022    HORA: 17-18 HRS
:*
:*            	 Clase para ejecutar datos a la Base de Datos
:*        
:*  Archivo     : EjecutorSQL.java
:*  Autor(es)   : Efrain Montalvo Sánchez           20130810
:*              : Aranza Abigail Gassós Salazar     19130889
:*              : Tomás Alejandro Galván Gándara    20130770
:*  Fecha       : 08/May/2022
:*  Compilador  :  
:*  Descripción : Clase usada para ejecutar y mandar los datos ingresados 
:*                a la Base de Datos.
:*
:*  Ultima modif: 08/May/2022
:*  Fecha        Modifico                 Motivo
:*========================================================================================== 
:*   
:*------------------------------------------------------------------------------------------*/


package app;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jdbc.ConexionDB;


public class EjecutorSQL {
    
    public static final String STRING          = "string";
    public static final String INT             = "int";
    public static final String FLOAT           = "float";
    
    
    //--------------------------------------------------------------------------
    
    // Ejecucion de una sentencia sql directa, no parametrizada, es decir que
    // no requiere sustituir argumentos en ella.
    
    public static ResultSet sqlQuery ( String sql ) throws SQLException {
        return sqlQuery ( sql, null );
    }
    
    //--------------------------------------------------------------------------
   
    public static ResultSet sqlQuery ( String sql, Object [][] args ) throws SQLException {

        PreparedStatement ps = ConexionDB.getInstancia().getConexion()
                                         .prepareStatement( sql );
        prepararArgumentos ( ps, args );
        return ps.executeQuery();
    }    
    
    //--------------------------------------------------------------------------
    
    public static int sqlEjecutar ( String sql ) throws SQLException {
        return sqlEjecutar ( sql, null );
    }

    //--------------------------------------------------------------------------
    
    public static int sqlEjecutar ( String sql, Object [][] args ) throws SQLException {
        PreparedStatement ps = ConexionDB.getInstancia().getConexion()
                                         .prepareStatement( sql );        
        prepararArgumentos ( ps, args );
        return ps.executeUpdate ();        
    }
    
    //--------------------------------------------------------------------------
    
    public static void prepararArgumentos ( PreparedStatement ps, Object [][] args ) 
                                                           throws SQLException {
        final int TIPO  = 0;
        final int VALOR = 1;
        
        if ( args != null ) {
            int numArg = 1;
            for ( Object [] arg : args ) {
                switch ( arg [ TIPO ].toString() ) {
                    case INT     : ps.setInt    ( numArg, 
                                   Integer.parseInt ( arg [ VALOR ].toString() ) );
                                   break;
                    case FLOAT   : ps.setFloat  ( numArg, 
                                   Float.parseFloat ( arg [ VALOR ].toString() ) ); 
                                   break;
                    case STRING  : ps.setString ( numArg, arg [ VALOR ].toString() ); 
                                   break;
                }
                numArg++;
            }
        }        
    }
    
}
