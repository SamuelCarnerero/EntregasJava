package entrega1;

import entrega1.Funciones.Funcion;

public class Funciones {

    // 1. Producto ∏ (𝑛 − 𝑖 + 1)𝑘
    public static long calcularProducto(int n, int k) {
        long producto = 1;
        for (int i = 0; i <= k; i++) {
            producto *= (n - i + 1);
        }
        return producto;
    }

    // 2. Producto de una secuencia geométrica
    public static double productoGeometrico(double a1, double r, int k) {
        double producto = 1;
        for (int i = 0; i < k; i++) {
            producto *= a1 * Math.pow(r, i);
        }
        return producto;
    }

    // 3. Número combinatorio (n k)
    public static long combinatorio(int n, int k) {
        if (k == 0 || k == n) return 1;
        long numerador = 1, denominador = 1;
        for (int i = 1; i <= k; i++) {
            numerador *= (n - i + 1);
            denominador *= i;
        }
        return numerador / denominador;
    }

    // 4. Cálculo de S(n, k)
    public static double calcularS(int n, int k) {
        double suma = 0;
        for (int i = 0; i < k; i++) {
            suma += Math.pow(-1, i) * combinatorio(k + 1, i + 1) * Math.pow(k - i, n);
        }
        return suma / factorial(k);
    }

    private static long factorial(int num) {
        long fact = 1;
        for (int i = 2; i <= num; i++) {
            fact *= i;
        }
        return fact;
    }

    // 5. Método de Newton para encontrar x0
    public static double metodoNewton(Funcion f, Funcion df, double x0, double epsilon) {
        double x = x0;
        while (Math.abs(f.evaluar(x)) > epsilon) {
            System.out.println("Iteración con x = " + x + ", f(x) = " + f.evaluar(x));
            x = x - f.evaluar(x) / df.evaluar(x);
        }
        return x;
    }

    // Interfaz funcional para la función y su derivada
    @FunctionalInterface
    interface Funcion {
        double evaluar(double x);
    }

    public static void main(String[] args) {
        System.out.println("Producto: " + calcularProducto(2, 2));
        System.out.println("Producto Geométrico: " + productoGeometrico(2, 3, 4));
        System.out.println("Combinatorio: " + combinatorio(5, 2));
        System.out.println("S(n, k): " + calcularS(5, 3));

        // Ejemplo de uso del método de Newton
        Funcion f = x -> x * x - 2; // f(x) = x^2 - 2
        Funcion df = x -> 2 * x; // f'(x) = 2x
        double raiz = metodoNewton(f, df, 1.0, 0.0001);
        System.out.println("Raíz con método de Newton: " + raiz);
    }
}
