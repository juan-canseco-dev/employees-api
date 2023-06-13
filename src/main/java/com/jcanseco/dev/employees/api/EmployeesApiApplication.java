package com.jcanseco.dev.employees.api;

import com.jcanseco.dev.employees.api.entities.Employee;
import com.jcanseco.dev.employees.api.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class EmployeesApiApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(EmployeesApiApplication.class, args);
    }

    @Autowired
    private EmployeeRepository repository;

    @Override
    public void run(String... args) throws Exception {
        repository.saveAllAndFlush(
                List.of(
                   Employee.builder().firstname("John").lastname("Doe").build(),
                   Employee.builder().firstname("Jane").lastname("Doe").build()
                ));
    }
}
