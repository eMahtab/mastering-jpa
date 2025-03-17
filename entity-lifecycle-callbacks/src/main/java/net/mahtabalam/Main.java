package net.mahtabalam;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("domainPU");
        EntityManager em = emf.createEntityManager();

        EmployeeService service = new EmployeeService(em);

        // 1. create and persist an employee
        em.getTransaction().begin();
        Employee emp = service.createEmployee("John Doe", 28);
        em.getTransaction().commit();
        System.out.println("Persisted " + emp);

        em.clear();
        // 2. find a specific employee
        emp = service.findEmployee(1);
        System.out.println("Found " + emp);

        // 3. find all employees
        List<Employee> emps = service.findAllEmployees();
        for (Employee e : emps)
            System.out.println("Found employee: " + e);

        // 4. update the employee
        em.getTransaction().begin();
        emp = service.updateEmployeeAge (1, 30);
        em.getTransaction().commit();
        System.out.println("Updated " + emp);

        // 5. remove an employee
        em.getTransaction().begin();
        service.removeEmployee(1);
        em.getTransaction().commit();
        System.out.println("Removed Employee 1");

        // close the EM and EMF when done
        em.close();
        emf.close();
    }
}