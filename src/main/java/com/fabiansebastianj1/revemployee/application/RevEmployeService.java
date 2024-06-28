package com.fabiansebastianj1.revemployee.application;

import java.util.Optional;

import com.fabiansebastianj1.revemployee.domain.models.RevEmployee;
import com.fabiansebastianj1.revemployee.infraestructure.RevEmployeeRepository;

public class RevEmployeService {
    private final RevEmployeeRepository revEmployeeRepository;

    public RevEmployeService(RevEmployeeRepository revEmployeeRepository) {
        this.revEmployeeRepository = revEmployeeRepository;
    }

    public void save(RevEmployee revEmployee) {
        revEmployeeRepository.save(revEmployee);
    }

    public Optional<RevEmployee> findRevEmployeeById(int id) {
        return revEmployeeRepository.findRevEmployeeById(id);
    }

    public void updateRevEmploye(RevEmployee revEmployee) {
        revEmployeeRepository.update(revEmployee);
    }

    public void deleteRevEmployee(int id) {
        revEmployeeRepository.delete(id);
    }


}
