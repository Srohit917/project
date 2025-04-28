package com.employees.employee.repository;

public class EmployeeRepository {

    @Repository
    public interface EmployeeRepository extends JpaRepository<Employee, Long> {}

}

