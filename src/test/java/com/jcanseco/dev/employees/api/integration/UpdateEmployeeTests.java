package com.jcanseco.dev.employees.api.integration;

import com.jcanseco.dev.employees.api.base.MySQLTestBase;
import com.jcanseco.dev.employees.api.dtos.UpdateEmployeeDto;
import com.jcanseco.dev.employees.api.entities.Employee;
import com.jcanseco.dev.employees.api.repositories.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.hamcrest.Matchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UpdateEmployeeTests extends MySQLTestBase {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;
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
    public void updateEmployeeStatusShouldBeOk() throws Exception {

        var employeeId = employee.getId();
        var updateDto = new UpdateEmployeeDto(employeeId, "John Maximum", "Dax Doe");

        var request = MockMvcRequestBuilders
                .put("/employees/" + employeeId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(updateDto));

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.notNullValue()))
                .andExpect(jsonPath("$.firstname").value(updateDto.getFirstname()))
                .andExpect(jsonPath("$.lastname").value(updateDto.getLastname()));
    }

    @Test
    public void updateEmployeeWhenModelHasErrorStatusShouldBeBadRequest() throws Exception {
        var employeeId = -1L;
        var updateDto = new UpdateEmployeeDto(employeeId, "", "");

        var request = MockMvcRequestBuilders
                .put("/employees/" + employeeId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(updateDto));

        mockMvc.perform(request)
                .andExpect(status().isBadRequest());
    }

    @Test
    public void updateEmployeeWhenEmployeesNotExistsStatusShouldBeNotFound() throws Exception {

        var employeeId = 5000L;
        var updateDto = new UpdateEmployeeDto(employeeId, "Not Found", "Doe");

        var request = MockMvcRequestBuilders
                .put("/employees/" + employeeId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(updateDto));

        mockMvc.perform(request)
                .andExpect(status().isNotFound());
    }
}
