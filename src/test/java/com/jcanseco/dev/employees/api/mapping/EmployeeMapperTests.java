package com.jcanseco.dev.employees.api.mapping;

import com.jcanseco.dev.employees.api.dtos.CreateEmployeeDto;
import com.jcanseco.dev.employees.api.entities.Employee;
import com.jcanseco.dev.employees.api.mappers.EmployeeMapper;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import static org.junit.jupiter.api.Assertions.*;

public class EmployeeMapperTests {
    private final EmployeeMapper mapper = Mappers.getMapper(EmployeeMapper.class);
    @Test
    public void entityToDto() {

        assertNotNull(mapper);
        var entity = Employee.builder().id(1L).firstname("John").lastname("Doe").build();
        var dto = mapper.entityToDto(entity);

        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getFirstname(), dto.getFirstname());
        assertEquals(entity.getLastname(), dto.getLastname());
    }

    @Test
    public void createDtoToEntity() {

        assertNotNull(mapper);
        var dto = new CreateEmployeeDto("John", "Doe");
        var entity = mapper.createDtoToEntity(dto);

        assertEquals(entity.getFirstname(), dto.getFirstname());
        assertEquals(entity.getLastname(), dto.getLastname());
    }

}
