# Decisiones de diseño — Semana 3

## 1. Punto de entrada

El proyecto mantiene un único punto de entrada:

```text
IngestaSensores.main()
```

No se crean aplicaciones independientes por semana.

`BancoDePruebas` es una clase auxiliar y no contiene `main`.

## 2. Búsqueda por timestamp

Se utilizan dos estrategias:

- Búsqueda lineal: no requiere ordenamiento.
- Búsqueda binaria: requiere que el arreglo esté ordenado por timestamp.

Los datos sintéticos de `GeneradorDatos` se generan en orden cronológico, por lo que la búsqueda binaria por timestamp cumple su precondición.

## 3. Búsqueda por PM2.5

No se asume que los datos estén ordenados por PM2.5.

Por tanto, la búsqueda binaria por PM2.5 se conserva como experimento para demostrar el efecto de una precondición incumplida.

## 4. Comparación de String

Los identificadores de estación se comparan mediante:

```java
equals()
```

y no mediante:

```java
==
```

porque se necesita comparar contenido.

## 5. Medición

La comparación principal entre algoritmos utiliza el número de comparaciones.

El tiempo en milisegundos se conserva como evidencia experimental, pero no es la única medida utilizada.

## 6. Evolución del proyecto

La Semana 3 agrega una nueva capacidad a la misma plataforma:

```text
Sensores
   ↓
Ingesta
   ↓
Repositorio
   ↓
Búsqueda
   ↓
Medición de eficiencia
```

La Semana 4 podrá extender esta misma arquitectura para estudiar ordenamiento.

---

# Decisiones de diseño — Semana 4

## DEC-04 — Pivote de QuickSort

**Semana:** 4

**Problema:**

QuickSort puede producir particiones muy desbalanceadas cuando siempre utiliza el primer elemento como pivote y los datos ya vienen ordenados cronológicamente.

**Alternativas:**

- Pivote aleatorio.
- Mediana de tres.

**Decisión:**

Se implementó un pivote aleatorio.

**Justificación:**

Se mantuvo primero la versión con pivote en el primer elemento para poder observar el problema y comparar el comportamiento antes y después de cambiar el pivote.

Con 50.000 lecturas se obtuvieron estos resultados:

- Datos desordenados con pivote primero: 900.318 comparaciones, 450.373 intercambios y 40 ms.
- Datos en orden cronológico con pivote primero: StackOverflowError después de 731.506.699 comparaciones.
- Datos en orden cronológico con pivote aleatorio: 931.164 comparaciones, 519.045 intercambios y 17 ms.

Los algoritmos generales de `Ordenador` comparan las lecturas por timestamp. Por eso, cuando las lecturas ya llegan ordenadas cronológicamente y QuickSort utiliza siempre el primer elemento como pivote, las particiones quedan muy desbalanceadas y la recursión crece hasta producir un StackOverflowError.

Con el pivote aleatorio, el mismo conjunto de 50.000 lecturas pudo ordenarse sin producir el error.

**Consecuencia:**

QuickSort deja de depender siempre del primer elemento como pivote y evita el comportamiento extremo observado con los datos cronológicamente ordenados.

## DEC-05 — Ordenamiento y búsqueda

**Semana:** 4

**Problema:**

La búsqueda binaria por timestamp requiere que el arreglo esté ordenado por timestamp. Al ordenar el mismo arreglo por PM2.5, esa precondición deja de cumplirse.

**Alternativas:**

- Trabajar sobre una copia.
- Restaurar el orden por timestamp.
- Mantener estructuras o índices separados.

**Decisión:**

Trabajar sobre una copia cuando sea necesario generar un ranking por PM2.5 y conservar el arreglo original ordenado por timestamp.

**Justificación:**

En el experimento con 100.000 lecturas, antes de ordenar por PM2.5 el arreglo estaba ordenado por timestamp y la búsqueda binaria encontró la lectura en la posición 73.412 con 16 comparaciones.

Después de ordenar el mismo arreglo por PM2.5, el arreglo dejó de estar ordenado por timestamp. La búsqueda binaria devolvió -1, aunque la búsqueda lineal encontró la lectura en la posición 87.705.

La lectura seguía existiendo. El problema fue que el arreglo dejó de cumplir la precondición necesaria para la búsqueda binaria por timestamp.

**Consecuencia:**

Trabajar sobre una copia permite generar rankings por PM2.5 sin destruir el orden cronológico utilizado por la búsqueda binaria. El costo es utilizar memoria adicional.