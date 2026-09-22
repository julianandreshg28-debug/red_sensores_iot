# Bitácora individual — Semana 3

Nombre: Julian Hernandez

## Pregunta 1

No necesariamente. Aunque la búsqueda binaria es mucho más rápida que la búsqueda lineal, para poder utilizarla los datos deben mantenerse ordenados.

Si solo se realizan cinco búsquedas en todo el día, puede que el costo de ordenar y mantener los datos ordenados sea mayor que el beneficio obtenido en esas pocas búsquedas.

También habría que considerar cuánto cuesta insertar nuevos datos, modificar registros, mantener el orden y cuánto tiempo tarda el proceso de ordenamiento.

Por eso, antes de escoger una estrategia no solo se debe pensar en qué algoritmo busca más rápido, sino también en cuántas búsquedas se realizan y cuánto cuesta mantener los datos preparados para ese algoritmo.

## Pregunta 2

La corrección debe analizarse antes que la eficiencia porque no sirve de mucho que un algoritmo sea muy rápido si entrega un resultado incorrecto.

En esta semana se vio un ejemplo claro con la búsqueda binaria por PM2.5. El algoritmo estaba bien implementado, pero los datos no estaban ordenados por ese campo. Como no se cumplía la precondición, la búsqueda binaria podía fallar aunque fuera más eficiente.

Primero debemos asegurarnos de que el algoritmo produzca resultados correctos en las condiciones reales del problema. Después de eso tiene sentido comparar tiempos, número de operaciones y eficiencia.

## Pregunta 3

Si la plataforma realiza consultas constantemente por timestamp, tendría sentido organizar los datos principalmente por ese campo, porque así la búsqueda binaria sería rápida y eficiente.

El problema es que organizar los datos por timestamp no significa que también estén ordenados por PM2.5. Por eso, una búsqueda binaria por PM2.5 no sería confiable si los datos no se reorganizan previamente por ese valor.

Esto muestra que organizar los datos para optimizar un tipo de consulta puede hacer menos conveniente otro tipo de búsqueda. La decisión depende de cuáles consultas son más frecuentes y más importantes para el funcionamiento de la plataforma.

En este caso, si la mayoría de consultas son por timestamp, tendría más sentido priorizar ese criterio de organización y utilizar otra estrategia para las consultas ocasionales por PM2.5.

## Pregunta 4

Si algunos registros se modifican y el orden de los datos deja de mantenerse, la búsqueda binaria puede comenzar a dar resultados incorrectos.

El problema es que la búsqueda binaria toma decisiones suponiendo que los datos están ordenados. Si esa condición deja de cumplirse, el algoritmo puede descartar una parte del arreglo donde realmente sí se encuentra el dato.

Esto puede hacer que una lectura existente aparezca como si no existiera, aunque el algoritmo esté bien programado.

Por eso, antes de utilizar búsqueda binaria, el sistema debe asegurarse de que la precondición de ordenamiento siga siendo verdadera.

## Pregunta 5

Durante las primeras semanas del proyecto entendí que no basta con que el programa simplemente funcione.

En la Semana 1 aprendimos que los datos deben validarse antes de utilizarlos, porque un programa puede seguir ejecutándose y aun así trabajar con información incorrecta.

En la Semana 2 vimos que también importa cómo se almacenan los datos. Una estructura mal diseñada puede perder información, dificultar las consultas o producir resultados equivocados.

En la Semana 3 aprendimos que además de funcionar correctamente, también debemos pensar en la eficiencia de los algoritmos y en las condiciones que necesitan para funcionar bien. La búsqueda binaria, por ejemplo, es muy eficiente, pero solo si los datos están ordenados.

Ahora analizo una solución considerando no solo si produce una respuesta, sino también si esa respuesta es correcta, si la estructura utilizada es adecuada, si el algoritmo cumple sus precondiciones y si la solución puede mantenerse y escalar cuando aumenta la cantidad de datos.