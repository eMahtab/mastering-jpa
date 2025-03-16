package net.mahtabalam;

import net.mahtabalam.domain.Department;
import net.mahtabalam.domain.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;


public class Main {
    public static void main(String[] args) {
        // Create EntityManagerFactory
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("domainPU");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            Department itDepartment = new Department();
            itDepartment.setName("IT Department");
            em.persist(itDepartment);

            // Create and persist employees
            Employee emp1 = new Employee();
            emp1.setName("John Doe");
            itDepartment.addEmployee(emp1);
            em.persist(emp1);

            Employee emp2 = new Employee();
            emp2.setName("Jane Smith");
            itDepartment.addEmployee(emp2);
            em.persist(emp2);

            em.getTransaction().commit();

            // Second transaction: Remove an employee
            em.getTransaction().begin();

            Department savedDept = em.find(Department.class, itDepartment.getId());
            System.out.println("Department: " + savedDept.getName());
            System.out.println("Number of employees before removal: " + savedDept.getEmployees().size());
            savedDept.getEmployees().forEach(emp ->
                    System.out.println("Employee: " + emp.getName()));

            // Remove emp1
            savedDept.removeEmployee(savedDept.getEmployees().get(0)); // Remove the first employee

            em.getTransaction().commit();

            // Verify the result in a new transaction
            Department updatedDept = em.find(Department.class, itDepartment.getId());
            System.out.println("Number of employees after removal: " + updatedDept.getEmployees().size());
            updatedDept.getEmployees().forEach(emp ->
                    System.out.println("Employee: " + emp.getName()));

        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}