package com.fabiansebastianj1.tripulationrole.infrastructure;

import java.util.List;
import java.util.Optional;

import com.fabiansebastianj1.tripulationrole.domain.models.TripulationRole;

public interface TripulationRoleRepository {
    void save(TripulationRole tripulationRole);
    void update(TripulationRole tripulationRole);
    void delete(int tripBooking);
    Optional<TripulationRole> findById(int id);
    List<TripulationRole> findAll();
}
