package examen;
import java.io.*;
import java.util.*;

public class Examen {
    public static int productoImpares(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("El número debe ser positivo y mayor que 0.");
        }
        
        int producto = 1;
        int contador = 0;
        int numero = 1;
        
        while (contador < n) {
            producto *= numero;
            numero += 2; // Avanza al siguiente número impar
            contador++;
        }
        
        return producto;
    }
    public static double sumaGeometricaAlternada(double a1, double r, int k) {
        if (k <= 0) {
            throw new IllegalArgumentException("k debe ser mayor que 0");
        }
        if (a1 <= 0 || r <= 0) {
            throw new IllegalArgumentException("a1 y r deben ser positivos");
        }

        double suma = 0;
        for (int n = 0; n < k; n++) {
            double termino = Math.pow(-1, n) * a1 * Math.pow(r, n);
            suma += termino;
        }
        return suma;
    }

    public static long combinatorioSinMultiplosDeTres(int n, int k) {
        if (n < k || n < 0 || k < 0) {
            throw new IllegalArgumentException("n debe ser mayor o igual que k y ambos deben ser positivos");
        }

        long num = 1;
        long den = 1;

        for (int i = 0; i < k; i++) {
            int numerador = n - i;
            int denominador = i + 1;

            if (numerador % 3 != 0) {
                num *= numerador;
            }
            if (denominador % 3 != 0) {
                den *= denominador;
            }
        }

        return num / den;
    }
    public static List<String> filtrarLineasConsecutivas(String fichero, List<String> palabrasClave) {
        List<String> lineasFiltradas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                // Limpiar la línea de espacios al inicio y al final
                linea = linea.trim();

                // Si la línea está vacía después de hacer el trim, la omitimos
                if (linea.isEmpty()) {
                    continue;
                }

                // Separar la línea en palabras, usando un patrón que captura solo palabras
                String[] palabras = linea.split("[^a-zA-ZáéíóúÁÉÍÓÚ]+");

                // Iterar sobre las palabras y comprobar si están en la lista de palabras clave consecutivas
                for (int i = 0; i < palabras.length - 1; i++) {
                    // Convertir las palabras a minúsculas para una comparación sin distinción de mayúsculas/minúsculas
                    if (palabrasClave.contains(palabras[i].toLowerCase()) && palabrasClave.contains(palabras[i + 1].toLowerCase())) {
                        // Si encontramos una coincidencia, añadimos la línea y detenemos la búsqueda en esa línea
                        lineasFiltradas.add(linea);
                        break; // Salir del bucle para la siguiente línea
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }

        return lineasFiltradas;
    }
        public static void main(String[] args) {
            testProductoImpares();
            System.out.println("####################################");
            testSumaGeometricaAlternada();
            System.out.println("####################################");
            testCombinatorioSinMultiplosDeTres();
            System.out.println("####################################");
            testFiltrarLineasConsecutivas();
        }

        public static void testProductoImpares() {
            System.out.println("Probando productoImpares...");
            try {
                int resultado = Examen.productoImpares(3);
                System.out.println("productoImpares(3) = " + resultado);
                resultado = Examen.productoImpares(5);
                System.out.println("productoImpares(5) = " + resultado);
                Examen.productoImpares(0);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        public static void testSumaGeometricaAlternada() {
            System.out.println("Probando sumaGeometricaAlternada...");
            try {
                double resultado = Examen.sumaGeometricaAlternada(1, 2, 3);
                System.out.println("sumaGeometricaAlternada(1, 2, 3) = " + resultado);
                resultado = Examen.sumaGeometricaAlternada(2, 3, 4);
                System.out.println("sumaGeometricaAlternada(2, 3, 4) = " + resultado);
                Examen.sumaGeometricaAlternada(-1, 2, 3);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        public static void testCombinatorioSinMultiplosDeTres() {
            System.out.println("Probando combinatorioSinMultiplosDeTres...");
            try {
                long resultado = Examen.combinatorioSinMultiplosDeTres(5, 2);
                System.out.println("combinatorioSinMultiplosDeTres(5, 2) = " + resultado);
                resultado = Examen.combinatorioSinMultiplosDeTres(6, 3);
                System.out.println("combinatorioSinMultiplosDeTres(6, 3) = " + resultado);
                Examen.combinatorioSinMultiplosDeTres(2, 3);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        public static void testFiltrarLineasConsecutivas() {
            System.out.println("Probando filtrarLineasConsecutivas...");

            // Definir las palabras clave, asegurándonos de que estén en minúsculas para comparación más robusta
            List<String> palabrasClave = Arrays.asList("clave", "prueba");

            // Cambia esta ruta si es necesario. Asegúrate de que el archivo esté en la ruta correcta.
            List<String> resultado = filtrarLineasConsecutivas("src/resources/fichero.txt", palabrasClave);

            System.out.println("Líneas filtradas:");

            if (resultado.isEmpty()) {
                System.out.println("No se encontraron líneas con las palabras clave consecutivas.");
            } else {
                for (String linea : resultado) {
                    System.out.println(linea);
                }
            }
        }
    }
