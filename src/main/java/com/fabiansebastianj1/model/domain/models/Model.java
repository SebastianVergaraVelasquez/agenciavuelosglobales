package com.fabiansebastianj1.model.domain.models;

public class Model {
    private int id;
    private String name;
    private int manufacturerId;
    
    public Model(int id, String name, int manufacturerId) {
        this.id = id;
        this.name = name;
        this.manufacturerId = manufacturerId;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getManufacturerId() {
        return manufacturerId;
    }
    public void setManufacturerId(int manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    
}
