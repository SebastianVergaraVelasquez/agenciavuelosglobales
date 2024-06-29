package com.fabiansebastianj1.trip.domain.models;

public class Trip {
    private int id;
    private String date;
    private double price;

    public Trip(int id, String date, double price) {
        this.id = id;
        this.date = date;
        this.price = price;
    }

    public Trip(String date, double price) {
        this.date = date;
        this.price = price;
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
}
