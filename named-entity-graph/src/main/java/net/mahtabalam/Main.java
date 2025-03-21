package net.mahtabalam;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import net.mahtabalam.domain.Post;
import net.mahtabalam.domain.User;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("domainPU");
        EntityManager em = emf.createEntityManager();

        populateTestData(em);

        demonstrateNPlusOne(em);

        em.close();
        emf.close();
    }

    private static void populateTestData(EntityManager em) {
        em.getTransaction().begin();

        User mahtab = new User("Mahtab", "mahtab01", "mahtab@test.com");
        User david = new User("David", "dave02", "david@test.com");

        em.persist(mahtab);
        em.persist(david);

        em.persist(new Post("First Post", mahtab));
        em.persist(new Post("Tech Thoughts", mahtab));
        em.persist(new Post("Kafka 101", mahtab));

        em.persist(new Post("Sales Tips", david));
        em.persist(new Post("Daily Update", david));
        em.persist(new Post("Beyond Self Help Books", david));

        em.getTransaction().commit();
    }

    private static void demonstrateNPlusOne(EntityManager em) {
        em.clear();
        em.getTransaction().begin();

        System.out.println("Demonstrating N+1 problem fixed with Entity Graph:");

        // Get the Entity Graph for User with posts included
        EntityGraph<?> graph = em.getEntityGraph("User.withPosts");

        // Use the Entity Graph as a fetch hint in the query
        List<User> users = em.createQuery("SELECT u FROM User u", User.class)
                .setHint("jakarta.persistence.fetchgraph", graph)
                .getResultList();

        // Access posts without triggering additional queries
        for (User user : users) {
            System.out.println("User: " + user.getUsername());
            System.out.println("Number of posts: " + user.getPosts().size());
            for (Post post : user.getPosts()) {
                System.out.println("  Post: " + post.getTitle());
            }
        }

        em.getTransaction().commit();
    }
}