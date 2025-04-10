package bloque5.examen;

import entrega2.EstructurasLineales.ListaOrdenada;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ClientesPorAntiguedad extends ListaOrdenada<Cliente> {

    public ClientesPorAntiguedad() {
        super(Comparator.comparingInt(Cliente::getAntiguedad).reversed()); // Orden descendente
    }

    // Método stream() que convierte la lista en un Stream
    public Stream<Cliente> stream() {
        return this.getElements().stream();  // Convierte la lista en un Stream
    }

    // Método para obtener los 'n' clientes más antiguos
    public List<Cliente> topClientes(int n) {
        return this.stream().limit(n).collect(Collectors.toList());
    }
}
