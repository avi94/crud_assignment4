package com.avinandan.ganguly.example_database.bean.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class EmployeeRequest {

    @NotNull(message = "Name should not be null")
    private String name;

    @NotNull(message = "Designation should not be null")
    private String designation;

    @NotNull(message = "Salary should be not null")
    @Min(value = 1, message = "Salary should be greater than 0")
    private Long salary;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }
}
