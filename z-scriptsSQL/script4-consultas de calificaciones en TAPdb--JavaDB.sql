/*------------------------------------------------------------------------------------------
:*                       INSTITUTO TECNOLOGICO DE LA LAGUNA
:*                     INGENIERIA EN SISTEMAS COMPUTACIONALES
:*                       TOPICOS AVANZADOS DE PROGRAMACION "B"
:*
:*                   SEMESTRE: ENE-JUN/2020    HORA: 17-18 HRS
:*
:*                Script SQL para coselecnsultar calificaciones de alumnos.
:*        
:*  Archivo     : script-consultas de calificaciones.sql
:*  Autor       : Fernando Gil          85360673
:*  Fecha       : 04/May/2020
:*  Compilador  : 
:*  Descripción : Scripts de consulta de calificaciones sobre la tabla Kardex.
:*              
:*  Ultima modif:
:*  Fecha       Modificó             Motivo
:*========================================================================================== 
:*   
:*------------------------------------------------------------------------------------------*/


/* Consultar las calificaciones de todos los alumnos ordenado por matricula   */
select K.MATRICULA, A.NOMBRE, A.APELLIDOS, K.PERIODO, M.MATERIA, K.CALIFICACION 
from Kardex K, Alumnos A, Materias M 
where K.MATRICULA = A.MATRICULA AND K.IDMATERIA = M.IDMATERIA 
order by K.MATRICULA;

/* Consultar las calificaciones de un solo alumno por su matricula            */
select K.MATRICULA, A.NOMBRE, A.APELLIDOS, K.PERIODO, M.MATERIA, K.CALIFICACION 
from Kardex K, Alumnos A, Materias M 
where K.MATRICULA = '17130433' AND K.MATRICULA = A.MATRICULA AND K.IDMATERIA = M.IDMATERIA ;

