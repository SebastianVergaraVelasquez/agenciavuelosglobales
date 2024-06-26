package com.fabiansebastianj1.passenger.application;

import java.util.List;

import com.fabiansebastianj1.passenger.infrastructure.PassengerRepository;

public class PassengerService {

    private final PassengerRepository passengerRepository;

    public PassengerService(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }

    public List<String>getOccupiedSeats(int id){
        return passengerRepository.getOccupiedSeats(id);
    }

    public int getTotalOccupiedSeats(int id){
        return passengerRepository.getTotalOccupiedSeats(id);
    }
}

