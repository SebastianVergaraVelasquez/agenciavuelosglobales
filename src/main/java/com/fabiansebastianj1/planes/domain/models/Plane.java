package com.fabiansebastianj1.planes.domain.models;

import java.sql.Date;

public class Plane {

    private int id;
    private String plates;
    private int capacity;
    private Date fabrication_date;
    private int id_status;
    private int id_model;
    
    public Plane(int id, String plates, int capacity, Date fabrication_date, int id_status, int id_model) {
        this.id = id;
        this.plates = plates;
        this.capacity = capacity;
        this.fabrication_date = fabrication_date;
        this.id_status = id_status;
        this.id_model = id_model;
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

    public Date getFabrication_date() {
        return fabrication_date;
    }

    public void setFabrication_date(Date fabrication_date) {
        this.fabrication_date = fabrication_date;
    }

    public int getId_status() {
        return id_status;
    }

    public void setId_status(int id_status) {
        this.id_status = id_status;
    }

    public int getId_model() {
        return id_model;
    }

    public void setId_model(int id_model) {
        this.id_model = id_model;
    }
}
