package com.avinandan.ganguly.example_database.repository;

import com.avinandan.ganguly.example_database.bean.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeJpaRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByNameContaining(String name);

    List<Employee> findByDesignationContaining(String designation);

    List<Employee> findByOrderBySalaryAsc();

    @Query(value = "SELECT * from employee e WHERE e.name not in (\n" +
            "\tSELECT e2.name FROM employee e2 group by e2.name HAVING COUNT(e2.name) > 1\n" +
            "); ", nativeQuery = true)
    List<Employee> findDistinctName();

    @Query(value = "SELECT * FROM employee e WHERE e.salary = (SELECT MAX(e.salary) FROM employee e);", nativeQuery = true)
    List<Employee> findAllByHighestSalary();
}
