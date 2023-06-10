package com.jcanseco.dev.employees.api.persistence;

import com.jcanseco.dev.employees.api.base.MySQLTestBase;
import com.jcanseco.dev.employees.api.entities.Employee;
import com.jcanseco.dev.employees.api.repositories.EmployeeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DisplayName("Employees Repository Integration Tests")
public class EmployeesTests extends MySQLTestBase {
    @Autowired
    private EmployeeRepository repository;

    @AfterEach
    public void cleanup() {
        repository.deleteAll();
    }

    @Test
    @DisplayName("Create Employee")
    public void createEmployeeTest() {

        var newEntity = Employee.builder()
                .firstname("John")
                .lastname("Doe").build();

        var createdEntity = repository.saveAndFlush(newEntity);

        assertTrue(createdEntity.getId() > 0L);
        assertEquals(newEntity.getFirstname(), createdEntity.getFirstname());
        assertEquals(newEntity.getLastname(), createdEntity.getLastname());
    }
}
