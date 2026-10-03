# Bitácora individual - Semana 04

## 1. Datos de la actividad

- **Estudiante:** Julian Hernandez
- **Equipo:** Individual
- **Semana:** 4
- **Fecha del laboratorio:** 2026-10-02
- **Fecha del taller:** 2026-10-02
- **Tema principal:** Algoritmos de ordenamiento y comparación de eficiencia
- **Pregunta de la semana:** ¿Qué algoritmo tiene sentido para mis datos, mi problema y mis restricciones?

## 2. Predicción antes de ejecutar

Antes de realizar los experimentos no dejé registrada formalmente una predicción por escrito. Para no inventarla después de conocer los resultados, dejo constancia de esta omisión.

Lo que se buscó comprobar durante las pruebas fue cómo cambiaba el comportamiento de los algoritmos cuando los datos estaban desordenados o ya venían ordenados, y qué ocurría con QuickSort cuando siempre utilizaba el primer elemento como pivote.

La comprobación se realizó ejecutando los experimentos con diferentes cantidades y estados iniciales de las lecturas, registrando comparaciones, intercambios y tiempos.

## 3. Evidencia del laboratorio

### Resultado observado

En el experimento 1 se utilizaron 10.000 lecturas desordenadas.

| Algoritmo | Comparaciones | Intercambios |
|---|---:|---:|
| Burbuja | 49.990.814 | 24.928.244 |
| Selección | 49.995.000 | 9.994 |
| Inserción | 24.938.233 | 24.928.244 |

En el experimento 2 se utilizaron 10.000 lecturas que ya venían ordenadas cronológicamente.

| Algoritmo | Comparaciones | Intercambios |
|---|---:|---:|
| Burbuja | 9.999 | 0 |
| Selección | 49.995.000 | 0 |
| Inserción | 9.999 | 0 |

El corte temprano de Burbuja permitió terminar después del primer recorrido al detectar que no se realizó ningún intercambio.

En el experimento 3 se comparó el crecimiento de Inserción, MergeSort y HeapSort.

| n | Algoritmo | Comparaciones | Intercambios |
|---:|---|---:|---:|
| 1.000 | Inserción | 242.787 | 241.797 |
| 1.000 | MergeSort | 8.684 | 9.976 |
| 1.000 | HeapSort | 16.786 | 9.065 |
| 10.000 | Inserción | 24.938.233 | 24.928.244 |
| 10.000 | MergeSort | 120.396 | 133.616 |
| 10.000 | HeapSort | 235.434 | 124.208 |
| 100.000 | Inserción | 2.497.222.762 | 2.497.122.770 |
| 100.000 | MergeSort | 1.536.325 | 1.668.928 |
| 100.000 | HeapSort | 3.019.556 | 1.574.970 |

### Razones de crecimiento

Cuando `n` aumentó diez veces, las comparaciones crecieron aproximadamente así:

| Algoritmo | 1.000 → 10.000 | 10.000 → 100.000 |
|---|---:|---:|
| Inserción | ×102,72 | ×100,14 |
| MergeSort | ×13,86 | ×12,76 |
| HeapSort | ×14,03 | ×12,83 |

Esto muestra que Inserción presenta un crecimiento mucho mayor cuando aumenta el tamaño de los datos, mientras MergeSort y HeapSort crecen de una forma más moderada.

En el experimento 4 se estudió QuickSort.

Con 50.000 lecturas desordenadas y utilizando el primer elemento como pivote, QuickSort terminó normalmente.

Cuando las 50.000 lecturas ya estaban ordenadas cronológicamente y se utilizó nuevamente el primer elemento como pivote, se produjo un `StackOverflowError`.

Esto ocurrió porque las particiones quedaron demasiado desbalanceadas y la profundidad de la recursión aumentó excesivamente.

Después se implementó una versión con pivote aleatorio. Al repetir el experimento con las 50.000 lecturas cronológicamente ordenadas, QuickSort pudo finalizar sin producir el error.

Los valores exactos del caso con pivote aleatorio pueden cambiar entre ejecuciones porque el pivote se selecciona de forma aleatoria.

En el experimento 5 se comprobó el efecto de ordenar por un criterio diferente al utilizado por la búsqueda binaria.

Antes de ordenar por PM2.5:

- El arreglo estaba ordenado por timestamp.
- La búsqueda binaria encontró la lectura en la posición 73.412.
- Se realizaron 16 comparaciones.

Después de ordenar el mismo arreglo por PM2.5:

- El arreglo dejó de estar ordenado por timestamp.
- La búsqueda binaria devolvió `-1`.
- La búsqueda lineal encontró la lectura en la posición 87.705.

La lectura nunca desapareció. Lo que dejó de cumplirse fue la precondición de la búsqueda binaria.

### Diferencia entre la predicción y el resultado

No existía una predicción formal registrada antes de ejecutar, por lo que no es correcto afirmar después que los resultados habían sido previstos.

Las pruebas mostraron que el estado inicial de los datos puede modificar de manera importante el comportamiento de un algoritmo.

Los casos más claros fueron:

- Burbuja con datos ya ordenados.
- Inserción con datos ya ordenados.
- QuickSort con pivote en el primer elemento y datos cronológicamente ordenados.
- La búsqueda binaria después de cambiar el orden del arreglo a PM2.5.

### Error o comportamiento inesperado

- **¿Qué ocurrió?**  
  QuickSort produjo un `StackOverflowError` al procesar 50.000 lecturas que ya estaban ordenadas cronológicamente.

- **¿Por qué ocurrió?**  
  La implementación utilizaba siempre el primer elemento como pivote. Al estar los datos ordenados por timestamp, las particiones quedaban muy desbalanceadas y la recursión se hacía demasiado profunda.

- **¿Cómo se corrigió?**  
  Se implementó una alternativa con pivote aleatorio. Al repetir el experimento, QuickSort pudo ordenar las 50.000 lecturas sin producir el error.

## 4. Explicación en lenguaje llano

Ordenar datos es parecido a organizar una fila siguiendo una regla. Algunos métodos revisan muchas veces los elementos, mientras otros dividen el problema en partes más pequeñas. También importa cómo llegan los datos originalmente, porque un algoritmo puede comportarse muy diferente si la información ya viene ordenada. Por eso no basta con decir que un algoritmo es rápido: hay que observar los datos y medir su comportamiento.

### Ejemplo o analogía

Inserción se puede comparar con organizar cartas en la mano.

Si ya tengo varias cartas ordenadas y recibo una nueva, solo tengo que moverla hasta encontrar el lugar correcto.

Si las cartas ya están casi ordenadas, se necesitan pocos movimientos.

Si están completamente mezcladas, se necesita mucho más trabajo.

## 5. El vacío que encontré

- **Mi duda concreta:**  
  ¿Por qué QuickSort puede funcionar correctamente con datos desordenados y producir un `StackOverflowError` cuando los datos ya vienen ordenados?

- **Lo que ya puedo explicar:**  
  QuickSort divide los datos utilizando un pivote y después repite el proceso sobre las partes resultantes.

- **Para resolver la duda consulté:**  
  La guía de Semana 4, el código de `Ordenador.java` y los resultados obtenidos en el experimento 4.

- **Ahora lo entiendo así:**  
  Si siempre se utiliza el primer elemento como pivote y ese elemento queda en un extremo de los datos, una partición puede quedar casi vacía y la otra conservar casi todos los elementos. Esto hace que la recursión sea demasiado profunda. Cambiar la estrategia del pivote evita depender siempre del primer elemento.

## 6. Trazado de la solución

Se seleccionó el experimento 5 porque conecta directamente el ordenamiento de Semana 4 con la búsqueda binaria desarrollada en Semana 3.

| Paso | Estado | Resultado |
|---|---|---|
| 1 | 100.000 lecturas ordenadas por timestamp | `estaOrdenadoPorTimestamp` devuelve `true` |
| 2 | Se busca el timestamp objetivo | La búsqueda binaria encuentra la posición 73.412 |
| 3 | El mismo arreglo se ordena por PM2.5 | El orden por timestamp se pierde |
| 4 | Se repite la búsqueda binaria | Devuelve `-1` |
| 5 | Se utiliza búsqueda lineal | La lectura aparece en la posición 87.705 |

La lectura seguía dentro del arreglo. El problema fue que el arreglo dejó de cumplir la condición necesaria para la búsqueda binaria.

## 7. Decisión de diseño

- **Problema:**  
  Generar un ranking por PM2.5 sin perder la posibilidad de realizar consultas binarias eficientes por timestamp.

- **Estrategia elegida:**  
  Trabajar sobre una copia cuando sea necesario ordenar por PM2.5 y conservar el arreglo original ordenado cronológicamente.

- **Alternativas consideradas:**  
  Restaurar posteriormente el orden por timestamp o mantener estructuras o índices separados.

- **Por qué se eligió esta estrategia:**  
  Permite conservar el orden requerido por la búsqueda binaria sin tener que volver a ordenar después de cada ranking.

- **Costo de la decisión:**  
  Se necesita memoria adicional para mantener la copia.

- **Evidencia:**  
  El experimento 5 mostró que después de ordenar el arreglo por PM2.5, la búsqueda binaria por timestamp devolvió `-1`, aunque la búsqueda lineal confirmó que la lectura seguía existiendo.

## 8. Aporte al proyecto

- **Archivos o módulos trabajados:**  
  `src/Ordenador.java`, `src/BancoDeOrdenamiento.java`, `src/IngestaSensores.java`, `docs/decisiones.md`, `bitacoras/s04-julian-hernandez.md` y `evidencias/grafica_crecimiento_semana4.png`.

- **Cambios realizados:**  
  Se incorporaron algoritmos de ordenamiento simples y avanzados, medición de comparaciones e intercambios, corte temprano de Burbuja, mejora del pivote de QuickSort y cinco experimentos para estudiar eficiencia y precondiciones.

- **Conexión con semanas anteriores:**  
  Semana 4 utiliza las lecturas generadas y los algoritmos de búsqueda de Semana 3. El experimento 5 demuestra directamente cómo cambiar el orden de los datos puede afectar la búsqueda binaria.

- **Integración final:**  
  Los cinco experimentos quedaron integrados dentro de `IngestaSensores.main()` sin crear un segundo punto de entrada.

- **Estado final:**  
  El proyecto fue compilado y ejecutado nuevamente después de integrar Semana 4 a `main`. La ingesta, Semana 3 y los cinco experimentos de Semana 4 funcionaron correctamente.

- **Pendiente:**  
  No queda pendiente una implementación técnica de Semana 4. Solo deben completarse los datos administrativos de esta bitácora que todavía aparezcan como `[Completar]`.

## 9. Commits realizados

| Commit | Mensaje | Qué demuestra |
|---|---|---|
| `4ff1c38` | `feat: agregar algoritmos simples de ordenamiento` | Implementación de Burbuja, Selección e Inserción |
| `2b06720` | `feat: agregar ordenamientos avanzados` | Incorporación de MergeSort, HeapSort y QuickSort |
| `10233b3` | `feat: agregar experimento uno de ordenamiento` | Primer banco de mediciones |
| `6c2a75d` | `fix: agregar corte temprano a burbuja` | Implementación del corte temprano |
| `8a933c1` | `feat: agregar experimento tres de ordenamiento` | Pruebas con 1.000, 10.000 y 100.000 lecturas |
| `15441a2` | `feat: mejorar pivote de quicksort` | Implementación de una alternativa al pivote fijo |
| `9962cef` | `feat: agregar experimento cinco de ordenamiento` | Integración entre ordenamiento y búsqueda |
| `7f55968` | `docs: registrar decisiones de semana cuatro` | Registro de DEC-04 y DEC-05 |
| `817979f` | `docs: actualizar bitacora semana 4` | Creación de la bitácora individual |
| `e3120c2` | `docs: agregar grafica de crecimiento semana 4` | Evidencia gráfica de Inserción y MergeSort |
| `8b81975` | `fix: alinear ordenamientos con datos cronologicos` | Ajuste final del código y de la integración de los experimentos |

## 10. Reexplicación final

> No existe un algoritmo de ordenamiento adecuado para todos los casos. Su comportamiento depende del tamaño, del estado inicial de los datos y del problema que se quiere resolver. Las mediciones mostraron que Inserción crece mucho más rápido que MergeSort y HeapSort cuando aumenta `n`, y que QuickSort puede tener problemas graves si se utiliza una mala estrategia de pivote. También se comprobó que ordenar por PM2.5 puede romper la precondición necesaria para buscar por timestamp.

## 11. Reflexión individual

1. **Lo que ahora puedo hacer y antes no podía:**  
   Puedo comparar algoritmos utilizando mediciones reales de comparaciones, intercambios y crecimiento, en lugar de mirar solamente cuánto tarda una ejecución.

2. **El error o supuesto que más me enseñó:**  
   El `StackOverflowError` de QuickSort mostró que no basta con conocer el rendimiento promedio de un algoritmo. También importa el patrón de los datos que recibe.

3. **La pregunta que llevaría a la próxima clase:**  
   ¿Cómo se pueden mantener búsquedas eficientes por varios criterios diferentes sin tener que reordenar el mismo arreglo constantemente?

4. **Qué parte del trabajo fue realmente mía:**  
   Ejecuté los experimentos, revisé las mediciones obtenidas, comprobé el comportamiento de Burbuja y QuickSort, verifiqué el efecto de ordenar por PM2.5 sobre la búsqueda binaria, integré los experimentos al proyecto y documenté los resultados y decisiones.

## Lista de verificación antes de entregar

- [ ] Escribí la predicción antes de consultar el resultado.
- [x] Incluí evidencia concreta del laboratorio.
- [x] Expliqué un concepto sin depender de jerga.
- [x] Registré un vacío, una duda o un error real.
- [x] Tracé al menos un caso paso a paso.
- [x] Justifiqué una decisión del proyecto y una alternativa descartada.
- [x] Registré mis commits y mi aporte individual.
- [x] Generé la gráfica de crecimiento.
- [x] Calculé las razones de crecimiento.
- [x] Actualicé `docs/decisiones.md`.
- [x] Integré Semana 4 con las semanas anteriores.
- [x] Mantuve un único `main()`.
- [x] Validé el proyecto después del merge a `main`.
- [x] Subí `main` al repositorio remoto.
- [x] Creé y subí la etiqueta `H1`.
- [x] Renombré la bitácora con el formato `sXX-nombre.md`.