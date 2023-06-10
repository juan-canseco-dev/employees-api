package com.jcanseco.dev.employees.api.controllers;

import com.jcanseco.dev.employees.api.dtos.CreateEmployeeDto;
import com.jcanseco.dev.employees.api.dtos.EmployeeDto;
import com.jcanseco.dev.employees.api.dtos.UpdateEmployeeDto;
import com.jcanseco.dev.employees.api.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Validated
@AllArgsConstructor
@RestControllerAdvice
@RequestMapping("employees")
@RestController
public class EmployeesController {

    private final EmployeeService service;

    @PostMapping
    public ResponseEntity<EmployeeDto> create(@RequestBody @Valid CreateEmployeeDto dto) {
        return ResponseEntity.ok(service.createEmployee(dto));
    }

    @PutMapping("{employeeId}")
    public ResponseEntity<EmployeeDto> update(@PathVariable Long employeeId, @RequestBody @Valid UpdateEmployeeDto dto) {
        if (!dto.getEmployeeId().equals(employeeId)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(service.updateEmployee(dto));
    }

    @GetMapping("{employeeId}")
    public ResponseEntity<EmployeeDto> getById(@PathVariable Long employeeId) {
        return ResponseEntity.ok(service.getEmployeeById(employeeId));
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAll() {
        return ResponseEntity.ok(service.getAllEmployees());
    }

    @DeleteMapping("{employeeId}")
    public ResponseEntity<?> delete(@PathVariable Long employeeId) {
        service.deleteEmployee(employeeId);
        return ResponseEntity.ok().build();
    }
}
