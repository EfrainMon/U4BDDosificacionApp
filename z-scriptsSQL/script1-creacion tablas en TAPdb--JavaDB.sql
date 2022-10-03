/*------------------------------------------------------------------------------------------
:*                       INSTITUTO TECNOLOGICO DE LA LAGUNA
:*                     INGENIERIA EN SISTEMAS COMPUTACIONALES
:*                       TOPICOS AVANZADOS DE PROGRAMACION "B"
:*
:*                   SEMESTRE: ENE-JUN/2020    HORA: 17-18 HRS
:*
:*                 Script de Creacion de Tablas de la BD TAPdb
:*        
:*  Archivo     : script-creacion tablas en bd TAPdb en JavaDB.sql
:*  Autor       : Fernando Gil          85360673
:*  Fecha       : 04/May/2020
:*  Compilador  : 
:*  Descripción : Script SQL para crear las tablas Alumnos, Materias y Kardex
:*                y poblarlas con registros de prueba.
:*  Ultima modif:
:*  Fecha       Modificó             Motivo
:*========================================================================================== 
:*   
:*------------------------------------------------------------------------------------------*/

/* CREACION DE TABLAS                                                         */     
create table Alumnos (
   MATRICULA VARCHAR ( 10 ) primary key,
   NOMBRE    VARCHAR ( 30 ) not null,
   APELLIDOS VARCHAR ( 50 ) not null,
   EDAD      INTEGER,
   PROMEDIO  REAL
);

create table Materias (
   IDMATERIA VARCHAR ( 10 ) primary key,
   MATERIA   VARCHAR ( 50 ) not null,
   CREDITOS  INTEGER
);

create table Kardex (
   IDCALIFIC    INTEGER primary key,
   MATRICULA    VARCHAR ( 10 ),
   IDMATERIA    VARCHAR ( 10 ),
   PERIODO      VARCHAR (  5 ),
   CALIFICACION INTEGER
);

/* CREACION DE LLAVES FORANEAS PARA LA INTEGRIDAD REFERENCIAL                 */
alter table Kardex add constraint fk_matricula foreign key ( MATRICULA ) references  Alumnos  ( MATRICULA );
alter table Kardex add constraint fk_idmateria foreign key ( IDMATERIA ) references  Materias ( IDMATERIA );

