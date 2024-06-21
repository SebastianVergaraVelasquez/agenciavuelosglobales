package com.fabiansebastianj1.gate.infrastructure;

import java.util.List;
import java.util.Optional;
import com.fabiansebastianj1.gate.domain.models.Gate;

public interface GateRepository {
    void save(Gate gate);
    void update(Gate gat);
    void delete(int id);
    Optional<Gate> findById(int id);
    List<Gate> findAll();
}
