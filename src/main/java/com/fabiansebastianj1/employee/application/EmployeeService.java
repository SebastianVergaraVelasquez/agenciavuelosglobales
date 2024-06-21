package com.fabiansebastianj1.employee.application;

import java.util.List;
import java.util.Optional;

import com.fabiansebastianj1.employee.infrastructure.EmployeeRepository;
import com.fabiansebastianj1.employee.domain.models.*;

public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService (EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

     public void createEmployee(Employee employee){
        employeeRepository.save(employee);
    }

    public Optional<Employee> findEmployeeById (String id){
        return employeeRepository.findById(id);
    }

    public List<Employee> findAllEmployees(){
        return employeeRepository.findAll();
    }

    public void deleteEmployee(String id){
        employeeRepository.delete(id);
    }

    public void updateEmployee(Employee employee){
        employeeRepository.update(employee);
    }
}
