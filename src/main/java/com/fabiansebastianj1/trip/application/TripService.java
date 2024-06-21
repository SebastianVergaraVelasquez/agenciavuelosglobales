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

    public void createTrip(Trip trip){
        tripRepository.save(trip);
    }

    public Optional<Trip> findTripById (int id){
        return tripRepository.findById(id);
    }

    public List<Trip> findAllTrips(){
        return tripRepository.findAll();
    }

    public void deleteTrip(int id){
        tripRepository.delete(id);
    }

    public void updateTrip(Trip trip){
        tripRepository.update(trip);
    }
}
