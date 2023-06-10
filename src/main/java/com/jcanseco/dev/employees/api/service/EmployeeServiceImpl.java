package com.jcanseco.dev.employees.api.service;

import com.jcanseco.dev.employees.api.dtos.CreateEmployeeDto;
import com.jcanseco.dev.employees.api.dtos.EmployeeDto;
import com.jcanseco.dev.employees.api.dtos.UpdateEmployeeDto;
import com.jcanseco.dev.employees.api.exceptions.NotFoundException;
import com.jcanseco.dev.employees.api.mappers.EmployeeMapper;
import com.jcanseco.dev.employees.api.repositories.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@AllArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService{

    private final EmployeeRepository repository;
    private final EmployeeMapper mapper;

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        return repository
                .findById(employeeId)
                .map(mapper::entityToDto)
                .orElseThrow(() -> new NotFoundException(String.format("Employee with the Id {%d} was not found.", employeeId)));
    }

    @Override
    public EmployeeDto createEmployee(CreateEmployeeDto dto) {
        var newEmployee = mapper.createDtoToEntity(dto);
        var createdEmployee = repository.saveAndFlush(newEmployee);
        return mapper.entityToDto(createdEmployee);
    }

    @Override
    public EmployeeDto updateEmployee(UpdateEmployeeDto dto) {
        var employee =  repository
                .findById(dto.getEmployeeId())
                .orElseThrow(() -> new NotFoundException(String.format("Employee with the Id {%d} was not found.", dto.getEmployeeId())));

        employee.setFirstname(dto.getFirstname());
        employee.setLastname(dto.getLastname());

        var updatedEmployee = repository.saveAndFlush(employee);
        return mapper.entityToDto(updatedEmployee);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        var employee =  repository
                .findById(employeeId)
                .orElseThrow(() -> new NotFoundException(String.format("Employee with the Id {%d} was not found.", employeeId)));

        repository.delete(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        return repository
                .findAll()
                .stream()
                .map(mapper::entityToDto)
                .toList();
    }
}
