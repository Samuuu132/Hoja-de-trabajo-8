package hdt8;

/**
 * Interfaz de una Cola de Prioridad.
 *
 * @param <E> Tipo de elemento, que debe ser comparable.
 */
public interface PriorityQueue<E> {
    void add(E element);
    E remove();
    E peek();
    boolean isEmpty();
}

