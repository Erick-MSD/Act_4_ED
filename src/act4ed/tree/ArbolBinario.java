package act4ed.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Árbol Binario de Búsqueda (BST) genérico para elementos comparables.
 */
public class ArbolBinario<T extends Comparable<T>> {
    private Node<T> root;

    // Métricas sencillas para comparar número de pasos en búsquedas
    private int lastSearchSteps = 0;

    public Node<T> getRoot() {
        return root;
    }

    public int getLastSearchSteps() {
        return lastSearchSteps;
    }

    // Inserción
    public void insert(T value) {
        Objects.requireNonNull(value, "value");
        root = insertRec(root, value);
    }

    private Node<T> insertRec(Node<T> node, T value) {
        if (node == null) return new Node<>(value);
        int cmp = value.compareTo(node.value);
        if (cmp < 0) node.left = insertRec(node.left, value);
        else if (cmp > 0) node.right = insertRec(node.right, value);
        // Ignora duplicados (o podríamos contar frecuencia)
        return node;
    }

    // Obtener el elemento igual a 'key' o null si no existe (actualiza pasos)
    public T getOrNull(T key) {
        lastSearchSteps = 0;
        Node<T> n = root;
        while (n != null) {
            lastSearchSteps++;
            int cmp = key.compareTo(n.value);
            if (cmp == 0) return n.value;
            n = (cmp < 0) ? n.left : n.right;
        }
        return null;
    }

    // Búsqueda booleana
    public boolean contains(T value) {
        return getOrNull(value) != null;
    }

    // Eliminación estándar en BST
    public void remove(T value) {
        root = removeRec(root, value);
    }

    private Node<T> removeRec(Node<T> node, T value) {
        if (node == null) return null;
        int cmp = value.compareTo(node.value);
        if (cmp < 0) node.left = removeRec(node.left, value);
        else if (cmp > 0) node.right = removeRec(node.right, value);
        else {
            // encontrado
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            // dos hijos: reemplazar con el sucesor inorder (mínimo en rama derecha)
            Node<T> succ = minNode(node.right);
            node.value = succ.value;
            node.right = removeRec(node.right, succ.value);
        }
        return node;
    }

    private Node<T> minNode(Node<T> node) {
        while (node.left != null) node = node.left;
        return node;
    }

    // Recorridos
    public List<T> inorder() { List<T> r = new ArrayList<>(); inorderRec(root, r); return r; }
    private void inorderRec(Node<T> n, List<T> out) {
        if (n == null) return; inorderRec(n.left, out); out.add(n.value); inorderRec(n.right, out);
    }

    public List<T> preorder() { List<T> r = new ArrayList<>(); preorderRec(root, r); return r; }
    private void preorderRec(Node<T> n, List<T> out) {
        if (n == null) return; out.add(n.value); preorderRec(n.left, out); preorderRec(n.right, out);
    }

    public List<T> postorder() { List<T> r = new ArrayList<>(); postorderRec(root, r); return r; }
    private void postorderRec(Node<T> n, List<T> out) {
        if (n == null) return; postorderRec(n.left, out); postorderRec(n.right, out); out.add(n.value);
    }

    // Representación visual simple del árbol (giro 90°)
    public String toPrettyString() {
        StringBuilder sb = new StringBuilder();
        prettyRec(root, sb, 0);
        return sb.toString();
    }

    private void prettyRec(Node<T> n, StringBuilder sb, int depth) {
        if (n == null) return;
        prettyRec(n.right, sb, depth + 1);
        sb.append("    ".repeat(depth)).append(n.value).append('\n');
        prettyRec(n.left, sb, depth + 1);
    }
}
