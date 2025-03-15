package net.mahtabalam;

import net.mahtabalam.domain.Department;
import net.mahtabalam.domain.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("domainPU");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            // Note that we are not persisting Department instance manually
            // it will automatically be persisted as we have CascadeType.PERSIST
            // on Department attribute in the Employee entity

            // Cascade operations are direction specific and only work in the direction
            // in which they are specified
            Department dept = new Department();
            dept.setName("IT Department");

            // Create and persist employees
            Employee emp1 = new Employee();
            emp1.setName("John Doe");
            emp1.setDepartment(dept);
            em.persist(emp1);

            Employee emp2 = new Employee();
            emp2.setName("Jane Smith");
            emp2.setDepartment(dept);
            em.persist(emp2);

            em.getTransaction().commit();

            // Query and display (using JPQL since we can't access employees from Department)
            Department savedDept = em.find(Department.class, dept.getId());
            System.out.println("Department: " + savedDept.getName());

            TypedQuery<Employee> query = em.createQuery(
                    "SELECT e FROM Employee e WHERE e.department.id = :deptId",
                    Employee.class
            );
            query.setParameter("deptId", dept.getId());
            List<Employee> employees = query.getResultList();

            System.out.println("Number of employees: " + employees.size());
            employees.forEach(emp ->
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