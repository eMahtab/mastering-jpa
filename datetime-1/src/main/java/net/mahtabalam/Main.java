package net.mahtabalam;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.OffsetDateTime;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("domainPU");
        EntityManager em = emf.createEntityManager();

        // 1. create and persist an employee
        em.getTransaction().begin();
        Event event1 = new Event("Some popular event", OffsetDateTime.parse("2025-03-25T10:00:00+08:00"));
        Event event2 = new Event("Another popular event", OffsetDateTime.parse("2025-03-30T09:00:00+05:30"));
        em.persist(event1);
        em.persist(event2);
        em.getTransaction().commit();


        Event event1DB = em.find(Event.class, event1.getId());
        System.out.println("Fetched from DB " + event1DB);
        Event event2DB = em.find(Event.class, event2.getId());
        System.out.println("Fetched from DB " + event2DB);

        em.close();
        emf.close();
    }
}