package net.mahtabalam;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("userPU");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Address address = new Address();
        address.setAddressLine1("6th Avenue")
               .setAddressLine2("West 4th Street")
               .setCity("New York City")
               .setState("New York")
               .setZipCode("10011")
               .setCountry("United States");

        User user = new User("peter@example.com",address);
        em.persist(user);
        em.getTransaction().commit();

        // 2. find a specific user
        User userFetched = em.find(User.class, user.getId());
        System.out.println("Found " + userFetched);

        em.close();
        emf.close();
    }
}