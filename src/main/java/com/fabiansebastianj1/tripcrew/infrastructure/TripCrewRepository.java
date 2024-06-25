package com.fabiansebastianj1.tripcrew.infrastructure;

import java.util.List;

import com.fabiansebastianj1.tripcrew.domain.models.TripCrew;
import com.fabiansebastianj1.tripcrew.domain.models.TripCrewDTO;

public interface TripCrewRepository {
    void save(TripCrew tripCrew);
    List<TripCrewDTO> listTripCrewDTO(int id);
}

