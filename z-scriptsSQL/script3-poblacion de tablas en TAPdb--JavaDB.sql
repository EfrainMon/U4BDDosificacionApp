/*------------------------------------------------------------------------------------------
:*                       INSTITUTO TECNOLOGICO DE LA LAGUNA
:*                     INGENIERIA EN SISTEMAS COMPUTACIONALES
:*                       TOPICOS AVANZADOS DE PROGRAMACION "B"
:*
:*                   SEMESTRE: ENE-JUN/2020    HORA: 17-18 HRS
:*
:*            Script para poblar de registros las Tablas de la BD TAPdb
:*        
:*  Archivo     : script-creacion tablas en bd TAPdb en JavaDB.sql
:*  Autor       : Fernando Gil          85360673
:*  Fecha       : 04/May/2020
:*  Compilador  : 
:*  Descripción : Script SQL para insertar registros de prueba a las tablas Alumnos,
:*                Materias y Kardex. Previo a la insercion se eliminan todos los registros
:*                que hay en las tablas.
:*  Ultima modif:
:*  Fecha       Modificó             Motivo
:*========================================================================================== 
:*   
:*------------------------------------------------------------------------------------------*/


/* VACIAR LAS TABLAS SI TIENEN REGISTROS, EN EL ORDEN ADECUADO                */
delete from Kardex;
delete from alumnos;
delete from materias;

/* POBLAR LAS TABLAS CON REGISTROS DE PRUEBA                                  */
insert into Alumnos values ( '17130856', 'Olivia',   'Olivares Oliveira', 19, 78.25 );
insert into Alumnos values ( '17130433', 'Hernan',   'Hernandez',         21, 82.87 );
insert into Alumnos values ( '18130945', 'Choncho',  'Renigris',          20, 92.14 );

insert into Materias values ( 'ED1',  'Estructura de Datos',     8 );
insert into Materias values ( 'TAP1', 'Topicos Av. de Progr.',   6 );
insert into Materias values ( 'TBD1', 'Taller de B. de D.',     10 );
insert into Materias values ( 'IA1',  'Inteligencia Artificial', 8 );

insert into Kardex   values ( 1, '17130856', 'TBD1', '2020A',  91 );
insert into Kardex   values ( 2, '17130856', 'ED1',  '2020A',  75 );
insert into Kardex   values ( 3, '17130856', 'TAP1', '2020A',  81 );
insert into Kardex   values ( 4, '17130856', 'IA1',  '2020A',  78 );

insert into Kardex   values ( 5, '17130433', 'TBD1', '2020A',  78 );
insert into Kardex   values ( 6, '17130433', 'ED1',  '2020A',  81 );
insert into Kardex   values ( 7, '17130433', 'TAP1', '2020A',  75 );
insert into Kardex   values ( 8, '17130433', 'IA1',  '2020A',  91 );

insert into Kardex   values ( 9, '18130945', 'TBD1', '2020A',  88 );
insert into Kardex   values (10, '18130945', 'ED1',  '2020A',  86 );
insert into Kardex   values (11, '18130945', 'TAP1', '2020A',  94 );
insert into Kardex   values (12, '18130945', 'IA1',  '2020A',  73 );



