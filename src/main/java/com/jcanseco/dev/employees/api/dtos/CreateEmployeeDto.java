package com.jcanseco.dev.employees.api.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateEmployeeDto {
    private String firstname;
    private String lastname;
}
