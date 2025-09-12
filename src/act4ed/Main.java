package act4ed;

import act4ed.tree.ArbolBinario;
import act4ed.employee.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        demoNumericTree();
        System.out.println("\n" + "=".repeat(60) + "\n");
        demoEmployees();
    }

    private static void demoNumericTree() {
        System.out.println("DEMOSTRACIÓN: Árbol Binario (BST) numérico\n");
        ArbolBinario<Integer> bst = new ArbolBinario<>();
        int[] values = {50, 30, 70, 20, 40, 60, 80};
        for (int v : values) bst.insert(v);
        System.out.println("Árbol después de inserciones:");
        System.out.println(bst.toPrettyString());

        System.out.println("Inorden:   " + bst.inorder());
        System.out.println("Preorden:  " + bst.preorder());
        System.out.println("Postorden: " + bst.postorder());

        int searchKey = 60;
        boolean found = bst.contains(searchKey);
        System.out.printf("Buscar %d -> %s (pasos=%d)%n", searchKey, found, bst.getLastSearchSteps());

        System.out.println("\nEliminar 30 (nodo con dos hijos)\n");
        bst.remove(30);
        System.out.println(bst.toPrettyString());
        System.out.println("Inorden tras eliminar: " + bst.inorder());
    }

    private static void demoEmployees() {
        System.out.println("DEMOSTRACIÓN: Gestión de Empleados con BST\n");
        EmployeeDirectory dir = new EmployeeDirectory();
        dir.add(new Employee(1005, "Ana", "Ventas"));
        dir.add(new Employee(1001, "Luis", "TI"));
        dir.add(new Employee(1009, "Marta", "RH"));
        dir.add(new Employee(1003, "Diego", "TI"));
        dir.add(new Employee(1007, "Sofía", "Finanzas"));

        System.out.println("Empleados en orden por ID (inorden):");
        List<Employee> ordered = dir.listInOrder();
        for (Employee e : ordered) System.out.println(" - " + e);

        int id = 1007;
        Employee found = dir.findById(id);
        int bstSteps = dir.bstSearchSteps(id);
        Employee seq = dir.sequentialFindById(id);

        System.out.printf("\nBuscar ID %d -> BST: %s (pasos=%d), Secuencial: %s%n",
                id, found, bstSteps, seq);

        int missing = 1011;
        System.out.printf("Buscar ID %d inexistente -> BST pasos=%d, Secuencial: %s%n",
                missing, dir.bstSearchSteps(missing), dir.sequentialFindById(missing));

        System.out.println("\nConclusión: El BST reduce comparaciones respecto a una búsqueda secuencial, "+
                "especialmente cuando crece el número de empleados.");
    }
}
