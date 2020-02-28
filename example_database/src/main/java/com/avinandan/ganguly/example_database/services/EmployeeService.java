package com.avinandan.ganguly.example_database.services;

import com.avinandan.ganguly.example_database.bean.entity.Employee;
import com.avinandan.ganguly.example_database.bean.request.EmployeeRequest;
import com.avinandan.ganguly.example_database.bean.response.Response;
import com.avinandan.ganguly.example_database.repository.EmployeeJpaRepository;
import com.avinandan.ganguly.example_database.util.exceptions.InvalidRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService implements IEmployeeService {

    @Autowired
    EmployeeJpaRepository employeeJpaRepository;

    @Override
    public List<Employee> getAllEmployees() {
        return employeeJpaRepository.findAll();
    }

    @Override
    public Employee getEmployee(Long id) {
        Optional<Employee> optionalEmployee = employeeJpaRepository.findById(id);

        if (!optionalEmployee.isPresent()) {
            throw new InvalidRequestException(String.format("Employee with id %s is not present", id));
        }

        return optionalEmployee.get();
    }

    @Override
    public Employee createEmployee(EmployeeRequest employeeRequest) {

        Employee employee = new Employee();
        employee.setName(employeeRequest.getName());
        employee.setDesignation(employeeRequest.getDesignation());
        employee.setSalary(employeeRequest.getSalary());

        return employeeJpaRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Long id, EmployeeRequest employeeRequest) {
        Optional<Employee> optionalEmployee = employeeJpaRepository.findById(id);

        if (!optionalEmployee.isPresent()) {
            throw new InvalidRequestException(String.format("Employee with id %s not present", id));
        }

        Employee employee = optionalEmployee.get();
        employee.setName(employeeRequest.getName());
        employee.setDesignation(employeeRequest.getDesignation());
        employee.setSalary(employeeRequest.getSalary());

        return employeeJpaRepository.save(employee);
    }

    @Override
    public Response deleteEmployee(Long id) {
        if (!employeeJpaRepository.existsById(id)) {
            throw new InvalidRequestException(String.format("Employee with id %s not present", id));
        }

        employeeJpaRepository.deleteById(id);

        return new Response(false, "Employee deleted");
    }



//    @Override
//    public List<Employee> getEmployeeByName(String name) {
//        return null;
//    }

    @Override
    public Response deleteAllEmployees() {
        employeeJpaRepository.deleteAll();
        return new Response(false, "All employees deleted");
    }

    @Override
    public List<Employee> findByName(String name) {
        return employeeJpaRepository.findByNameContaining(name);
    }

    @Override
    public List<Employee> findByDesignation(String designation) {
        return employeeJpaRepository.findByDesignationContaining(designation);
    }

    @Override
    public List<Employee> findBySalaryOrder() {
        return employeeJpaRepository.findByOrderBySalaryAsc();
    }

    @Override
    public List<Employee> findByUniqueNames() {
        return employeeJpaRepository.findDistinctName();
    }

    @Override
    public List<Employee> findByHighestSalary() {
        return employeeJpaRepository.findAllByHighestSalary();
    }

}
