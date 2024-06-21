package com.fabiansebastianj1.revemployee.infraestructure;

import com.fabiansebastianj1.revemployee.domain.models.RevEmployee;

public interface RevEmployeeRepository {
    void save(RevEmployee revEmployee);
}
