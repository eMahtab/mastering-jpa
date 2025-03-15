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
        Question q1 = new Question("Do you prefer coffee over tea", Answer.YES);
        Question q2 = new Question("Would you rather live in a big city than in a small town", Answer.NO);

        em.persist(q1);
        em.persist(q2);
        em.getTransaction().commit();

        // 2. find a specific employee
        Question findQ1 = em.find(Question.class, q1.getId());
        Question findQ2 = em.find(Question.class, q2.getId());
        System.out.println("Fetch Q1 :" + findQ1);
        System.out.println("Fetch Q1 :" + findQ2);

        em.close();
        emf.close();
    }
}