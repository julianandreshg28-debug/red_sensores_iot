public class Ordenador {

    private long comparaciones;
    private long intercambios;

    public Ordenador() {
        reiniciarContadores();
    }

    public void reiniciarContadores() {
        comparaciones = 0;
        intercambios = 0;
    }

    public long getComparaciones() {
        return comparaciones;
    }

    public long getIntercambios() {
        return intercambios;
    }

    private void registrarComparacion() {
        comparaciones++;
    }

    private void registrarIntercambio() {
        intercambios++;
    }

    private void intercambiar(
            LecturaSensor[] datos,
            int i,
            int j) {

        LecturaSensor temporal = datos[i];
        datos[i] = datos[j];
        datos[j] = temporal;

        registrarIntercambio();
    }

    private boolean mayorPM25(
            LecturaSensor a,
            LecturaSensor b) {

        registrarComparacion();

        return a.getPm25() > b.getPm25();
    }

    private boolean menorPM25(
            LecturaSensor a,
            LecturaSensor b) {

        registrarComparacion();

        return a.getPm25() < b.getPm25();
    }

    public void burbuja(LecturaSensor[] datos) {

        int n = datos.length;

        for (int pasada = 0; pasada < n - 1; pasada++) {

            for (int j = 0; j < n - 1 - pasada; j++) {

                if (mayorPM25(datos[j], datos[j + 1])) {
                    intercambiar(datos, j, j + 1);
                }
            }
        }
    }

    public void seleccion(LecturaSensor[] datos) {

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

    public void insercion(LecturaSensor[] datos) {

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
                j--;
            }

            datos[j + 1] = actual;
        }
    }

    public void mergeSort(LecturaSensor[] datos) {

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

        mergeSort(izquierda);
        mergeSort(derecha);

        fusionar(datos, izquierda, derecha);
    }

    private void fusionar(
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
    public void heapSort(LecturaSensor[] datos) {

        int n = datos.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(datos, n, i);
        }

        for (int fin = n - 1; fin > 0; fin--) {

            intercambiar(datos, 0, fin);

            heapify(datos, fin, 0);
        }
    }

    private void heapify(
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

            intercambiar(datos, raiz, mayor);

            heapify(datos, n, mayor);
        }
    }
    public void quickSort(LecturaSensor[] datos) {
        quickSort(
                datos,
                0,
                datos.length - 1);
    }

    private void quickSort(
            LecturaSensor[] datos,
            int inicio,
            int fin) {

        if (inicio >= fin) {
            return;
        }

        int posicionPivote =
                particionar(datos, inicio, fin);

        quickSort(
                datos,
                inicio,
                posicionPivote - 1);

        quickSort(
                datos,
                posicionPivote + 1,
                fin);
    }

    private int particionar(
            LecturaSensor[] datos,
            int inicio,
            int fin) {

        LecturaSensor pivote = datos[inicio];

        int i = inicio + 1;
        int j = fin;

        while (true) {

            while (i <= fin) {
                registrarComparacion();

                if (datos[i].getPm25() > pivote.getPm25()) {
                    break;
                }

                i++;
            }

            while (j > inicio) {
                registrarComparacion();

                if (datos[j].getPm25() <= pivote.getPm25()) {
                    break;
                }

                j--;
            }

            if (i >= j) {
                break;
            }

            intercambiar(datos, i, j);
            i++;
            j--;
        }

        intercambiar(datos, inicio, j);

        return j;
    }
}