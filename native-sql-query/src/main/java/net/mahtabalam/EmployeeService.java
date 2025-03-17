package net.mahtabalam;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;

public class EmployeeService {

    public void insertEmployee(String name, int age) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Query query = em.createNativeQuery(
                    "INSERT INTO employees (name, age) VALUES (?, ?)"
            );
            query.setParameter(1, name);
            query.setParameter(2, age);
            query.executeUpdate();
            em.getTransaction().commit();
            System.out.println("Inserted Employee: " + name);
        } finally {
            em.close();
        }
    }

    public void findEmployeesOlderThan(int age) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            Query query = em.createNativeQuery(
                    "SELECT * FROM employees WHERE age > ?",
                    Employee.class
            );
            query.setParameter(1, age);
            List<Employee> employees = query.getResultList();
            System.out.println("Employees older than " + age + ":");
            employees.forEach(System.out::println);
        } finally {
            em.close();
        }
    }

    public void updateEmployeeAge(long id, int newAge) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Query query = em.createNativeQuery(
                    "UPDATE employees SET age = ? WHERE id = ?"
            );
            query.setParameter(1, newAge);
            query.setParameter(2, id);
            int updated = query.executeUpdate();
            em.getTransaction().commit();
            System.out.println("Updated " + updated + " employee");
        } finally {
            em.close();
        }
    }

    public void deleteEmployee(long id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Query query = em.createNativeQuery(
                    "DELETE FROM employees WHERE id = ?"
            );
            query.setParameter(1, id);
            int deleted = query.executeUpdate();
            em.getTransaction().commit();
            System.out.println("Deleted " + deleted + " employee");
        } finally {
            em.close();
        }
    }

}