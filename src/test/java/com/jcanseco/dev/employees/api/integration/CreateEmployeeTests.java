package com.jcanseco.dev.employees.api.integration;

import com.jcanseco.dev.employees.api.base.MySQLTestBase;
import com.jcanseco.dev.employees.api.dtos.CreateEmployeeDto;
import com.jcanseco.dev.employees.api.repositories.EmployeeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.hamcrest.Matchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CreateEmployeeTests extends MySQLTestBase {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private EmployeeRepository repository;
    @AfterEach
    public void cleanup() {
        repository.deleteAll();
    }
    @Test
    public void createEmployeeStatusShouldBeOk() throws Exception {

        var createdDto = new CreateEmployeeDto("John", "Doe");

        var request = MockMvcRequestBuilders
                .post("/employees")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(createdDto));

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.notNullValue()))
                .andExpect(jsonPath("$.firstname").value("John"))
                .andExpect(jsonPath("$.lastname").value("Doe"));
    }

    @Test
    public void createEmployeeWhenValidationErrorStatusShouldBe400() throws Exception{
        var createdDto = new CreateEmployeeDto("", "Doe");

        var request = MockMvcRequestBuilders
                .post("/employees")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(createdDto));

        mockMvc.perform(request)
                .andExpect(status().isBadRequest());
    }

}
