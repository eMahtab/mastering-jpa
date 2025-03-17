package net.mahtabalam;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class EmployeeService {
    protected EntityManager em;

    public EmployeeService(EntityManager em) {
        this.em = em;
    }

    public Employee createEmployee(String name, int age) {
        Employee emp = new Employee(name);
        emp.setName(name);
        emp.setAge(age);
        em.persist(emp);
        return emp;
    }

    public void removeEmployee(int id) {
        Employee emp = findEmployee(id);
        if (emp != null) {
            em.remove(emp);
        }
    }

    public Employee updateEmployeeAge(int id, int newAge) {
        Employee emp = em.find(Employee.class, id);
        if (emp != null) {
            emp.setAge(newAge);
        }
        return emp;
    }

    public Employee findEmployee(int id) {
        return em.find(Employee.class, id);
    }

    public List<Employee> findAllEmployees() {
        TypedQuery<Employee> query = em.createQuery(
                "SELECT e FROM Employee e", Employee.class);
        return query.getResultList();
    }
}