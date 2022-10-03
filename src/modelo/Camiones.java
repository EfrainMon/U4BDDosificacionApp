/*------------------------------------------------------------------------------------------
:*                         TECNOLOGICO NACIONAL DE MEXICO
:*                               CAMPUS LA LAGUNA
:*                     INGENIERIA EN SISTEMAS COMPUTACIONALES
:*                       TOPICOS AVANZADOS DE PROGRAMACION "B"
:*
:*                   SEMESTRE: ENE-JUN/2022    HORA: 17-18 HRS
:*
:*                Clase con métodos get/set de la tabla CAMIONES
:*        
:*  Archivo     : Camiones.java
:*  Autor(es)   : Efrain Montalvo Sánchez           20130810
:*              : Aranza Abigail Gassós Salazar     19130889
:*              : Tomás Alejandro Galván Gándara    20130770
:*  Fecha       : 08/May/2022
:*  Compilador  :  
:*  Descripción : Clase que contiene los métodos get y set de los atributos de 
:*                la tabla CAMIONES usados para la base de datos DosificaciónDB.
:*                
:*                
:*  Ultima modif: 08/May/2022
:*  Fecha        Modifico                 Motivo
:*========================================================================================== 
:*   
:*------------------------------------------------------------------------------------------*/


package modelo;

//------------------------------------------------------------------------------

public class Camiones {
    private int IDCAMION;
    private String CODIGO;
    private String DESCRIPCION;
    private float CAPACIDAD;
    
    //--------------------------------------------------------------------------
    
    public Camiones ( int IDCAMION, String CODIGO, String DESCRIPCION, float CAPACIDAD ) {
        this.IDCAMION    = IDCAMION;
        this.CODIGO      = CODIGO;
        this.DESCRIPCION = DESCRIPCION;
        this.CAPACIDAD   = CAPACIDAD;
    }

    //--------------------------------------------------------------------------
    
    public int getIDCAMION() {
        return IDCAMION;
    }

    //--------------------------------------------------------------------------
    
    public void setIDCAMION( int IDCAMION ) {
        this.IDCAMION = IDCAMION;
    }

    //--------------------------------------------------------------------------
    
    public String getCODIGO() {
        return CODIGO;
    }

    //--------------------------------------------------------------------------
    
    public void setCODIGO( String CODIGO ) {
        this.CODIGO = CODIGO;
    }

    //--------------------------------------------------------------------------
    
    public String getDESCRIPCION() {
        return DESCRIPCION;
    }

    //--------------------------------------------------------------------------
    
    public void setDESCRIPCION( String DESCRIPCION ) {
        this.DESCRIPCION = DESCRIPCION;
    }

    //--------------------------------------------------------------------------
    
    public float getCAPACIDAD() {
        return CAPACIDAD;
    }

    //--------------------------------------------------------------------------
    
    public void setCAPACIDAD( float CAPACIDAD ) {
        this.CAPACIDAD = CAPACIDAD;
    }
    
    //--------------------------------------------------------------------------
    
        @Override
    public String toString () {
        return IDCAMION + " - " + DESCRIPCION;
    }
    
}
