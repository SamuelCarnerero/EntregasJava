package bloque5.examen;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

public class EstadisticasClientes {

    // 🔹 Versión imperativa
    public static Map<Cliente, List<Compra>> agruparComprasPorClienteImperativo(List<Compra> compras) {
        Map<Cliente, List<Compra>> mapaCompras = new HashMap<>();
        
        for (Compra compra : compras) {
            Cliente cliente = compra.getCliente();
            
            // Si el cliente no está en el mapa, lo añadimos con una nueva lista
            mapaCompras.putIfAbsent(cliente, new java.util.ArrayList<>());
            mapaCompras.get(cliente).add(compra);
        }
        return mapaCompras;
    }

    // 🔹 Versión funcional
    public static Map<Cliente, List<Compra>> agruparComprasPorClienteFuncional(List<Compra> compras) {
        return compras.stream()
                      .collect(Collectors.groupingBy(Compra::getCliente));
    }
}
