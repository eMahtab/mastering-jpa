package net.mahtabalam;

import net.mahtabalam.domain.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import net.mahtabalam.domain.Project;


public class Main {

    public static void main(String[] args) {
        // Create EntityManagerFactory
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("domainPU");
        EntityManager em = emf.createEntityManager();

        try {
            // Begin transaction
            em.getTransaction().begin();

            // Create employees
            Employee emp1 = new Employee("John Doe");
            Employee emp2 = new Employee("Jane Smith");

            // Create projects
            Project proj1 = new Project("Project A");
            Project proj2 = new Project("Project B");

            // Associate employees with projects
            emp1.addProject(proj1);
            emp1.addProject(proj2);
            emp2.addProject(proj1);

            // Persist entities
            em.persist(emp1);
            em.persist(emp2);
            em.persist(proj1);
            em.persist(proj2);

            // Commit transaction
            em.getTransaction().commit();

            // Query and display results
            System.out.println("\nRetrieving data...");
            Employee retrievedEmp = em.find(Employee.class, emp1.getId());
            System.out.println("Employee: " + retrievedEmp.getName());
            System.out.println("Projects: ");
            for (Project p : retrievedEmp.getProjects()) {
                System.out.println("- " + p.getTitle());
            }

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }

}