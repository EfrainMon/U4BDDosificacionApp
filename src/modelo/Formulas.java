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
:*  Archivo     : Formulas.java
:*  Autor(es)   : Efrain Montalvo Sánchez           20130810
:*              : Aranza Abigail Gassós Salazar     19130889
:*              : Tomás Alejandro Galván Gándara    20130770
:*  Fecha       : 08/May/2022
:*  Compilador  :  
:*  Descripción : Clase que contiene los métodos get y set de los atributos de 
:*                la tabla FORMULAS usados para la base de datos DosificaciónDB.
:*                
:*                
:*  Ultima modif: 08/May/2022
:*  Fecha        Modifico                 Motivo
:*========================================================================================== 
:*   
:*------------------------------------------------------------------------------------------*/


package modelo;

//-----------------------------------------------------------------------------

public class Formulas {
    private int IDFORMULA;
    private String CODIGO;
    private String DESCRIPCION;
    private int    CANT_INGREDIENTE_1;
    private int    CANT_INGREDIENTE_2;
    private int    CANT_INGREDIENTE_3;

    //--------------------------------------------------------------------------
    
    public Formulas ( int IDFORMULA, String CODIGO, String DESCRIPCION, 
           int CANT_INGREDIENTE_1, int CANT_INGREDIENTE_2, int CANT_INGREDIENTE_3 ) {
        this.IDFORMULA           = IDFORMULA;
        this.CODIGO              = CODIGO;
        this.DESCRIPCION         = DESCRIPCION;
        this.CANT_INGREDIENTE_1  = CANT_INGREDIENTE_1;
        this.CANT_INGREDIENTE_2  = CANT_INGREDIENTE_2;
        this.CANT_INGREDIENTE_3  = CANT_INGREDIENTE_3;
    }

    //--------------------------------------------------------------------------
    
    public int getIDFORMULA() {
        return IDFORMULA;
    }

    //--------------------------------------------------------------------------
    
    public void setIDFORMULA( int IDFORMULA ) {
        this.IDFORMULA = IDFORMULA;
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
    
    public int getCANT_INGREDIENTE_1() {
        return CANT_INGREDIENTE_1;
    }

    //--------------------------------------------------------------------------
    
    public void setCANT_INGREDIENTE_1( int CANT_INGREDIENTE_1 ) {
        this.CANT_INGREDIENTE_1 = CANT_INGREDIENTE_1;
    }

    //--------------------------------------------------------------------------
    
    public int getCANT_INGREDIENTE_2() {
        return CANT_INGREDIENTE_2;
    }

    //--------------------------------------------------------------------------
    
    public void setCANT_INGREDIENTE_2( int CANT_INGREDIENTE_2 ) {
        this.CANT_INGREDIENTE_2 = CANT_INGREDIENTE_2;
    }

    //--------------------------------------------------------------------------
    
    public int getCANT_INGREDIENTE_3() {
        return CANT_INGREDIENTE_3;
    }

    //--------------------------------------------------------------------------
    
    public void setCANT_INGREDIENTE_3( int CANT_INGREDIENTE_3 ) {
        this.CANT_INGREDIENTE_3 = CANT_INGREDIENTE_3;
    }
    
    //--------------------------------------------------------------------------
    
    @Override
    public String toString () {
        return IDFORMULA + " - " + DESCRIPCION;
    }

}
