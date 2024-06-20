package com.fabiansebastianj1.employee.infrastructure;

import java.util.List;
import java.util.Optional;

import com.fabiansebastianj1.employee.domain.models.Employee;

public interface EmployeeRepository {
    void save(Employee employee);
    void update(Employee employee);
    void delete(String id);
    Optional<Employee> findById(String id);
    List<Employee> findAll();
}
