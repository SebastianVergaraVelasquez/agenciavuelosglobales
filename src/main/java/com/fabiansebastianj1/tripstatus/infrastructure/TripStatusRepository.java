package com.fabiansebastianj1.tripstatus.infrastructure;

import java.util.List;
import java.util.Optional;

import com.fabiansebastianj1.tripstatus.domain.models.TripStatus;

public interface TripStatusRepository {
    void save(TripStatus tripStatus);
    void update(TripStatus tripStatus);
    Optional<TripStatus> findById(int id);
    void delete(int id);
    List<TripStatus> findAll();
}
