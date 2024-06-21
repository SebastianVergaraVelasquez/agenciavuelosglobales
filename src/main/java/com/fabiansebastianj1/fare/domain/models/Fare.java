package com.fabiansebastianj1.fare.domain.models;

public class Fare {
    private int id;
    private String description;
    private String detail;
    private double value;
    
    public Fare(int id, String description, String detail, double value) {
        this.id = id;
        this.description = description;
        this.detail = detail;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    
}
