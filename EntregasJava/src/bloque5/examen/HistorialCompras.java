package bloque5.examen;

import entrega2.EstructurasLineales.Pila;
import java.util.List;
import java.util.stream.Collectors;

public class HistorialCompras extends Pila<Compra> {

    // Método funcional: calcula el importe total gastado por un cliente
    public double totalGastadoPor(Cliente cliente) {
        return this.getElementos().stream()  // Accede a la lista interna y crea un stream
                   .filter(c -> c.getCliente().equals(cliente))
                   .mapToDouble(Compra::getImporte)
                   .sum();
    }

    // Método funcional: devuelve compras cuyo importe supera una cantidad
    public List<Compra> comprasMayoresA(double cantidad) {
        return this.getElementos().stream()  // Accede a la lista interna y crea un stream
                   .filter(c -> c.getImporte() > cantidad)
                   .collect(Collectors.toList());
    }

    // Método para agregar compra a la pila (usando add en lugar de push)
    public void agregarCompra(Compra compra) {
        this.add(compra);  // Si la clase Pila usa el método add
    }

    // Método para obtener la lista de elementos en la pila (si la lista es interna)
    public List<Compra> getElementos() {
        return this.elementos;  // Devuelve la lista interna de elementos
    }
}
