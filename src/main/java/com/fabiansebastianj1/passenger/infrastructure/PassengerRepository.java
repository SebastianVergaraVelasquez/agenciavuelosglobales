package com.fabiansebastianj1.passenger.infrastructure;

import java.util.List;

public interface PassengerRepository {
    List<String> getOccupiedSeats(int tripId);
    int getTotalOccupiedSeats(int tripId);
}
