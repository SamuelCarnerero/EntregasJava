package bloque5.examen;

import java.util.Objects;

public final class Cliente implements Comparable<Cliente> {
    private final String nombre;
    private final int antiguedad;

    // Constructor canónico
    public Cliente(String nombre, int antiguedad) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede ser nulo o vacío.");
        }
        if (antiguedad < 0) {
            throw new IllegalArgumentException("La antigüedad no puede ser negativa.");
        }
        this.nombre = nombre;
        this.antiguedad = antiguedad;
    }

    // Método de factoría
    public static Cliente of(String nombre, int antiguedad) {
        return new Cliente(nombre, antiguedad);
    }

    // Métodos de consulta
    public String getNombre() {
        return nombre;
    }

    public int getAntiguedad() {
        return antiguedad;
    }

    // toString según el formato especificado
    @Override
    public String toString() {
        return String.format("Cliente[nombre=%s, antigüedad=%d]", nombre, antiguedad);
    }

    // Dos clientes son iguales si tienen el mismo nombre
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Cliente cliente = (Cliente) obj;
        return Objects.equals(nombre, cliente.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }

    // Ordenación por antigüedad (menor a mayor)
    @Override
    public int compareTo(Cliente otro) {
        return Integer.compare(this.antiguedad, otro.antiguedad);
    }
}
