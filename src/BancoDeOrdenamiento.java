import java.util.Random;

public class BancoDeOrdenamiento {

    private static LecturaSensor[] copiar(
            LecturaSensor[] original) {

        LecturaSensor[] copia =
                new LecturaSensor[original.length];

        System.arraycopy(
                original, 0,
                copia, 0,
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
}