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

            Department dept = new Department();
            dept.setName("IT Department");

            // Create Employees and set department
            Employee emp1 = new Employee();
            emp1.setName("John Doe");
            emp1.setDepartment(dept);
            dept.addEmployee(emp1);

            Employee emp2 = new Employee();
            emp2.setName("Jane Smith");
            emp2.setDepartment(dept);
            dept.addEmployee(emp2);

            em.persist(dept);

            // Create a second department and employees
            Department hrDept = new Department();
            hrDept.setName("HR Department");

            Employee emp3 = new Employee();
            emp3.setName("Robert Johnson");
            emp3.setDepartment(hrDept);
            hrDept.addEmployee(emp3);
            em.persist(hrDept);

            em.getTransaction().commit();

            // Query and display departments
            System.out.println("\n--- All Departments ---");
            TypedQuery<Department> deptQuery = em.createQuery(
                    "SELECT d FROM Department d",
                    Department.class
            );
            List<Department> departments = deptQuery.getResultList();
            departments.forEach(d ->
                    System.out.println("Department: " + d.getName()));

            // Query and display employees by department
            System.out.println("\n--- IT Department Employees ---");
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

            // Query all employees with department info
            System.out.println("\n--- All Employees with Department Info ---");
            TypedQuery<Employee> allEmpQuery = em.createQuery(
                    "SELECT e FROM Employee e",
                    Employee.class
            );
            List<Employee> allEmployees = allEmpQuery.getResultList();
            allEmployees.forEach(emp ->
                    System.out.println("Employee: " + emp.getName() +
                            " | Department: " + emp.getDepartment().getName()));

            // removing IT department, since we have cascade property set
            // it will also delete all the employees in IT department
            em.getTransaction().begin();
            Department itDepartment = em.find(Department.class, dept.getId());
            if (itDepartment != null) {
                em.remove(itDepartment);
                System.out.println("IT Department removed successfully.");
            }
            em.getTransaction().commit();


        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}