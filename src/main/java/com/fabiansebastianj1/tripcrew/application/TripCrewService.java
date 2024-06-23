package com.fabiansebastianj1.tripcrew.application;

import com.fabiansebastianj1.connection.infraestructure.ConnectionRepository;
import com.fabiansebastianj1.tripcrew.domain.models.TripCrew;
import com.fabiansebastianj1.tripcrew.infrastructure.TripCrewRepository;

public class TripCrewService {

    private final TripCrewRepository tripCrewRepository;
    private final ConnectionRepository connectionRepository;

    public TripCrewService(TripCrewRepository tripCrewRepository, ConnectionRepository connectionRepository) {
        this.tripCrewRepository = tripCrewRepository;
        this.connectionRepository = connectionRepository;
    }

    public void createTripCrew(TripCrew tripCrew){
        tripCrewRepository.save(tripCrew);
    }

    public void findFlightNoConnection(){
        connectionRepository.findAll();
    }
}
