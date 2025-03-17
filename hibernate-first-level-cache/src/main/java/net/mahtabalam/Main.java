package net.mahtabalam;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("domainPU");
        EntityManager em = emf.createEntityManager();


        // 1. create and persist an employee
        em.getTransaction().begin();
        Employee emp = new Employee("Mahtab");
        em.persist(emp);
        em.getTransaction().commit();

        System.out.println("Persisted " + emp);

        // 2. find a specific employee
        Employee e1 = em.find(Employee.class, 1l);
        Employee e2 = em.find(Employee.class, 1l);

        System.out.println("e1 == e2 :" + (e1==e2));

        em.close();
        emf.close();
    }
}