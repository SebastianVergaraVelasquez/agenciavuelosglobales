package com.fabiansebastianj1.trip.domain.models;

import java.sql.Date;

public class Trip {
    private int id;
    private String date;
    private double price;
    private int tripConditionId;

    public Trip(int id, String date, double price, int tripConditionId) {
        this.id = id;
        this.date = date;
        this.price = price;
        this.tripConditionId = tripConditionId;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getTripConditionId() {
        return tripConditionId;
    }

    public void setTripConditionId(int tripConditionId) {
        this.tripConditionId = tripConditionId;
    }

}
