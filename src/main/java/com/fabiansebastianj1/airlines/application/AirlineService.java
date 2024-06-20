package com.fabiansebastianj1.airlines.application;

import java.util.List;
import java.util.Optional;

import com.fabiansebastianj1.airlines.domain.models.Airline;
import com.fabiansebastianj1.airlines.infrastructure.AirlineRepository;

public class AirlineService {

    private final AirlineRepository airlineRepository;

    public AirlineService(AirlineRepository airlineRepository){
        this.airlineRepository = airlineRepository;
    }

    public void createAirline(Airline airline){
        airlineRepository.save(airline);
    }

    public Optional<Airline> findAirlineById (int id){
        return airlineRepository.findById(id);
    }

    public List<Airline> findAllAirlines(){
        return airlineRepository.findAll();
    }

    public void deleteAirline(int id){
        airlineRepository.delete(id);
    }

    public void updateAirline(Airline airline){
        airlineRepository.update(airline);
    }
}
