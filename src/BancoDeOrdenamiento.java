import java.util.Random;

public class BancoDeOrdenamiento {

    private static LecturaSensor[] copiar(
            LecturaSensor[] original) {

        LecturaSensor[] copia =
                new LecturaSensor[original.length];

        System.arraycopy(
                original,
                0,
                copia,
                0,
                original.length);

        return copia;
    }

    private static LecturaSensor[] desordenar(
            LecturaSensor[] original) {

        LecturaSensor[] copia = copiar(original);

        Random azar = new Random(777L);

        for (int i = copia.length - 1; i > 0; i--) {

            int j = azar.nextInt(i + 1);

            LecturaSensor temporal = copia[i];
            copia[i] = copia[j];
            copia[j] = temporal;
        }

        return copia;
    }

    private static void reportar(
            String nombre,
            long milis) {

        System.out.printf(
                "%-14s comparaciones: %,14d   intercambios: %,14d   %6d ms%n",
                nombre,
                Ordenador.getComparaciones(),
                Ordenador.getIntercambios(),
                milis);
    }

    // =====================================================
    // EXPERIMENTO 1
    // =====================================================

    public static void experimentoUno() {

        System.out.println(
                "=== EXP 1: ALGORITMOS SIMPLES, 10.000 LECTURAS DESORDENADAS ==="
        );

        LecturaSensor[] base =
                desordenar(
                        GeneradorDatos.generar(10_000)
                );

        LecturaSensor[] a = copiar(base);

        long t = System.currentTimeMillis();

        Ordenador.burbuja(a);

        reportar(
                "Burbuja",
                System.currentTimeMillis() - t
        );

        LecturaSensor[] b = copiar(base);

        t = System.currentTimeMillis();

        Ordenador.seleccion(b);

        reportar(
                "Seleccion",
                System.currentTimeMillis() - t
        );

        LecturaSensor[] c = copiar(base);

        t = System.currentTimeMillis();

        Ordenador.insercion(c);

        reportar(
                "Insercion",
                System.currentTimeMillis() - t
        );

        System.out.println();
    }

    // =====================================================
    // EXPERIMENTO 2
    // =====================================================

    public static void experimentoDos() {

        System.out.println(
                "=== EXP 2: ALGORITMOS SIMPLES, 10.000 LECTURAS ORDENADAS ==="
        );

        LecturaSensor[] base =
                GeneradorDatos.generar(10_000);

        Ordenador.mergeSort(base);

        LecturaSensor[] a = copiar(base);

        long t = System.currentTimeMillis();

        Ordenador.burbuja(a);

        reportar(
                "Burbuja",
                System.currentTimeMillis() - t
        );

        LecturaSensor[] b = copiar(base);

        t = System.currentTimeMillis();

        Ordenador.seleccion(b);

        reportar(
                "Seleccion",
                System.currentTimeMillis() - t
        );

        LecturaSensor[] c = copiar(base);

        t = System.currentTimeMillis();

        Ordenador.insercion(c);

        reportar(
                "Insercion",
                System.currentTimeMillis() - t
        );

        System.out.println();
    }

    // =====================================================
    // EXPERIMENTO 3
    // =====================================================

    public static void experimentoTres() {

        System.out.println(
                "=== EXP 3: INSERCION vs MERGESORT vs HEAPSORT ==="
        );

        int[] tamanos = {
                1_000,
                10_000,
                100_000
        };

        for (int n : tamanos) {

            System.out.println(
                    "-- " + String.format("%,d", n)
                            + " lecturas desordenadas --"
            );

            LecturaSensor[] base =
                    desordenar(
                            GeneradorDatos.generar(n)
                    );

            LecturaSensor[] a = copiar(base);

            long t = System.currentTimeMillis();

            Ordenador.insercion(a);

            reportar(
                    "Insercion",
                    System.currentTimeMillis() - t
            );

            LecturaSensor[] b = copiar(base);

            t = System.currentTimeMillis();

            Ordenador.mergeSort(b);

            reportar(
                    "MergeSort",
                    System.currentTimeMillis() - t
            );

            LecturaSensor[] c = copiar(base);

            t = System.currentTimeMillis();

            Ordenador.heapSort(c);

            reportar(
                    "HeapSort",
                    System.currentTimeMillis() - t
            );

            System.out.println();
        }
    }

    // =====================================================
    // EXPERIMENTO 4
    // =====================================================

    public static void experimentoCuatro() {

        System.out.println(
                "=== EXP 4: QUICKSORT Y ELECCION DEL PIVOTE ==="
        );

        System.out.println(
                "-- Caso A: 50.000 lecturas DESORDENADAS, pivote primero --"
        );

        LecturaSensor[] revueltas =
                desordenar(
                        GeneradorDatos.generar(50_000)
                );

        long t = System.currentTimeMillis();

        Ordenador.quickSortPivotePrimero(revueltas);

        reportar(
                "QuickSort",
                System.currentTimeMillis() - t
        );

        System.out.println();

        System.out.println(
                "-- Caso B: 50.000 lecturas EN ORDEN CRONOLOGICO, pivote primero --"
        );

        LecturaSensor[] enOrden =
                GeneradorDatos.generar(50_000);

        try {

            t = System.currentTimeMillis();

            Ordenador.quickSortPivotePrimero(enOrden);

            reportar(
                    "QuickSort",
                    System.currentTimeMillis() - t
            );

        } catch (StackOverflowError e) {

            System.out.println(
                    "QuickSort -> StackOverflowError"
            );

            System.out.println(
                    "Comparaciones antes del error: "
                            + String.format(
                            "%,d",
                            Ordenador.getComparaciones()
                    )
            );
        }

        System.out.println();

        System.out.println(
                "-- Caso C: 50.000 lecturas EN ORDEN CRONOLOGICO, pivote aleatorio --"
        );

        LecturaSensor[] enOrdenMejorado =
                GeneradorDatos.generar(50_000);

        t = System.currentTimeMillis();

        Ordenador.quickSort(enOrdenMejorado);

        reportar(
                "QuickSort",
                System.currentTimeMillis() - t
        );

        System.out.println();
    }
}