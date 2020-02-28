package com.avinandan.ganguly.example_database.controller;

import com.avinandan.ganguly.example_database.bean.entity.Employee;
import com.avinandan.ganguly.example_database.bean.request.EmployeeRequest;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc
@SpringBootTest
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    private static EmployeeRequest employeeRequest;

    @BeforeAll
    public static void setUp() {
        employeeRequest = new EmployeeRequest();
        employeeRequest.setName("Avinandan");
        employeeRequest.setDesignation("trainee");
        employeeRequest.setSalary(123654L);
    }

    @Order(2)
    @Test
    void getAllEmployees() throws Exception {

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v1/employees");
        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andReturn();

        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        int statusCode = mockHttpServletResponse.getStatus();

        assertEquals(200, statusCode);

        String contentAsString = mockHttpServletResponse.getContentAsString();
        System.out.println(contentAsString);

        List<Employee> employees = objectMapper.readValue(contentAsString, new TypeReference<List<Employee>>() {
        });

        Employee employee = employees.get(0);

        assertEquals(employee.getName(), employeeRequest.getName());
        assertEquals(employee.getSalary(), employeeRequest.getSalary());
        assertEquals(employee.getDesignation(), employeeRequest.getDesignation());
        assertTrue(employee.getEmployeeId() > 0);

    }

    @Order(3)
    @Test
    void getEmployee() throws Exception {

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v1/employees");
        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andReturn();

        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        int statusCode = mockHttpServletResponse.getStatus();

        assertEquals(200, statusCode);

        String contentAsString = mockHttpServletResponse.getContentAsString();
        System.out.println(contentAsString);

        Employee employee = objectMapper.readValue(contentAsString, new TypeReference<Employee>() {
        });

        assertEquals(employee.getName(), employeeRequest.getName());
        assertEquals(employee.getSalary(), employeeRequest.getSalary());
        assertEquals(employee.getDesignation(), employeeRequest.getDesignation());
        assertTrue(employee.getEmployeeId() > 0);

    }

    @Order(1)
    @Test
    void createEmployee() throws Exception {

        String requestBody = objectMapper.writeValueAsString(employeeRequest);
        System.out.println(requestBody);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/v1/employees")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestBody);

        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andReturn();

        MockHttpServletResponse mockMvcResponse = mvcResult.getResponse();

        int statusCode = mockMvcResponse.getStatus();
        String response = mockMvcResponse.getContentAsString();
        System.out.println(response);

        assertEquals(201, statusCode);

        Employee employee = objectMapper.readValue(response, Employee.class);

        assertEquals(employeeRequest.getName(), employee.getName());
        assertEquals(employeeRequest.getDesignation(), employee.getDesignation());
        assertEquals(employeeRequest.getSalary(), employee.getSalary());
        assertTrue(employee.getEmployeeId() > 0);

    }

    @Test
    void updateEmployee() {


    }

    @Test
    void deleteEmployee() {
    }
}