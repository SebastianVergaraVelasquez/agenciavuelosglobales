package com.fabiansebastianj1.fare.application;

import java.util.List;
import java.util.Optional;

import com.fabiansebastianj1.fare.infrastructure.FareRepository;
import com.fabiansebastianj1.fare.domain.models.Fare;

public class FareService {
    private final FareRepository fareRepository;

    public FareService (FareRepository fareRepository){
        this.fareRepository = fareRepository;
    }

    public void createFare(Fare fare){
        fareRepository.save(fare);
    }

    public Optional<Fare> findFareById (int id){
        return fareRepository.findById(id);
    }

    public List<Fare> findAllFares(){
        return fareRepository.findAll();
    }

    public void deleteFare(int id){
        fareRepository.delete(id);
    }

    public void updateFare(Fare fare){
        fareRepository.update(fare);
    }
}
