package com.fabiansebastianj1.airport.infrastructure;

import java.util.List;
import java.util.Optional;

import com.fabiansebastianj1.airport.domain.models.City;

public interface AirportRepository {
    void save(City airport);

    void update(City airport);

    void delete(String id);

    Optional<City> findById(String id);

    List<City> findAll();
}
