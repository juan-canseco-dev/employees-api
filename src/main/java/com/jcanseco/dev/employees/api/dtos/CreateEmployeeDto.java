package com.jcanseco.dev.employees.api.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateEmployeeDto {
    @NotEmpty
    private String firstname;
    @NotEmpty
    private String lastname;
}
