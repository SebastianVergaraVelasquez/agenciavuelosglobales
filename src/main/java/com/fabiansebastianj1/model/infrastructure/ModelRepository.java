package com.fabiansebastianj1.model.infrastructure;

import java.util.List;
import java.util.Optional;

import com.fabiansebastianj1.model.domain.models.Model;

public interface ModelRepository {
    void save(Model model);
    void update(Model model);
    void delete(int id);
    Optional<Model> findById(int id);
    List<Model> findAll();
}
