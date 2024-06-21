package com.fabiansebastianj1.tripbooking.domain.models;

import java.sql.Date;

public class TripBooking {
    private int id;
    private Date date;
    private int id_trip;

    public TripBooking(int id, Date date, int id_trip) {
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

    public java.sql.Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId_trip() {
        return id_trip;
    }

    public void setId_trip(int id_trip) {
        this.id_trip = id_trip;
    }
}
