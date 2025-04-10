package bloque5.examen;

import java.util.Objects;

public final class Compra {
    private final Cliente cliente;
    private final String descripcion;
    private final double importe;

    // Constructor canónico
    public Compra(Cliente cliente, String descripcion, double importe) {
        if (cliente == null) {
            throw new IllegalArgumentException("El cliente no puede ser nulo.");
        }
        if (descripcion == null || descripcion.trim().isEmpty()) {
            throw new IllegalArgumentException("La descripción no puede estar vacía.");
        }
        if (importe < 0) {
            throw new IllegalArgumentException("El importe no puede ser negativo.");
        }
        this.cliente = cliente;
        this.descripcion = descripcion;
        this.importe = importe;
    }

    // Método de factoría
    public static Compra of(Cliente cliente, String descripcion, double importe) {
        return new Compra(cliente, descripcion, importe);
    }

    // Métodos de consulta
    public Cliente getCliente() {
        return cliente;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getImporte() {
        return importe;
    }

    // Representación en cadena según el formato requerido
    @Override
    public String toString() {
        return String.format("Compra [Nombre de cliente= %s, descripción= %s, importe= %.2f €]", 
                              cliente.getNombre(), descripcion, importe);
    }

    // Equals y hashCode (dos compras son iguales si tienen el mismo cliente, descripción e importe)
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Compra compra = (Compra) obj;
        return Double.compare(compra.importe, importe) == 0 &&
               Objects.equals(cliente, compra.cliente) &&
               Objects.equals(descripcion, compra.descripcion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cliente, descripcion, importe);
    }
}
