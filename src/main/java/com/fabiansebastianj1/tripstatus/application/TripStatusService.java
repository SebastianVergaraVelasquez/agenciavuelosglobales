package com.fabiansebastianj1.tripstatus.application;

import java.util.List;
import java.util.Optional;

import com.fabiansebastianj1.tripstatus.domain.models.*;
import com.fabiansebastianj1.tripstatus.infrastructure.TripStatusRepository;

public class TripStatusService {

    private final TripStatusRepository tripStatusRepository;

    public TripStatusService (TripStatusRepository tripStatusRepository){
        this.tripStatusRepository = tripStatusRepository;
    }

    public void createTripStatus(TripStatus tripStatus){
        tripStatusRepository.save(tripStatus);
    }

    public Optional<TripStatus> findTripStatusById (int id){
        return tripStatusRepository.findById(id);
    }

    public List<TripStatus> findAllTripStatuses(){
        return tripStatusRepository.findAll();
    }

    public void deleteTripStatus(int id){
        tripStatusRepository.delete(id);
    }

    public void updateTripStatus(TripStatus tripStatus){
        tripStatusRepository.update(tripStatus);
    }
}
