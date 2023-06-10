package com.jcanseco.dev.employees.api.integration;

import com.jcanseco.dev.employees.api.base.MySQLTestBase;
import com.jcanseco.dev.employees.api.entities.Employee;
import com.jcanseco.dev.employees.api.repositories.EmployeeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
public class DeleteEmployeeTests extends MySQLTestBase {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeeRepository repository;

    private Employee createdEmployee;
    @BeforeEach
    public void setup() {
        createdEmployee = this.repository.saveAndFlush(Employee
                .builder()
                .firstname("John")
                .lastname("Doe")
                .build());
    }
    @AfterEach
    public void cleanup() {
        repository.deleteAll();
    }

    @Test
    public void deleteEmployeeStatusShouldBeOk() throws Exception {

        var employeeId = createdEmployee.getId();

        var request = MockMvcRequestBuilders
                .delete("/employees/" + employeeId)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk());

        var deletedEmployee = repository.findById(employeeId);
        assertTrue(deletedEmployee.isEmpty());
    }

    @Test
    public void deleteEmployeeWhenEmployeeNotExistsStatusShouldBeNotFound() throws Exception {
        var employeeId = 9888L;

        var request = MockMvcRequestBuilders
                .delete("/employees/" + employeeId)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isNotFound());
    }
}
