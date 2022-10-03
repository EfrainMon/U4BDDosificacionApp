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
:*  Archivo     : Operaciones.java
:*  Autor(es)   : Efrain Montalvo Sánchez           20130810
:*              : Aranza Abigail Gassós Salazar     19130889
:*              : Tomás Alejandro Galván Gándara    20130770
:*  Fecha       : 08/May/2022
:*  Compilador  :  
:*  Descripción : Clase que contiene los métodos get y set de los atributos de 
:*                la tabla OPERACIONES usados para la base de datos DosificaciónDB.
:*                
:*                
:*  Ultima modif: 08/May/2022
:*  Fecha        Modifico                 Motivo
:*========================================================================================== 
:*   
:*------------------------------------------------------------------------------------------*/


package modelo;

//------------------------------------------------------------------------------

public class Operaciones {
    private int     IDOPERACION;
    private String   FECHA;
    private String   HORA;
    private int      IDFORMULA;
    private int      IDCAMION;
    private float      TEORICO_INGR_1;
    private float      TEORICO_INGR_2;
    private float      TEORICO_INGR_3;
    private float      REAL_INGR_1;
    private float      REAL_INGR_2;
    private float      REAL_INGR_3;

    //--------------------------------------------------------------------------
    
    public Operaciones ( int IDOPERACION, String FECHA, String HORA, int IDFORMULA,
           int IDCAMION, float TEORICO_INGR_1, float TEORICO_INGR_2, float TEORICO_INGR_3,
           float REAL_INGR_1, float REAL_INGR_2, float REAL_INGR_3 ) {
        
        this.IDOPERACION = IDOPERACION;
        this.FECHA = FECHA;
        this.HORA = HORA;
        this.IDFORMULA = IDFORMULA;
        this.IDCAMION = IDCAMION;
        this.TEORICO_INGR_1 = TEORICO_INGR_1;
        this.TEORICO_INGR_2 = TEORICO_INGR_2;
        this.TEORICO_INGR_3 = TEORICO_INGR_3;
        this.REAL_INGR_1 = REAL_INGR_1;
        this.REAL_INGR_2 = REAL_INGR_2;
        this.REAL_INGR_3 = REAL_INGR_3;
    }

    //--------------------------------------------------------------------------
    
    public int getIDOPERACION() {
        return IDOPERACION;
    }

    //--------------------------------------------------------------------------
    
    public void setIDOPERACION( int IDOPERACION ) {
        this.IDOPERACION = IDOPERACION;
    }

    //--------------------------------------------------------------------------
    
    public String getFECHA() {
        return FECHA;
    }

    //--------------------------------------------------------------------------
    
    public void setFECHA( String FECHA ) {
        this.FECHA = FECHA;
    }

    //--------------------------------------------------------------------------
    
    public String getHORA() {
        return HORA;
    }

    //--------------------------------------------------------------------------
    
    public void setHORA( String HORA ) {
        this.HORA = HORA;
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
    
    public int getIDCAMION() {
        return IDCAMION;
    }

    //--------------------------------------------------------------------------
    
    public void setIDCAMION( int IDCAMION ) {
        this.IDCAMION = IDCAMION;
    }

    //--------------------------------------------------------------------------
    
    public float getTEORICO_INGR_1() {
        return TEORICO_INGR_1;
    }

    //--------------------------------------------------------------------------
    
    public void setTEORICO_INGR_1( float TEORICO_INGR_1 ) {
        this.TEORICO_INGR_1 = TEORICO_INGR_1;
    }

    //--------------------------------------------------------------------------
    
    public float getTEORICO_INGR_2() {
        return TEORICO_INGR_2;
    }

    //--------------------------------------------------------------------------
    
    public void setTEORICO_INGR_2( float TEORICO_INGR_2 ) {
        this.TEORICO_INGR_2 = TEORICO_INGR_2;
    }

    //--------------------------------------------------------------------------
    
    public float getTEORICO_INGR_3() {
        return TEORICO_INGR_3;
    }

    //--------------------------------------------------------------------------
    
    public void setTEORICO_INGR_3( float TEORICO_INGR_3 ) {
        this.TEORICO_INGR_3 = TEORICO_INGR_3;
    }

    //--------------------------------------------------------------------------
    
    public float getREAL_INGR_1() {
        return REAL_INGR_1;
    }

    //--------------------------------------------------------------------------
    
    public void setREAL_INGR_1( float REAL_INGR_1 ) {
        this.REAL_INGR_1 = REAL_INGR_1;
    }

    //--------------------------------------------------------------------------
    
    public float getREAL_INGR_2() {
        return REAL_INGR_2;
    }

    //--------------------------------------------------------------------------
    
    public void setREAL_INGR_2( float REAL_INGR_2 ) {
        this.REAL_INGR_2 = REAL_INGR_2;
    }

    //--------------------------------------------------------------------------
    
    public float getREAL_INGR_3() {
        return REAL_INGR_3;
    }

    //--------------------------------------------------------------------------
    
    public void setREAL_INGR_3( float REAL_INGR_3 ) {
        this.REAL_INGR_3 = REAL_INGR_3;
    }
    
}
