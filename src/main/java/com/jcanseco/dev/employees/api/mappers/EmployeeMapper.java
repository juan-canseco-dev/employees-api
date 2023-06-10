package com.jcanseco.dev.employees.api.mappers;

import com.jcanseco.dev.employees.api.dtos.CreateEmployeeDto;
import com.jcanseco.dev.employees.api.dtos.EmployeeDto;
import com.jcanseco.dev.employees.api.entities.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    EmployeeDto entityToDto(Employee entity);

    @Mapping(target = "id", ignore = true)
    Employee createDtoToEntity(CreateEmployeeDto dto);
}
