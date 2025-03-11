package net.mahtabalam;

import net.mahtabalam.domain.IdCard;
import net.mahtabalam.domain.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("domainPU");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            // Create new IdCard
            IdCard idCard = new IdCard();
            idCard.setCardNumber("EMP123456");
            idCard.setIssueDate(LocalDate.now());
            em.persist(idCard);

            // Create new Employee with IdCard
            Employee employee = new Employee();
            employee.setName("Jane Smith");
            employee.setIdCard(idCard);

            // Persist the employee
            em.persist(employee);

            em.getTransaction().commit();

            // Fetch and print
            Employee savedEmployee = em.find(Employee.class, employee.getId());
            System.out.println("Employee: " + savedEmployee);
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }

}