package com.fabiansebastianj1.fare.infrastructure;

import java.util.List;
import java.util.Optional;

import com.fabiansebastianj1.fare.domain.models.Fare;

public interface FareRepository {
    void save(Fare fare);
    void update(Fare fare);
    void delete(int id);
    Optional<Fare> findById(int id);
    List<Fare> findAll();
}
