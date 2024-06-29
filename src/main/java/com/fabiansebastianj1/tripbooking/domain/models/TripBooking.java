package com.fabiansebastianj1.tripbooking.domain.models;

public class TripBooking {
    private int id;
    private String date;
    private int id_trip;

    public TripBooking(String date, int id_trip) {
        this.date = date;
        this.id_trip = id_trip;
    }

    public TripBooking(int id, String date, int id_trip) {
        this.id = id;
        this.date = date;
        this.id_trip = id_trip;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId_trip() {
        return id_trip;
    }

    public void setId_trip(int id_trip) {
        this.id_trip = id_trip;
    }
}
