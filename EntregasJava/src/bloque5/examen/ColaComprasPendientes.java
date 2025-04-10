package bloque5.examen;

import entrega2.EstructurasLineales.Cola;
import java.util.List;
import java.util.stream.Collectors;

public class ColaComprasPendientes extends Cola<Compra> {

    // Método para agregar compra a la cola (usando el método add de Cola base)
    public void agregarCompra(Compra compra) {
        this.add(compra);  // Usa add si Cola tiene este método
    }

    // Método que convierte la cola en un Stream
    public List<Compra> getElementos() {
        return this.elementos;  // Devuelve la lista interna de elementos
    }

    // Método imperativo: busca la primera compra cuya descripción contenga la cadena dada
    public Compra buscarCompraPorDescripcion(String desc) {
        for (Compra compra : this.getElementos()) {
            if (compra.getDescripcion().contains(desc)) {
                return compra;
            }
        }
        return null; // No se encontró ninguna compra con esa descripción
    }

    // Método funcional: filtra compras de un cliente con un producto específico
    public List<Compra> filtrarPorClienteYProducto(Cliente cliente, String producto) {
        return this.getElementos().stream()
                   .filter(c -> c.getCliente().equals(cliente) && c.getDescripcion().contains(producto))
                   .collect(Collectors.toList());
    }
}
