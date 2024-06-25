package com.fabiansebastianj1.revemployee.infraestructure;

import java.util.List;
import java.util.Optional;

import com.fabiansebastianj1.revemployee.domain.models.RevEmployee;

public interface RevEmployeeRepository {
    void save(RevEmployee revEmployee);
    Optional<RevEmployee> findRevEmployeeById(int id);
    void update(RevEmployee revEmployee);
    void delete(int id);
}
