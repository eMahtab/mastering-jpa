package net.mahtabalam;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("domainPU");
        EntityManager em = emf.createEntityManager();

        // 1. create and persist an employee
        em.getTransaction().begin();
        Employee emp = new Employee("Mahtab Alam", 4000000, EmploymentType.FULL_TIME);
        em.persist(emp);
        em.getTransaction().commit();

        System.out.println("Persisted " + emp);

        Employee fetchedFromDB = em.find(Employee.class, emp.getId());
        System.out.println("Fetched from DB " + fetchedFromDB);

        em.close();
        emf.close();
    }
}