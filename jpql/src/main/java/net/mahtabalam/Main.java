package net.mahtabalam;

public class Main {

    public static void main(String[] args) {

        EmployeeService employeeService = new EmployeeService();

        // Insert employee using JPQL
        employeeService.insertEmployee("John Doe", 30);
        employeeService.insertEmployee("Jason Smith", 35);

        // Find employees older than 25 using JPQL
        employeeService.findEmployeesOlderThan(25);

        // Update employee using JPQL
        employeeService.updateEmployeeAge(1l, 31);

        // Delete employee using JPQL
        employeeService.deleteEmployee(2l);

        JpaUtil.close();
    }
}