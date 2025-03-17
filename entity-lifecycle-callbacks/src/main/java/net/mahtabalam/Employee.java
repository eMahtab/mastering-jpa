package net.mahtabalam;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "employees")
@EntityListeners(EmployeeListener.class)
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int age;

    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;

    public Employee() {}

    public Employee(String name) {
        this.name = name;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
    public void setName(String name) { this.name = name; }
    public void setAge (int age) { this.age = age; }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", lastUpdated=" + lastUpdated +
                '}';
    }
}