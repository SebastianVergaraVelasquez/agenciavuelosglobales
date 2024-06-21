package com.fabiansebastianj1.manufacturers.infrastructure;

import com.fabiansebastianj1.manufacturers.domain.models.Model;
import java.util.Optional;
import java.util.List;

public interface ManufacturerRepository {
    void save(Model manufacturer);
    void update(Model manufacturer);
    void delete(int id);
    Optional<Model> findById(int id);
    List<Model> findAll();
}
