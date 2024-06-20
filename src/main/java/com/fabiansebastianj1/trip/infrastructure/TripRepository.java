package com.fabiansebastianj1.trip.infrastructure;

import java.util.List;
import java.util.Optional;

import com.fabiansebastianj1.trip.domain.models.Trip;

public interface TripRepository {
    void save(Trip trip);
    void update(Trip trip);
    void delete(int id);
    Optional<Trip> findById(int id);
    List<Trip> findAll();
}
