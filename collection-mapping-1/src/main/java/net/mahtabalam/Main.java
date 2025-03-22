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

            em.clear();
            Book findBook = em.find(Book.class, book1.getId());
            System.out.println("Find Book: " + findBook);

            em.clear();
            // Fetch book along with categories using JPQL
            TypedQuery<Book> findQuery = em.createQuery(
                    "SELECT b FROM Book b LEFT JOIN FETCH b.categories WHERE b.id = :id",
                    Book.class
            );
            findQuery.setParameter("id", book1.getId());

            Book singleBook = findQuery.getSingleResult();
            System.out.println("Find Book: " + singleBook);

            // Fetch all books along with categories
            TypedQuery<Book> query = em.createQuery(
                    "SELECT DISTINCT b FROM Book b LEFT JOIN FETCH b.categories",
                    Book.class
            );

            List<Book> books = query.getResultList();

            System.out.println("Number of books: " + books.size());
            books.forEach(book ->
                    System.out.println("Book: " + book.getName() + ", Categories: " + book.getCategories()));


        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}