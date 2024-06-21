package com.fabiansebastianj1.tripulationrole.application;

import java.util.List;
import java.util.Optional;

import com.fabiansebastianj1.tripulationrole.domain.models.TripulationRole;
import com.fabiansebastianj1.tripulationrole.infrastructure.TripulationRoleRepository;

public class TripulationRoleService {

    private final TripulationRoleRepository tripulationRoleRepository;

    public TripulationRoleService(TripulationRoleRepository tripulationRoleRepository) {
        this.tripulationRoleRepository = tripulationRoleRepository;
    }

    public void createTripulationRole(TripulationRole tripulationRole) {
        tripulationRoleRepository.save(tripulationRole);
    }

    public void deleteTripulationRole(int id) {
        tripulationRoleRepository.delete(id);
    }

    public void updateTripulationRole(TripulationRole tripulationRole) {
        tripulationRoleRepository.update(tripulationRole);
    }

    public Optional<TripulationRole> findTripulationRoleById(int id) {
        return tripulationRoleRepository.findById(id);
    }

    public List<TripulationRole> findAllTripulationRoles() {
        return tripulationRoleRepository.findAll();
    }
}
