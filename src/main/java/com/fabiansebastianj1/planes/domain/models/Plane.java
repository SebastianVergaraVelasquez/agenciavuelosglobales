package com.fabiansebastianj1.planes.domain.models;

import java.sql.Date;

public class Plane {

    private int id;
    private String plates;
    private int capacity;
    private Date fabricationDate;
    private int statusId;
    private int airlineId;
    private int modelId;
    
    public Plane(int id, String plates, int capacity, Date fabricationDate, int statusId, int airlineId, int modelId) {
        this.id = id;
        this.plates = plates;
        this.capacity = capacity;
        this.fabricationDate = fabricationDate;
        this.statusId = statusId;
        this.airlineId = airlineId;
        this.modelId = modelId;
    }

    public Plane(String plates, int capacity, Date fabricationDate, int statusId, int airlineId, int modelId) {
        this.plates = plates;
        this.capacity = capacity;
        this.fabricationDate = fabricationDate;
        this.statusId = statusId;
        this.airlineId = airlineId;
        this.modelId = modelId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlates() {
        return plates;
    }

    public void setPlates(String plates) {
        this.plates = plates;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Date getFabricationDate() {
        return fabricationDate;
    }

    public void setFabricationDate(Date fabricationDate) {
        this.fabricationDate = fabricationDate;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public int getAirlineId() {
        return airlineId;
    }

    public void setAirlineId(int airlineId) {
        this.airlineId = airlineId;
    }

    public int getModelId() {
        return modelId;
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
    } 
}
