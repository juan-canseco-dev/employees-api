package com.jcanseco.dev.employees.api.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEmployeeDto {
    @Min(1)
    private Long employeeId;
    @NotEmpty
    private String firstname;
    @NotEmpty
    private String lastname;
}
