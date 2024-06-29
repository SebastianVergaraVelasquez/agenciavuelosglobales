package com.fabiansebastianj1.employee.infrastructure;

import java.util.List;
import java.util.Optional;

import com.fabiansebastianj1.employee.domain.models.Employee;
import com.fabiansebastianj1.employee.domain.models.EmployeeDTO;

public interface EmployeeRepository {
    void save(Employee employee);
    void update(Employee employee);
    void delete(String id);
    Optional<Employee> findById(String id);
    List<EmployeeDTO> findAll();
    List<EmployeeDTO> findAllTechniciansByAirline(int airlineId);
    List<EmployeeDTO> findAllTripulation(int airlineId);
}
