package net.mahtabalam;

import net.mahtabalam.domain.IdCard;
import net.mahtabalam.domain.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        // Create EntityManagerFactory
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("domainPU");
        EntityManager em = emf.createEntityManager();

        try {
            // Begin transaction
            em.getTransaction().begin();

            // Create an IdCard
            IdCard idCard = new IdCard();
            idCard.setCardNumber("EMP-001");
            idCard.setIssueDate(LocalDate.now());

            // Create an Employee
            Employee employee = new Employee();
            employee.setName("John Doe");
            employee.setIdCard(idCard);

            // Set the bidirectional relationship
            idCard.setEmployee(employee);

            // Persist the entities
            em.persist(idCard);
            em.persist(employee);

            // Commit transaction
            em.getTransaction().commit();

            // Query and display the persisted data
            Employee foundEmployee = em.find(Employee.class, employee.getId());
            System.out.println("Retrieved Employee: " + foundEmployee);
            System.out.println("Employee's ID Card: " + foundEmployee.getIdCard());

            // Demonstrate update
            em.getTransaction().begin();
            foundEmployee.setName("John Smith");
            foundEmployee.getIdCard().setCardNumber("EMP-002");
            em.getTransaction().commit();

            System.out.println("\nAfter update:");
            System.out.println("Updated Employee: " + foundEmployee);

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            // Clean up
            if (em != null && em.isOpen()) {
                em.close();
            }
            if (emf != null && emf.isOpen()) {
                emf.close();
            }
        }
    }

}