package act4ed.tree;

/**
 * Nodo genérico para Árbol Binario de Búsqueda (BST).
 */
public class Node<T> {
    public T value;
    public Node<T> left;
    public Node<T> right;

    public Node(T value) {
        this.value = value;
    }
}
