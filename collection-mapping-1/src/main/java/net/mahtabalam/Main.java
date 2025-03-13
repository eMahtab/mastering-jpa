package net.mahtabalam;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import net.mahtabalam.domain.Book;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("domainPU");
        EntityManager em = emf.createEntityManager();

        try {

            em.getTransaction().begin();

            Book book1 = new Book("Designing Data-Intensive Applications",
                    List.of("Computer Science", "Software Design"));
            Book book2 = new Book("Clean Code", List.of("Computer Science", "Computer Programming"));

            em.persist(book1);
            em.persist(book2);

            em.getTransaction().commit();


            Book findBook = em.find(Book.class, book1.getId());
            System.out.println("Find Book: " + findBook);

            TypedQuery<Book> query = em.createQuery(
                    "SELECT e FROM Book e",
                    Book.class
            );

            List<Book> books = query.getResultList();

            System.out.println("Number of books: " + books.size());
            books.forEach(book ->
                    System.out.println("Book: " + book.getName()));

        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}