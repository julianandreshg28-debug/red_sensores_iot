# Traza de búsqueda binaria — Semana 3

Arreglo:

[0, 1, 2, 3]

Objetivo:

3

| Paso | inicio | fin | medio | valor medio | acción |
|---|---:|---:|---:|---:|---|
| 1 | 0 | 3 | 1 | 1 | Como 1 < 3, buscar a la derecha: inicio = medio + 1 = 2 |
| 2 | 2 | 3 | 2 | 2 | Como 2 < 3, buscar a la derecha: inicio = medio + 1 = 3 |
| 3 | 3 | 3 | 3 | 3 | El valor medio es igual al objetivo. Dato encontrado. |

## Conclusión

Cuando el valor del medio es menor que el objetivo, el nuevo inicio debe ser:

inicio = medio + 1;

El +1 es necesario porque la posición medio ya fue comparada.

Si se utilizara solamente:

inicio = medio;

el intervalo podría dejar de avanzar y la búsqueda podría quedarse repitiendo la misma posición, produciendo un ciclo infinito.