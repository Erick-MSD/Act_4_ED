package act4ed.employee;

import java.util.Objects;

public class Employee implements Comparable<Employee> {
    private final int id;
    private final String name;
    private final String department;

    public Employee(int id, String name, String department) {
        this.id = id;
        this.name = Objects.requireNonNull(name);
        this.department = Objects.requireNonNull(department);
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getDepartment() { return department; }

    @Override
    public int compareTo(Employee o) {
        return Integer.compare(this.id, o.id);
    }

    @Override
    public String toString() {
        return String.format("Employee{id=%d, name='%s', dept='%s'}", id, name, department);
    }
}
