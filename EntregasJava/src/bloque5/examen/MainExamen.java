package bloque5.examen;

import java.util.List;

public class MainExamen {
    public static void main(String[] args) {
        // Crear clientes
        Cliente ana = Cliente.of("Ana", 5);
        Cliente juan = Cliente.of("Juan", 2);
        Cliente luis = Cliente.of("Luis", 7);

        // Crear compras
        Compra c1 = Compra.of(ana, "Agenda personalizada", 25.5);
        Compra c2 = Compra.of(juan, "Camiseta estampada", 60.0);
        Compra c3 = Compra.of(ana, "Taza con foto", 15.0);
        Compra c4 = Compra.of(luis, "Poster gigante", 80.0);

        // Crear instancias de HistorialCompras y ColaComprasPendientes
        HistorialCompras historial = new HistorialCompras();
        ColaComprasPendientes cola = new ColaComprasPendientes();

        // Agregar compras a la pila (historial)
        historial.agregarCompra(c1);
        historial.agregarCompra(c2);
        historial.agregarCompra(c3);
        historial.agregarCompra(c4);

        // Agregar compras a la cola
        cola.agregarCompra(c1);
        cola.agregarCompra(c2);

        // Ejemplo de operaciones adicionales

        // Mostrar las compras en la cola
        System.out.println("Compras en la cola:");
        cola.getElementos().forEach(compra -> System.out.println(compra.getDescripcion()));

        // Mostrar las compras en el historial (pila)
        System.out.println("\nCompras en el historial (pila):");
        historial.getElementos().forEach(compra -> System.out.println(compra.getDescripcion()));

        // Ejemplo de operaciones adicionales, como total gastado por un cliente
        double totalGastadoPorAna = historial.totalGastadoPor(ana);
        System.out.println("\nTotal gastado por Ana: " + totalGastadoPorAna + " €");

        // Filtrar compras mayores a una cantidad
        List<Compra> comprasMayoresA50 = historial.comprasMayoresA(50.0);
        System.out.println("\nCompras mayores a 50 €:");
        comprasMayoresA50.forEach(compra -> System.out.println(compra.getDescripcion()));
    }
}
