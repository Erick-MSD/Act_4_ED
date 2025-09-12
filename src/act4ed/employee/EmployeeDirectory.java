package act4ed.employee;

import act4ed.tree.ArbolBinario;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDirectory {
    private final ArbolBinario<Employee> tree = new ArbolBinario<>();
    private final List<Employee> list = new ArrayList<>(); // para comparación secuencial

    public void add(Employee e) {
        tree.insert(e);
        list.add(e);
    }

    public Employee findById(int id) {
        return tree.getOrNull(new Employee(id, "", ""));
    }

    public int bstSearchSteps(int id) {
        tree.getOrNull(new Employee(id, "", ""));
        return tree.getLastSearchSteps();
    }

    public Employee sequentialFindById(int id) {
        for (Employee e : list) {
            if (e.getId() == id) return e;
        }
        return null;
    }

    public List<Employee> listInOrder() {
        return tree.inorder();
    }
}
