# Bitácora individual - Semana 03

## 1. Datos de la actividad

- **Estudiante:** Julian Hernandez
- **Equipo:** Trabajo individual
- **Semana:** 03
- **Fecha del laboratorio:** 2026-09-21
- **Fecha del taller:** 2026-09-21
- **Tema principal:** Búsqueda lineal, búsqueda binaria y análisis de eficiencia
- **Pregunta de la semana:** ¿Cómo encontramos una lectura específica cuando el repositorio pasa de cientos a cientos de miles o millones de registros?

## 2. Predicción antes de ejecutar

### 1. ¿Qué creo que va a ocurrir?

Antes de realizar los experimentos esperaba que la búsqueda binaria necesitara muchas menos comparaciones que la búsqueda lineal, especialmente cuando aumentara la cantidad de registros.

También esperaba que la búsqueda lineal necesitara recorrer prácticamente todo el arreglo cuando el dato estuviera al final.

### 2. ¿Qué parte del programa o del algoritmo puede fallar?

Una parte que podía fallar era la búsqueda binaria si los datos no estaban ordenados por el campo utilizado para buscar.

También era importante revisar correctamente la actualización de los límites `inicio` y `fin`, porque si estos no avanzaban se podía producir un ciclo infinito.

### 3. ¿Cómo comprobaré mi predicción?

Generaría arreglos de diferentes tamaños y buscaría datos que estuvieran al final del arreglo.

Compararía la cantidad de operaciones de búsqueda lineal y binaria para:

- 1.000 registros
- 100.000 registros
- 1.000.000 de registros

También probaría datos existentes, inexistentes y búsquedas sobre PM2.5.

## 3. Evidencia del laboratorio

### Resultado observado

Al ejecutar el proyecto, la ingesta de las semanas anteriores continuó funcionando correctamente:

```text
Lecturas almacenadas: 201
Descartadas por formato: 2
Descartadas por rango: 8
```

En la búsqueda lineal se obtuvieron los siguientes resultados:

| Tamaño | Comparaciones |
|---:|---:|
| 1.000 | 1.000 |
| 100.000 | 100.000 |
| 1.000.000 | 1.000.000 |

En la comparación entre búsqueda lineal y binaria se obtuvieron estos resultados:

| Tamaño | Lineal | Binaria | Tiempo lineal | Tiempo binaria |
|---:|---:|---:|---:|---:|
| 1.000 | 1.000 | 10 | 0,037 ms | 0,043 ms |
| 100.000 | 100.000 | 17 | 1,894 ms | 0,034 ms |
| 1.000.000 | 1.000.000 | 20 | 9,850 ms | 0,051 ms |

Para un timestamp inexistente en un arreglo de 100.000 elementos:

```text
Búsqueda lineal: 100000 comparaciones
Búsqueda binaria: 17 comparaciones
```

En el experimento de PM2.5:

```text
Valores existentes buscados: 20
Encontrados por búsqueda lineal: 20
Encontrados por búsqueda binaria: 0
```

También se realizaron pruebas mínimas de búsqueda binaria:

```text
Primer elemento: 0
Elemento intermedio: 5
Último elemento: 9
Elemento inexistente: -1
Elemento existente en arreglo grande: 500000
```

### Diferencia entre la predicción y el resultado

La predicción principal sí se cumplió.

La búsqueda binaria necesitó una cantidad mucho menor de comparaciones que la búsqueda lineal.

Por ejemplo, con un millón de registros la búsqueda lineal necesitó 1.000.000 de comparaciones mientras que la búsqueda binaria solamente necesitó 20.

Sin embargo, también se comprobó que ser más rápida no significa que siempre pueda utilizarse. En el experimento de PM2.5 la búsqueda binaria no encontró ninguno de los 20 valores existentes porque los datos no estaban ordenados por PM2.5.

### Error o comportamiento inesperado

- **¿Qué ocurrió?**  
  La búsqueda binaria por PM2.5 encontró 0 de los 20 valores que sí existían.

- **¿Por qué ocurrió?**  
  Porque los valores de PM2.5 fueron generados de forma aleatoria y el arreglo no estaba ordenado por ese campo. La búsqueda binaria necesita que los datos estén ordenados según el atributo que se está utilizando para buscar.

- **¿Cómo lo corregimos o qué falta corregir?**  
  No se debe utilizar búsqueda binaria directamente sobre PM2.5 mientras los datos no estén ordenados por ese campo. Una posible solución futura sería ordenar primero los registros, tema que se relaciona con la Semana 4.

## 4. Explicación en lenguaje llano

La búsqueda lineal revisa los datos uno por uno hasta encontrar lo que necesita.

La búsqueda binaria funciona diferente: revisa un punto intermedio y descarta una parte de los datos en cada paso.

Por eso puede encontrar información con muchas menos comparaciones.

Pero para poder hacerlo correctamente necesita que los datos estén ordenados.

### Ejemplo o analogía

La búsqueda lineal es como buscar una palabra en un montón de tarjetas desordenadas revisándolas una por una.

La búsqueda binaria es como buscar una palabra en un diccionario. Como las palabras están ordenadas, podemos abrir por la mitad y decidir si debemos buscar hacia adelante o hacia atrás.

La analogía deja de funcionar si el diccionario estuviera desordenado, porque ya no podríamos descartar correctamente una mitad.

## 5. El vacío que encontré

- **Mi duda concreta es:**  
  ¿Por qué una búsqueda binaria puede estar correctamente programada y aun así no encontrar un dato que sí existe?

- **Lo que ya puedo explicar es:**  
  Puedo explicar que la búsqueda binaria divide progresivamente el espacio de búsqueda y tiene una complejidad aproximada de O(log n).

- **Para resolver la duda consulté:**  
  La guía de la Semana 3 y el experimento realizado con valores de PM2.5.

- **Ahora lo entiendo así:**  
  Un algoritmo no depende solamente de que su código esté bien escrito. También existen precondiciones que deben cumplirse. En la búsqueda binaria, los datos deben estar ordenados por el campo utilizado en la búsqueda. Si esta condición no se cumple, el algoritmo puede descartar una parte del arreglo donde realmente se encontraba el dato.

## 6. Trazado de la solución

Se realizó una traza de búsqueda binaria utilizando el arreglo:

```text
[0, 1, 2, 3]
```

Buscando el valor:

```text
3
```

| Paso | Estado de los datos o estructura | Decisión o resultado |
|---|---|---|
| 1 | inicio = 0, fin = 3, medio = 1, valor = 1 | Como 1 < 3, se continúa hacia la derecha. inicio = medio + 1 = 2 |
| 2 | inicio = 2, fin = 3, medio = 2, valor = 2 | Como 2 < 3, se continúa hacia la derecha. inicio = medio + 1 = 3 |
| 3 | inicio = 3, fin = 3, medio = 3, valor = 3 | El valor coincide con el objetivo. Dato encontrado |
| 4 | Búsqueda terminada | El dato fue encontrado en la posición 3 |

La actualización correcta es:

```java
inicio = medio + 1;
```

El `+1` es necesario porque la posición `medio` ya fue comparada.

Si se utilizara solamente:

```java
inicio = medio;
```

el intervalo podría dejar de avanzar y producir un ciclo infinito.

## 7. Decisión de diseño

- **Problema que debíamos resolver:**  
  Encontrar lecturas específicas cuando la cantidad de registros aumenta desde cientos hasta cientos de miles o millones.

- **Estructura, algoritmo o estrategia elegida:**  
  Utilizar búsqueda binaria para las consultas por timestamp cuando los datos se encuentran ordenados cronológicamente.

- **Alternativa descartada:**  
  Utilizar siempre búsqueda lineal para todas las consultas.

- **¿Por qué elegimos la primera?**  
  Porque la búsqueda binaria reduce considerablemente la cantidad de comparaciones cuando existen muchos registros. Sin embargo, tiene el costo de necesitar datos ordenados.

- **¿Qué evidencia respalda la decisión?**  
  Con 1.000.000 de registros, la búsqueda lineal realizó 1.000.000 de comparaciones mientras que la búsqueda binaria realizó solamente 20.

También se comprobó que la búsqueda binaria no debe utilizarse sobre PM2.5 mientras esos valores no estén ordenados.

## 8. Aporte al proyecto

- **Archivo(s) o módulo(s) trabajado(s):**
    - `../src/BuscadorLecturas.java`
    - `../src/GeneradorDatos.java`
    - `../src/BancoDePruebas.java`
    - `../src/IngestaSensores.java`
    - `traza_busqueda_binaria.md`
    - `bitacoras/s03-julian-hernandez.md`

- **Cambio realizado:**  
  Se incorporaron métodos de búsqueda lineal y búsqueda binaria, generación de datos sintéticos, experimentos para comparar eficiencia, pruebas mínimas y una traza manual de búsqueda binaria.

- **¿Cómo se conecta con la capa anterior?**  
  Las semanas anteriores permitieron validar y almacenar las lecturas. La Semana 3 agrega la capacidad de buscar información dentro de esas estructuras y analizar cuánto cuesta realizar esas búsquedas.

- **¿Qué queda pendiente para la siguiente semana?**  
  Estudiar cómo ordenar los datos y analizar el costo de mantenerlos organizados para poder utilizar algoritmos como la búsqueda binaria.

## 9. Commits realizados

| Commit | Mensaje | Qué demuestra |
|---|---|---|
| `54e9ab8` | `feat: agregar busqueda lineal por timestamp` | Implementación inicial de búsqueda lineal |
| `c62454f` | `feat: generar datos sinteticos ordenados` | Generación de conjuntos de prueba ordenados por timestamp |
| `76f4db5` | `test: agregar experimento de busqueda lineal` | Experimento para medir el peor caso de búsqueda lineal |
| `0bbf635` | `feat: implementar busqueda binaria y busqueda por estacion` | Implementación de búsqueda binaria y comparación correcta de estaciones |
| `3da838e` | `test: agregar experimentos de busqueda` | Integración de experimentos de comparación y PM2.5 |
| `de587c3` | `docs: agregar traza de busqueda binaria` | Evidencia manual del funcionamiento de la búsqueda binaria |
| `af89bf3` | `test: agregar pruebas minimas de busqueda binaria` | Pruebas del primer, intermedio, último, inexistente y arreglo grande |
| `e1e6436` | `test: medir tiempos de busqueda lineal y binaria` | Medición experimental de tiempos de ambas búsquedas |
| `d279eb5` | `docs: agregar bitacora individual semana 3` | Registro de la reflexión individual de la semana |

## 10. Reexplicación final

La búsqueda lineal funciona sin necesitar los datos ordenados, pero su costo aumenta directamente con la cantidad de registros.

La búsqueda binaria puede encontrar un dato con muchas menos comparaciones porque descarta partes completas del arreglo.

Sin embargo, solo puede utilizarse de forma confiable cuando los datos están ordenados por el campo buscado.

Por eso la elección de un algoritmo debe considerar tanto su eficiencia como las condiciones necesarias para que produzca resultados correctos.

## 11. Reflexión individual

### 1. Lo que ahora puedo hacer y antes no podía

Ahora puedo diferenciar una búsqueda lineal de una búsqueda binaria, implementar ambas y comparar su eficiencia utilizando el número de comparaciones y los tiempos de ejecución.

### 2. El error o supuesto que más me enseñó

El caso que más me enseñó fue la búsqueda binaria por PM2.5. Al principio podría parecer que si el algoritmo está bien escrito debería encontrar los datos, pero aprendí que también es necesario verificar que se cumplan sus precondiciones.

### 3. La pregunta que llevaría a la próxima clase

¿Cómo podemos mantener los datos ordenados sin tener que ordenar completamente todo el arreglo cada vez que se agrega o modifica una lectura?

### 4. ¿Qué parte del trabajo fue realmente mía?

Realicé la integración de los métodos de búsqueda en el proyecto, ejecuté y verifiqué los experimentos, registré las mediciones obtenidas en mi computador, realicé las pruebas mínimas, construí la traza de búsqueda binaria y trabajé con Git utilizando una rama de funcionalidad antes de integrar los cambios a `main`.

## Lista de verificación antes de entregar

- [x] Escribí una predicción sobre el comportamiento esperado.
- [x] Incluí evidencia concreta del laboratorio.
- [x] Expliqué el concepto sin depender de jerga.
- [x] Registré una duda y cómo la resolví.
- [x] Tracé al menos un caso paso a paso.
- [x] Justifiqué una decisión del proyecto y una alternativa descartada.
- [x] Registré mis commits y mi aporte individual.
- [x] Dejé claro qué queda pendiente.
- [x] Renombré el archivo con el formato `s03-julian-hernandez.md`.

---

# Preguntas de pensamiento crítico de la Semana 3

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