package net.mahtabalam;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;

public class EmployeeService {

    public void insertEmployee(String name, int age) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Employee employee = new Employee(name, age);
            em.persist(employee);
            em.getTransaction().commit();
            System.out.println("Inserted Employee: " + name);
        } finally {
            em.close();
        }
    }

    public void findEmployeesOlderThan(int age) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            Query query = em.createQuery(
                    "SELECT e FROM Employee e WHERE e.age > :age",
                    Employee.class
            );
            query.setParameter("age", age);
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
            Query query = em.createQuery(
                    "UPDATE Employee e SET e.age = :newAge WHERE e.id = :id"
            );
            query.setParameter("newAge", newAge);
            query.setParameter("id", id);
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
            Query query = em.createQuery(
                    "DELETE FROM Employee e WHERE e.id = :id"
            );
            query.setParameter("id", id);
            int deleted = query.executeUpdate();
            em.getTransaction().commit();
            System.out.println("Deleted " + deleted + " employee");
        } finally {
            em.close();
        }
    }

}