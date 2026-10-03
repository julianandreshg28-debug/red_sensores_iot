# Bitacora individual - Semana 04

## 1. Datos de la actividad

- **Estudiante:** Julian Hernandez
- **Equipo:** [Completar]
- **Semana:** 4
- **Fecha del laboratorio:** [Completar]
- **Fecha del taller:** [Completar]
- **Tema principal:** Algoritmos de ordenamiento y comparacion de eficiencia
- **Pregunta de la semana:** ¿Que algoritmo tiene sentido para mis datos, mi problema y mis restricciones?

## 2. Prediccion antes de ejecutar

Antes de realizar los experimentos no deje registrada formalmente una prediccion por escrito. Para no inventarla despues de conocer los resultados, dejo constancia de esta omision.

Lo que si queria comprobar durante las pruebas era como cambiaba el comportamiento de los algoritmos cuando los datos estaban desordenados o ya venian ordenados, y si una mala eleccion del pivote podia afectar a QuickSort.

La comprobacion se realizo ejecutando los experimentos con diferentes cantidades y estados iniciales de las lecturas, registrando comparaciones, intercambios y tiempos.

## 3. Evidencia del laboratorio

### Resultado observado

En el primer experimento se utilizaron 10.000 lecturas desordenadas.

- Burbuja realizo 49.990.814 comparaciones, 24.928.244 intercambios y tomo 841 ms.
- Seleccion realizo 49.995.000 comparaciones, 9.994 intercambios y tomo 528 ms.
- Insercion realizo 24.938.233 comparaciones, 24.928.244 intercambios y tomo 183 ms.

Con 10.000 lecturas que ya venian ordenadas cronologicamente:

- Burbuja realizo solamente 9.999 comparaciones y 0 intercambios gracias al corte temprano.
- Seleccion mantuvo 49.995.000 comparaciones y 0 intercambios.
- Insercion realizo 9.999 comparaciones y 0 intercambios.

En el experimento de crecimiento se obtuvieron los siguientes resultados:

| n | Algoritmo | Comparaciones | Intercambios | Tiempo |
|---:|---|---:|---:|---:|
| 1.000 | Insercion | 242.787 | 241.797 | 11 ms |
| 1.000 | MergeSort | 8.684 | 9.976 | 1 ms |
| 1.000 | HeapSort | 16.786 | 9.065 | 1 ms |
| 10.000 | Insercion | 24.938.233 | 24.928.244 | 161 ms |
| 10.000 | MergeSort | 120.396 | 133.616 | 4 ms |
| 10.000 | HeapSort | 235.434 | 124.208 | 5 ms |
| 100.000 | Insercion | 2.497.222.762 | 2.497.122.770 | 29.750 ms |
| 100.000 | MergeSort | 1.536.325 | 1.668.928 | 68 ms |
| 100.000 | HeapSort | 3.019.556 | 1.574.970 | 108 ms |

En QuickSort, con 50.000 lecturas desordenadas y el primer elemento como pivote se obtuvieron 900.318 comparaciones, 450.373 intercambios y 40 ms.

Cuando las mismas 50.000 lecturas llegaron ordenadas cronologicamente y se utilizo el primer elemento como pivote, se produjo un StackOverflowError despues de 731.506.699 comparaciones.

Al cambiar a un pivote aleatorio, las 50.000 lecturas ordenadas pudieron procesarse sin el error, con 931.164 comparaciones, 519.045 intercambios y 17 ms.

En el ultimo experimento, la busqueda binaria por timestamp encontro inicialmente la lectura objetivo en la posicion 73.412 con 16 comparaciones. Despues de ordenar el arreglo por PM2.5, el arreglo dejo de estar ordenado por timestamp y la busqueda binaria devolvio -1. La busqueda lineal demostro que la lectura seguia existiendo y la encontro en la posicion 87.705.

### Diferencia entre la prediccion y el resultado

No habia una prediccion formal registrada antes de ejecutar, por lo que no es correcto afirmar despues que un resultado habia sido previsto.

Lo que mostraron las mediciones fue que el estado inicial de los datos puede cambiar mucho el comportamiento de un algoritmo. El caso mas claro fue Burbuja con datos ya ordenados y QuickSort con el primer elemento como pivote.

### Error o comportamiento inesperado

- **Que ocurrio?** QuickSort produjo un StackOverflowError al procesar 50.000 lecturas que ya estaban ordenadas cronologicamente.
- **Por que ocurrio?** El algoritmo utilizaba siempre el primer elemento como pivote. Como las lecturas ya estaban ordenadas por timestamp, las particiones quedaban muy desbalanceadas y la profundidad de la recursion aumentaba demasiado.
- **Como lo corregimos o que falta corregir?** Se implemento una segunda version de QuickSort con pivote aleatorio. Al repetir el experimento, las 50.000 lecturas pudieron ordenarse sin producir el error.

## 4. Explicacion en lenguaje llano

Ordenar datos es parecido a organizar una fila de objetos siguiendo una regla. Algunos metodos revisan muchas veces la fila, mientras otros la dividen en partes para trabajar mas rapido. Tambien importa como llega la fila al principio, porque un metodo puede funcionar muy bien con datos mezclados y tener problemas cuando ya estan ordenados. Por eso no basta con elegir un algoritmo por su nombre: hay que mirar los datos y medir lo que hace.

### Ejemplo o analogia

Insercion se puede comparar con organizar cartas en la mano. Se toma una carta nueva y se mueve hasta encontrar su lugar entre las que ya estaban organizadas. Si las cartas ya estan casi en orden, hay que mover muy pocas. Si estan completamente mezcladas, hay que hacer muchos mas movimientos. La analogia deja de ser exacta porque el programa trabaja con miles de objetos y registra operaciones que una persona normalmente no contaria.

## 5. El vacio que encontre

- **Mi duda concreta es:** Por que QuickSort puede funcionar normalmente con datos desordenados y producir un StackOverflowError cuando los datos ya vienen ordenados.
- **Lo que ya puedo explicar es:** QuickSort divide los datos utilizando un pivote y despues repite el proceso sobre las partes resultantes.
- **Para resolver la duda consulte:** La guia de la Semana 4 y los resultados del experimento con 50.000 lecturas.
- **Ahora lo entiendo asi:** Si siempre se escoge el primer elemento y ese elemento queda en un extremo de los datos ordenados, una particion puede quedar casi vacia y la otra con casi todos los elementos. La recursion se vuelve demasiado profunda. Cambiar la forma de elegir el pivote evita depender siempre de ese primer elemento.

## 6. Trazado de la solucion

Se selecciona el experimento 5 porque conecta el ordenamiento con la busqueda desarrollada en la semana anterior.

| Paso | Estado de los datos o estructura | Decision o resultado |
|---|---|---|
| 1 | 100.000 lecturas ordenadas por timestamp | `estaOrdenadoPorTimestamp` devuelve `true` |
| 2 | Se busca el timestamp objetivo | Busqueda binaria encuentra la posicion 73.412 con 16 comparaciones |
| 3 | El mismo arreglo se ordena por PM2.5 | El arreglo deja de estar ordenado por timestamp |
| 4 | Se repite la busqueda binaria | Devuelve -1 |
| 5 | Se utiliza busqueda lineal | La lectura aparece en la posicion 87.705 |

El dato no desaparecio. Lo que cambio fue el criterio con el que estaba ordenado el arreglo.

## 7. Decision de diseño

- **Problema que debiamos resolver:** Generar un ranking por PM2.5 sin perder la posibilidad de realizar consultas binarias eficientes por timestamp.
- **Estructura, algoritmo o estrategia elegida:** Trabajar sobre una copia cuando sea necesario ordenar por PM2.5 y conservar el arreglo original en orden cronologico.
- **Alternativa descartada:** Ordenar el mismo arreglo por PM2.5 y posteriormente restaurar el orden por timestamp.
- **Por que elegimos la primera:** Mantiene disponible el orden necesario para la busqueda binaria sin tener que volver a ordenar despues de cada ranking. El costo es utilizar memoria adicional.
- **Que evidencia respalda la decision:** En el experimento 5, despues de ordenar por PM2.5, la busqueda binaria devolvio -1 aunque la busqueda lineal comprobo que la lectura seguia dentro del arreglo.

## 8. Aporte al proyecto

- **Archivo(s) o modulo(s) trabajado(s):** `src/Ordenador.java`, `src/BancoDeOrdenamiento.java`, `src/IngestaSensores.java` y `docs/decisiones.md`.
- **Cambio realizado:** Se incorporaron algoritmos de ordenamiento simples y avanzados, medicion de comparaciones e intercambios, corte temprano de Burbuja, una alternativa de pivote para QuickSort y los cinco experimentos de ordenamiento.
- **Como se conecta con la capa anterior:** La Semana 4 utiliza las lecturas generadas y los algoritmos de busqueda de la Semana 3. El experimento 5 muestra directamente como modificar el orden de los datos afecta la busqueda binaria.
- **Que queda pendiente para la siguiente semana:** Terminar las evidencias del Hito H1, generar la grafica de crecimiento, realizar la validacion final e integrar la rama de Semana 4 a `main`.

## 9. Commits realizados

| Commit | Mensaje | Que demuestra |
|---|---|---|
| `4ff1c38` | `feat: agregar algoritmos simples de ordenamiento` | Implementacion inicial de Burbuja, Seleccion e Insercion |
| `2b06720` | `feat: agregar ordenamientos avanzados` | Incorporacion de MergeSort, HeapSort y QuickSort |
| `10233b3` | `feat: agregar experimento uno de ordenamiento` | Integracion del primer banco de mediciones |
| `6c2a75d` | `fix: agregar corte temprano a burbuja` | Solucion del TODO 1 |
| `8a933c1` | `feat: agregar experimento tres de ordenamiento` | Pruebas con 1.000, 10.000 y 100.000 lecturas |
| `15441a2` | `feat: mejorar pivote de quicksort` | Implementacion de una alternativa al pivote fijo |
| `9962cef` | `feat: agregar experimento cinco de ordenamiento` | Integracion entre ordenamiento y busqueda |
| `7f55968` | `docs: registrar decisiones de semana cuatro` | Registro de DEC-04 y DEC-05 |

## 10. Reexplicacion final

> No existe un algoritmo de ordenamiento que sea automaticamente adecuado para todos los casos. La eleccion depende del tamaño, del estado inicial de los datos, del costo de las operaciones y de lo que el sistema necesite hacer despues. Los experimentos mostraron que Insercion y Burbuja pueden aprovechar datos ordenados, que los algoritmos avanzados crecen mejor con grandes cantidades y que una mala estrategia de pivote puede afectar seriamente a QuickSort. Tambien se comprobo que ordenar por un criterio puede romper la precondicion necesaria para buscar por otro.

## 11. Reflexion individual

1. **Lo que ahora puedo hacer y antes no podia:**  
   Puedo comparar algoritmos utilizando mediciones reales de comparaciones, intercambios y crecimiento, en lugar de mirar solamente el tiempo.

2. **El error o supuesto que mas me enseno:**  
   El StackOverflowError de QuickSort mostro que un algoritmo puede tener buen rendimiento promedio y aun asi comportarse muy mal con un patron especifico de datos.

3. **La pregunta que llevaria a la proxima clase:**  
   Como se pueden mantener consultas eficientes por diferentes criterios sin tener que reordenar el mismo arreglo cada vez.

4. **Que parte del trabajo fue realmente mia:**  
   Ejecute y revise los experimentos, registre las mediciones obtenidas, probe los cambios de Burbuja y QuickSort, verifique el efecto del ordenamiento por PM2.5 sobre la busqueda binaria y documente las decisiones tomadas para el proyecto.

## Lista de verificacion antes de entregar

- [ ] Escribi la prediccion antes de consultar el resultado.
- [x] Inclui evidencia concreta del laboratorio.
- [x] Explique un concepto sin depender de jerga.
- [x] Registre un vacio, una duda o un error real.
- [x] Trace al menos un caso paso a paso.
- [x] Justifique una decision del proyecto y una alternativa descartada.
- [x] Registre mis commits y mi aporte individual.
- [x] Deje claro que queda pendiente.
- [x] Renombre el archivo con el formato `sXX-nombre.md`.