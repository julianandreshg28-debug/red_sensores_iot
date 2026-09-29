public class Ordenador {

    private static long comparaciones;
    private static long intercambios;

    public static void reiniciarContadores() {
        comparaciones = 0;
        intercambios = 0;
    }

    public static long getComparaciones() {
        return comparaciones;
    }

    public static long getIntercambios() {
        return intercambios;
    }

    private static void registrarComparacion() {
        comparaciones++;
    }

    private static void registrarIntercambio() {
        intercambios++;
    }

    private static void intercambiar(
            LecturaSensor[] datos,
            int i,
            int j) {

        LecturaSensor temporal = datos[i];
        datos[i] = datos[j];
        datos[j] = temporal;

        registrarIntercambio();
    }

    private static boolean mayorPM25(
            LecturaSensor a,
            LecturaSensor b) {

        registrarComparacion();

        return a.getPm25() > b.getPm25();
    }

    private static boolean menorPM25(
            LecturaSensor a,
            LecturaSensor b) {

        registrarComparacion();

        return a.getPm25() < b.getPm25();
    }

    public static void burbuja(LecturaSensor[] datos) {

        reiniciarContadores();

        int n = datos.length;

        for (int pasada = 0; pasada < n - 1; pasada++) {

            boolean huboIntercambio = false;

            for (int j = 0; j < n - 1 - pasada; j++) {

                if (mayorPM25(datos[j], datos[j + 1])) {

                    intercambiar(datos, j, j + 1);
                    huboIntercambio = true;
                }
            }

            if (!huboIntercambio) {
                break;
            }
        }
    }

    public static void seleccion(LecturaSensor[] datos) {

        reiniciarContadores();

        int n = datos.length;

        for (int i = 0; i < n - 1; i++) {

            int posicionMenor = i;

            for (int j = i + 1; j < n; j++) {

                if (menorPM25(
                        datos[j],
                        datos[posicionMenor])) {

                    posicionMenor = j;
                }
            }

            if (posicionMenor != i) {

                intercambiar(
                        datos,
                        i,
                        posicionMenor);
            }
        }
    }

    public static void insercion(LecturaSensor[] datos) {

        reiniciarContadores();

        for (int i = 1; i < datos.length; i++) {

            LecturaSensor actual = datos[i];
            int j = i - 1;

            while (j >= 0) {

                registrarComparacion();

                if (datos[j].getPm25()
                        <= actual.getPm25()) {
                    break;
                }

                datos[j + 1] = datos[j];
                registrarIntercambio();

                j--;
            }

            datos[j + 1] = actual;
        }
    }

    public static void mergeSort(LecturaSensor[] datos) {

        reiniciarContadores();

        mergeSortRecursivo(datos);
    }

    private static void mergeSortRecursivo(
            LecturaSensor[] datos) {

        if (datos.length <= 1) {
            return;
        }

        int medio = datos.length / 2;

        LecturaSensor[] izquierda =
                new LecturaSensor[medio];

        LecturaSensor[] derecha =
                new LecturaSensor[datos.length - medio];

        System.arraycopy(
                datos, 0,
                izquierda, 0,
                izquierda.length);

        System.arraycopy(
                datos, medio,
                derecha, 0,
                derecha.length);

        mergeSortRecursivo(izquierda);
        mergeSortRecursivo(derecha);

        fusionar(datos, izquierda, derecha);
    }

    private static void fusionar(
            LecturaSensor[] datos,
            LecturaSensor[] izquierda,
            LecturaSensor[] derecha) {

        int i = 0;
        int j = 0;
        int k = 0;

        while (i < izquierda.length
                && j < derecha.length) {

            registrarComparacion();

            if (izquierda[i].getPm25()
                    <= derecha[j].getPm25()) {

                datos[k++] = izquierda[i++];

            } else {

                datos[k++] = derecha[j++];
            }
        }

        while (i < izquierda.length) {
            datos[k++] = izquierda[i++];
        }

        while (j < derecha.length) {
            datos[k++] = derecha[j++];
        }
    }

    public static void heapSort(LecturaSensor[] datos) {

        reiniciarContadores();

        int n = datos.length;

        for (int i = n / 2 - 1; i >= 0; i--) {

            heapify(
                    datos,
                    n,
                    i);
        }

        for (int fin = n - 1; fin > 0; fin--) {

            intercambiar(
                    datos,
                    0,
                    fin);

            heapify(
                    datos,
                    fin,
                    0);
        }
    }

    private static void heapify(
            LecturaSensor[] datos,
            int n,
            int raiz) {

        int mayor = raiz;
        int izquierda = 2 * raiz + 1;
        int derecha = 2 * raiz + 2;

        if (izquierda < n) {

            registrarComparacion();

            if (datos[izquierda].getPm25()
                    > datos[mayor].getPm25()) {

                mayor = izquierda;
            }
        }

        if (derecha < n) {

            registrarComparacion();

            if (datos[derecha].getPm25()
                    > datos[mayor].getPm25()) {

                mayor = derecha;
            }
        }

        if (mayor != raiz) {

            intercambiar(
                    datos,
                    raiz,
                    mayor);

            heapify(
                    datos,
                    n,
                    mayor);
        }
    }

    public static void quickSortPivotePrimero(
            LecturaSensor[] datos) {

        reiniciarContadores();

        quickSortPivotePrimero(
                datos,
                0,
                datos.length - 1);
    }

    private static void quickSortPivotePrimero(
            LecturaSensor[] datos,
            int inicio,
            int fin) {

        if (inicio >= fin) {
            return;
        }

        int posicionPivote =
                particionarPivotePrimero(
                        datos,
                        inicio,
                        fin);

        quickSortPivotePrimero(
                datos,
                inicio,
                posicionPivote - 1);

        quickSortPivotePrimero(
                datos,
                posicionPivote + 1,
                fin);
    }

    private static int particionarPivotePrimero(
            LecturaSensor[] datos,
            int inicio,
            int fin) {

        LecturaSensor pivote =
                datos[inicio];

        int i = inicio + 1;
        int j = fin;

        while (true) {

            while (i <= fin) {

                registrarComparacion();

                if (datos[i].getPm25()
                        > pivote.getPm25()) {

                    break;
                }

                i++;
            }

            while (j > inicio) {

                registrarComparacion();

                if (datos[j].getPm25()
                        <= pivote.getPm25()) {

                    break;
                }

                j--;
            }

            if (i >= j) {
                break;
            }

            intercambiar(
                    datos,
                    i,
                    j);

            i++;
            j--;
        }

        intercambiar(
                datos,
                inicio,
                j);

        return j;
    }

    public static void ordenarPorPm25(
            LecturaSensor[] datos) {

        mergeSort(datos);
    }

    public static boolean estaOrdenadoPorTimestamp(
            LecturaSensor[] datos) {

        for (int i = 1; i < datos.length; i++) {

            if (datos[i - 1]
                    .getTimestamp()
                    .compareTo(
                            datos[i].getTimestamp()) > 0) {

                return false;
            }
        }

        return true;
    }
}