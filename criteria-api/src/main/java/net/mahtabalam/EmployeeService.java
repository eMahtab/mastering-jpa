package net.mahtabalam;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class EmployeeService {

    public void insertEmployee(String name, int age) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Employee employee = new Employee(name, age);
            em.persist(employee);
            em.getTransaction().commit();
            System.out.println("Inserted Employee: " + name);
        } finally {
            em.close();
        }
    }

    public void findEmployeesOlderThan(int age) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
            Root<Employee> root = cq.from(Employee.class);

            cq.select(root)
                    .where(cb.greaterThan(root.get("age"), age));

            TypedQuery<Employee> query = em.createQuery(cq);
            List<Employee> employees = query.getResultList();

            System.out.println("Employees older than " + age + ":");
            employees.forEach(System.out::println);
        } finally {
            em.close();
        }
    }

    public void updateEmployeeAge(long id, int newAge) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaUpdate<Employee> cu = cb.createCriteriaUpdate(Employee.class);
            Root<Employee> root = cu.from(Employee.class);

            cu.set(root.get("age"), newAge)
                    .where(cb.equal(root.get("id"), id));

            int updated = em.createQuery(cu).executeUpdate();
            em.getTransaction().commit();
            System.out.println("Updated " + updated + " employee");
        } finally {
            em.close();
        }
    }

    public void deleteEmployee(long id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaDelete<Employee> cd = cb.createCriteriaDelete(Employee.class);
            Root<Employee> root = cd.from(Employee.class);

            cd.where(cb.equal(root.get("id"), id));

            int deleted = em.createQuery(cd).executeUpdate();
            em.getTransaction().commit();
            System.out.println("Deleted " + deleted + " employee");
        } finally {
            em.close();
        }
    }

}