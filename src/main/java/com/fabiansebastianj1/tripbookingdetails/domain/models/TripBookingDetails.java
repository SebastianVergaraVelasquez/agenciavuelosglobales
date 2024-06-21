package com.fabiansebastianj1.tripbookingdetails.domain.models;

public class TripBookingDetails {
    private int id;
    private int tripBookingId;
    private String customerId;
    private int fareId;
    
    public TripBookingDetails(int id, int tripBookingId, String customerId, int fareId) {
        this.id = id;
        this.tripBookingId = tripBookingId;
        this.customerId = customerId;
        this.fareId = fareId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTripBookingId() {
        return tripBookingId;
    }

    public void setTripBookingId(int tripBookingId) {
        this.tripBookingId = tripBookingId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public int getFareId() {
        return fareId;
    }

    public void setFareId(int fareId) {
        this.fareId = fareId;
    }

    
}
