package com.fabiansebastianj1.airlines.infrastructure;

import com.fabiansebastianj1.airlines.domain.models.Airline;
import java.util.Optional;
import java.util.List;

public interface AirlineRepository {
    void save(Airline airline);
    boolean update(Airline airline);
    boolean delete(int id);
    Optional<Airline> findById(int id);
    List<Airline> findAll();
}
