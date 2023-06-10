package com.jcanseco.dev.employees.api.repositories;

import com.jcanseco.dev.employees.api.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> { }
