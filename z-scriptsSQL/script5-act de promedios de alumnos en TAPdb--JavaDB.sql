select avg ( calificacion ) as prom from kardex where matricula = '17130433'

update alumnos A set promedio = ( select avg (calificacion) as prom from kardex K where K.matricula = A.MATRICULA )
