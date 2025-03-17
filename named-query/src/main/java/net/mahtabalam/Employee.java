package net.mahtabalam;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;

@Entity
@Table(name = "employees")
@NamedQueries({
        @NamedQuery(
                name = "Employee.findEmployeesOlderThan",
                query = "SELECT e FROM Employee e WHERE e.age > :age"
        ),
        @NamedQuery(
                name = "Employee.updateEmployeeAge",
                query = "UPDATE Employee e SET e.age = :newAge WHERE e.id = :id"
        ),
        @NamedQuery(
                name = "Employee.deleteEmployee",
                query = "DELETE FROM Employee e WHERE e.id = :id"
        )
})
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private int age;

    public Employee() {}

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}