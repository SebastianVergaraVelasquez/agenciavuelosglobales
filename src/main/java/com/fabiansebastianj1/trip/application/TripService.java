package com.fabiansebastianj1.trip.application;

import java.util.List;
import java.util.Optional;

import com.fabiansebastianj1.trip.domain.models.Trip;
import com.fabiansebastianj1.trip.infrastructure.TripRepository;

public class TripService {

    private final TripRepository tripRepository;

    public TripService (TripRepository tripRepository){
        this.tripRepository = tripRepository;
    }

    public void createManufacturer(Trip trip){
        tripRepository.save(trip);
    }

    public Optional<Trip> findManufacturerById (int id){
        return tripRepository.findById(id);
    }

    public List<Trip> findAllManufacturers(){
        return tripRepository.findAll();
    }

    public void deleteManufacturer(int id){
        tripRepository.delete(id);
    }

    public void updateManufacturer(Trip trip){
        tripRepository.update(trip);
    }
}
