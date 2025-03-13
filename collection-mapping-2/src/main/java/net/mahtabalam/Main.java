package net.mahtabalam;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import net.mahtabalam.domain.Product;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("domainPU");
        EntityManager em = emf.createEntityManager();

        try {

            em.getTransaction().begin();

            Product p1 = new Product("Samsung Galaxy M16 5G",
                    Map.of("brand","Samsung", "CPU Speed", "2.4GHz"));
            Product p2 = new Product("Canon EOS R100 24.1 MP",
                    Map.of("brand", "Canon", "Optical Zoom", "2"));

            em.persist(p1);
            em.persist(p2);

            em.getTransaction().commit();


            Product findProduct = em.find(Product.class, p1.getId());
            System.out.println("Find Product: " + findProduct);

            TypedQuery<Product> query = em.createQuery(
                    "SELECT e FROM Product e",
                    Product.class
            );

            List<Product> products = query.getResultList();

            System.out.println("Number of books: " + products.size());
            products.forEach(product ->
                    System.out.println("Product: " + product.getName()));

        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}