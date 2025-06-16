package model;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class Employee {
    private static final AtomicInteger SEQ = new AtomicInteger(1);

    private final int id;
    private final String name;
    private final String department;

    public Employee(String name, String department) {
        this.id = SEQ.getAndIncrement();
        this.name = name;
        this.department = department;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getDepartment() { return department; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        return id == ((Employee) o).id;
    }

    @Override
    public int hashCode() { return Objects.hash(id); }

    @Override
    public String toString() {
        return "Employee[" + id + ", " + name + ", " + department + "]";
    }
}
