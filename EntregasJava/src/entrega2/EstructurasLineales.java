package entrega2;

import java.util.*;

public class EstructurasLineales {

    // Clase base para estructuras lineales
    abstract static class AgregadoLineal<E> {
        protected List<E> elementos = new ArrayList<>();

        public abstract void add(E elemento);

        public E remove() {
            if (elementos.isEmpty()) {
                throw new NoSuchElementException("No se puede eliminar de un agregado vacío.");
            }
            return elementos.remove(0);
        }

        public int size() {
            return elementos.size();
        }

        public boolean isEmpty() {
            return elementos.isEmpty();
        }

        public List<E> getElements() {
            return new ArrayList<>(elementos);
        }
    }

    // Lista Ordenada
    public static class ListaOrdenada<E> extends AgregadoLineal<E> {

        private Comparator<E> comparator;

        public ListaOrdenada(Comparator<E> comparator) {
            this.comparator = comparator;
        }

        public void add(E elemento) {
            elementos.add(elemento);
            elementos.sort(comparator);
        }
    }

    // Lista Ordenada Sin Repetición
    static class ListaOrdenadaSinRepeticion<E> extends ListaOrdenada<E> {
        public ListaOrdenadaSinRepeticion(Comparator<E> comparator) {
            super(comparator);
        }

        @Override
        public void add(E elemento) {
            if (!elementos.contains(elemento)) {
                super.add(elemento);
            }
        }
    }

    // Cola FIFO
    public static class Cola<E> extends AgregadoLineal<E> {
        public void add(E elemento) {
            elementos.add(elemento);
        }
    }

    // PriorityElement record
    static record PriorityElement<E, P extends Comparable<P>>(E value, P priority) {}

    // Cola de Prioridad
    static class ColaPrioridad<E, P extends Comparable<P>> extends Cola<PriorityElement<E, P>> {

        public void add(E value, P priority) {
            elementos.add(new PriorityElement<>(value, priority));
            elementos.sort(Comparator.comparing(PriorityElement::priority));
        }

        public void cambiarPrioridad(E value, P newPriority) {
            elementos.removeIf(e -> e.value().equals(value));
            add(value, newPriority);
        }

        // Método addAll para añadir múltiples elementos a la ColaPrioridad
        public void addAll(List<PriorityElement<E, P>> elements) {
            for (PriorityElement<E, P> element : elements) {
                add(element.value(), element.priority());
            }
        }
    }

    // Pila (LIFO)
    public static class Pila<E> {
        protected List<E> elementos;  // Lista interna de elementos en la pila

        public Pila() {
            elementos = new ArrayList<>();  // Inicializa la lista
        }

        // Método para agregar un elemento a la pila
        public void add(E elemento) {
            elementos.add(elemento);
        }

        // Método para eliminar un elemento de la pila
        public E remove() {
            if (isEmpty()) {
                throw new NoSuchElementException("No se puede remover de una pila vacía.");
            }
            return elementos.remove(elementos.size() - 1);  // Elimina el último elemento (tope)
        }

        // Método para obtener el elemento en la cima (tope) de la pila sin eliminarlo
        public E top() {
            if (isEmpty()) {
                throw new NoSuchElementException("No hay elementos en la pila.");
            }
            return elementos.get(elementos.size() - 1);  // Devuelve el último elemento (tope)
        }

        // Método para verificar si la pila está vacía
        public boolean isEmpty() {
            return elementos.isEmpty();
        }

        // Método para obtener la lista de elementos de la pila
        public List<E> getElements() {
            return new ArrayList<>(elementos);  // Devuelve una copia de la lista para evitar modificaciones externas
        }

        // Método para obtener el tamaño de la pila
        public int size() {
            return elementos.size();
        }
    }

    // Método de pruebas
    public static void main(String[] args) {
        System.out.println("===== INICIANDO PRUEBAS DE ESTRUCTURAS LINEALES =====\n");

        // Prueba de ListaOrdenada
        System.out.println("----- Prueba de ListaOrdenada -----");
        ListaOrdenada<Integer> lista = new ListaOrdenada<>(Integer::compareTo);
        lista.add(5);
        lista.add(2);
        lista.add(8);
        lista.add(1);
        lista.add(3);
        System.out.println("Elementos en la lista: " + lista.getElements());
        System.out.println("Tamaño de la lista: " + lista.size());
        System.out.println("Eliminando el primer elemento: " + lista.remove());
        System.out.println("Elementos después de eliminar: " + lista.getElements());
        lista.add(4);
        lista.add(6);
        lista.add(7);
        System.out.println("Elementos después de añadir lote: " + lista.getElements());
        System.out.println("¿Está vacía? " + lista.isEmpty());

        // Prueba de ListaOrdenadaSinRepeticion
        System.out.println("----- Prueba de ListaOrdenadaSinRepeticion -----");
        ListaOrdenadaSinRepeticion<Integer> listaSinRep = new ListaOrdenadaSinRepeticion<>(Integer::compareTo);
        System.out.println("Añadiendo elementos: 5, 2, 8, 1, 3, 5, 2");
        listaSinRep.add(5);
        listaSinRep.add(2);
        listaSinRep.add(8);
        listaSinRep.add(1);
        listaSinRep.add(3);
        listaSinRep.add(5);
        listaSinRep.add(2);
        System.out.println("Elementos en la lista: " + listaSinRep.getElements());
        System.out.println("Tamaño de la lista: " + listaSinRep.size());
        System.out.println("Se esperan 5 elementos únicos ordenados");
        System.out.println("Eliminando el primer elemento: " + listaSinRep.remove());
        System.out.println("Elementos después de eliminar: " + listaSinRep.getElements());
        System.out.println("Añadiendo elementos en lote: 4, 6, 7, 4");
        listaSinRep.add(4);
        listaSinRep.add(6);
        listaSinRep.add(7);
        listaSinRep.add(4);
        System.out.println("Elementos después de añadir lote: " + listaSinRep.getElements());
        System.out.println("Se espera que el 4 aparezca solo una vez");

        // Prueba de Cola FIFO
        System.out.println("----- Prueba de Cola (FIFO) -----");
        Cola<String> cola = new Cola<>();
        System.out.println("Añadiendo elementos: 'primero', 'segundo', 'tercero'");
        cola.add("primero");
        cola.add("segundo");
        cola.add("tercero");
        System.out.println("Elementos en la cola: " + cola.getElements());
        System.out.println("Tamaño de la cola: " + cola.size());
        System.out.println("Desencolando elementos:");
        while (!cola.isEmpty()) {
            System.out.println("Desencolado: " + cola.remove());
            System.out.println("Cola restante: " + cola.getElements());
        }
        System.out.println("¿Está vacía? " + cola.isEmpty());
        try {
            cola.remove();
        } catch (NoSuchElementException e) {
            System.out.println("Excepción correctamente lanzada al intentar desencolar de una cola vacía: " + e.getMessage());
        }

        // Prueba de Pila (LIFO)
        System.out.println("----- Prueba de Pila (LIFO) -----");
        Pila<Double> pila = new Pila<>();
        System.out.println("Añadiendo elementos: 1.1, 2.2, 3.3");
        pila.add(1.1);
        pila.add(2.2);
        pila.add(3.3);
        System.out.println("Elementos en la pila: " + pila.getElements());
        System.out.println("Tamaño de la pila: " + pila.size());
        System.out.println("Elemento en el tope: " + pila.top());
        System.out.println("Desapilando elementos:");
        while (!pila.isEmpty()) {
            System.out.println("Desapilado: " + pila.remove());
            System.out.println("Pila restante: " + pila.getElements());
        }
        System.out.println("¿Está vacía? " + pila.isEmpty());
        try {
            pila.top();
        } catch (NoSuchElementException e) {
            System.out.println("Excepción correctamente lanzada al intentar acceder al tope de una pila vacía: " + e.getMessage());
        }

        // Prueba de ColaPrioridad
        System.out.println("\n----- Prueba de ColaPrioridad -----");
        ColaPrioridad<String, Integer> colaPrioridad = new ColaPrioridad<>();
        colaPrioridad.add("Crítico", 1);
        colaPrioridad.add("Normal", 3);
        colaPrioridad.add("Urgente", 2);
        colaPrioridad.add("Bajo", 4);
        System.out.println("Elementos ordenados por prioridad: " + colaPrioridad.getElements());
        colaPrioridad.cambiarPrioridad("Normal", 1);
        System.out.println("Elementos con prioridad actualizada: " + colaPrioridad.getElements());
        while (!colaPrioridad.isEmpty()) {
            System.out.println("Desencolado: " + colaPrioridad.remove().value());
            System.out.println("Cola restante: " + colaPrioridad.getElements());
        }

        // Prueba con addAll
        System.out.println("\n----- Prueba con addAll -----");
        ColaPrioridad<String, Integer> colaPrioridadAddAll = new ColaPrioridad<>();
        List<PriorityElement<String, Integer>> tareas = Arrays.asList(
            new PriorityElement<>("Tarea A", 2),
            new PriorityElement<>("Tarea B", 2),
            new PriorityElement<>("Tarea C", 2)
        );
        colaPrioridadAddAll.addAll(tareas);
        System.out.println("Elementos añadidos en lote con prioridad 2: " + colaPrioridadAddAll.getElements());
        colaPrioridadAddAll.add("Tarea Urgente", 1);
        System.out.println("Después de añadir 'Tarea Urgente' con prioridad 1: " + colaPrioridadAddAll.getElements());

        System.out.println("===== TODAS LAS PRUEBAS COMPLETADAS =====");
    }
} 
