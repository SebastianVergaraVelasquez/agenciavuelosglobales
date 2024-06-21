package com.fabiansebastianj1.revemployee.application;

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
}
