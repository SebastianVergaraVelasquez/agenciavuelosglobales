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

     public void createManufacturer(Employee employee){
        employeeRepository.save(employee);
    }

    public Optional<Employee> findManufacturerById (int id){
        return employeeRepository.findById(id);
    }

    public List<Employee> findAllManufacturers(){
        return employeeRepository.findAll();
    }

    public void deleteManufacturer(int id){
        employeeRepository.delete(id);
    }

    public void updateManufacturer(Employee employee){
        employeeRepository.update(employee);
    }
}
