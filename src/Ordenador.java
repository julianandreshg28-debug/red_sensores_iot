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
}