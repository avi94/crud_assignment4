package com.avinandan.ganguly.example_database.services;

import com.avinandan.ganguly.example_database.bean.entity.Employee;
import com.avinandan.ganguly.example_database.bean.request.EmployeeRequest;
import com.avinandan.ganguly.example_database.bean.response.Response;

import java.util.List;

public interface IEmployeeService {

    List<Employee> getAllEmployees();

    Employee getEmployee(Long id);

    Employee createEmployee(EmployeeRequest employee);

    Employee updateEmployee(Long id, EmployeeRequest employeeRequest);

    Response deleteEmployee(Long id);

//    List<Employee> getEmployeeByName(String name);

    Response deleteAllEmployees();

    List<Employee> findByName(String name);

    List<Employee> findByDesignation(String designation);

    List<Employee> findBySalaryOrder();

    List<Employee> findByUniqueNames();

    List<Employee> findByHighestSalary();
}
