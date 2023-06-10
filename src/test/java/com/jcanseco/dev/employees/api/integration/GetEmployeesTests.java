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
import java.util.List;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.hamcrest.Matchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class GetEmployeesTests extends MySQLTestBase {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private EmployeeRepository repository;
    @BeforeEach
    public void setup() {
        repository.saveAllAndFlush(
                List.of(
                        Employee.builder().firstname("John").lastname("Doe").build(),
                        Employee.builder().firstname("Jane").lastname("Doe").build()));
    }
    @AfterEach
    public void cleanup() {
        repository.deleteAll();
    }

    @Test
    public void getEmployeesStatusShouldBeOk() throws Exception {

        var request = MockMvcRequestBuilders
                .get("/employees")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.notNullValue()))
                .andExpect(jsonPath("$.size()").value(2));
    }
}
