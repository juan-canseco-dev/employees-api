package com.jcanseco.dev.employees.api.integration;

import com.jcanseco.dev.employees.api.base.MySQLTestBase;
import com.jcanseco.dev.employees.api.entities.Employee;
import com.jcanseco.dev.employees.api.repositories.EmployeeRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class GetEmployeeTests extends MySQLTestBase {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private EmployeeRepository repository;
    private Employee employee;
    @BeforeEach
    public void setup() {
        employee = repository.saveAndFlush(
                Employee.builder().firstname("John").lastname("Doe").build()
        );
    }
    @AfterEach
    public void cleanup() {
        repository.deleteAll();
    }
    @Test
    public void getEmployeeStatusShouldBeOk() throws Exception {

        var employeeId = employee.getId();

        var request = MockMvcRequestBuilders
                .get("/employees/" + employeeId)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.notNullValue()))
                .andExpect(jsonPath("$.id").value(employee.getId()))
                .andExpect(jsonPath("$.firstname").value(employee.getFirstname()))
                .andExpect(jsonPath("$.lastname").value(employee.getLastname()));
    }

    @Test
    public void getEmployeeWhenNotExistsStatusShouldBeNotFound() throws Exception {
        var employeeId = 9000L;

        var request = MockMvcRequestBuilders
                .get("/employees/" + employeeId)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isNotFound());
    }
}
