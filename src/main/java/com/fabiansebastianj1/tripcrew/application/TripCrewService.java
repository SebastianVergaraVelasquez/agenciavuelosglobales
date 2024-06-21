package com.fabiansebastianj1.tripcrew.application;

import com.fabiansebastianj1.tripcrew.domain.models.TripCrew;
import com.fabiansebastianj1.tripcrew.infrastructure.TripCrewRepository;

public class TripCrewService {

    private final TripCrewRepository tripCrewRepository;

    public TripCrewService(TripCrewRepository tripCrewRepository) {
        this.tripCrewRepository = tripCrewRepository;
    }

    public void createTripCrew(TripCrew tripCrew){
        tripCrewRepository.save(tripCrew);
    }
}
