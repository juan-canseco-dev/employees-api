package com.jcanseco.dev.employees.api.dtos;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
    private Long id;
    private String firstname;
    private String lastname;
}
