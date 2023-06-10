package com.jcanseco.dev.employees.api.service;

import com.jcanseco.dev.employees.api.dtos.CreateEmployeeDto;
import com.jcanseco.dev.employees.api.dtos.EmployeeDto;
import com.jcanseco.dev.employees.api.dtos.UpdateEmployeeDto;
import java.util.List;

public interface EmployeeService {
    EmployeeDto getEmployeeById(Long employeeId);
    EmployeeDto createEmployee(CreateEmployeeDto dto);
    EmployeeDto updateEmployee(UpdateEmployeeDto dto);
    void deleteEmployee(Long employeeId);
    List<EmployeeDto> getAllEmployees();
}
