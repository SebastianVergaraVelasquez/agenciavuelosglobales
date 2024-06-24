package com.fabiansebastianj1.planes.domain.models;

public class PlaneDTO {
    private int id;
    private String plates;
    private int capacity;
    private String fabricationDate;
    private String statusName;
    private String AirlineName;
    private String modelName;

    public PlaneDTO(int id, String plates, int capacity, String fabricationDate, String statusName, String airlineName, String modelName) {
        this.id = id;
        this.plates = plates;
        this.capacity = capacity;
        this.fabricationDate = fabricationDate;
        this.statusName = statusName;
        AirlineName = airlineName;
        this.modelName = modelName;
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

    public String getFabricationDate() {
        return fabricationDate;
    }

    public void setFabricationDate(String fabricationDate) {
        this.fabricationDate = fabricationDate;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getAirlineName() {
        return AirlineName;
    }

    public void setAirlineName(String airlineName) {
        AirlineName = airlineName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }
}
