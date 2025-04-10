package entrega1;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

public class Lecturas {

    // Método para obtener la ruta correcta del archivo dentro de src/resources
    private static String obtenerRuta(String fichero) {
        return "src/resources/" + fichero;
    }

    // Función 6: Contar cuántas veces aparece una palabra en un archivo
    public static int contarPalabra(String fichero, String sep, String cad) {
        String ruta = obtenerRuta(fichero);
        int contador = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] palabras = linea.split("\\W+"); // Dividir eliminando puntuaciones
                for (String palabra : palabras) {
                    if (palabra.equalsIgnoreCase(cad)) { // Ignorar mayúsculas y minúsculas
                        contador++;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contador;
    }

    // Función 7: Obtener líneas que contienen una cadena específica
    public static List<String> obtenerLineas(String fichero, String cad) {
        String ruta = obtenerRuta(fichero);
        List<String> lineas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.toLowerCase().contains(cad.toLowerCase())) { // Ignorar mayúsculas y minúsculas
                    lineas.add(linea);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lineas;
    }

    // Función 8: Obtener palabras únicas del archivo
    public static Set<String> palabrasUnicas(String fichero) {
        String ruta = obtenerRuta(fichero);
        Set<String> palabrasUnicas = new HashSet<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] palabras = linea.split("\\W+");
                for (String palabra : palabras) {
                    palabrasUnicas.add(palabra.toLowerCase()); // Ignorar mayúsculas y minúsculas
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return palabrasUnicas;
    }

    // Función 9: Calcular la longitud promedio de las líneas de un archivo CSV
    public static double longitudPromedio(String fichero) {
        String ruta = obtenerRuta(fichero);
        List<Integer> longitudes = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] palabras = linea.split(","); // Dividir por comas para contar palabras
                longitudes.add(palabras.length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return longitudes.stream().mapToInt(Integer::intValue).average().orElse(0.0);
    }

    
    public static void main(String[] args) {
        // Pruebas de las funciones
        String fichero1 = "lin_quijote.txt";
        String fichero2 = "archivo_palabras.txt";
        String fichero3 = "palabras_random.csv";
        String palabra = "quijote";
        String separador = " ";

        // Test función 6
        System.out.println("El número de veces que aparece la palabra " + palabra + " en el fichero " + fichero1 + " es: " + contarPalabra(fichero1, separador, palabra));
        
        // Test función 7
        System.out.println("Las líneas en las que aparece la palabra " + palabra + " son: " + obtenerLineas(fichero1, palabra));
        
        // Test función 8
        System.out.println("Las palabras únicas en el fichero " + fichero2 + " son: " + palabrasUnicas(fichero2));
        
        // Test función 9
        System.out.println("La longitud promedio de las líneas del fichero " + fichero3 + " es: " + longitudPromedio(fichero3));
    }
}
