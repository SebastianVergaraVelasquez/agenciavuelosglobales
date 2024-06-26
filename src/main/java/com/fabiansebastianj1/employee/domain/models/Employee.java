package com.fabiansebastianj1.employee.domain.models;

public class Employee {
    private String id;
    private String name;
    private int rolId;
    private String ingressDate;
    private int airlineId;
    private String airportId;
    
    public Employee(String id, String name, int rolId, String ingressDate, int airlineId, String airportId) {
        this.id = id;
        this.name = name;
        this.rolId = rolId;
        this.ingressDate = ingressDate;
        this.airlineId = airlineId;
        this.airportId = airportId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRolId() {
        return rolId;
    }

    public void setRolId(int rolId) {
        this.rolId = rolId;
    }

    public String getIngressDate() {
        return ingressDate;
    }

    public void setIngressDate(String ingressDate) {
        this.ingressDate = ingressDate;
    }

    public int getAirlineId() {
        return airlineId;
    }

    public void setAirlineId(int airlineId) {
        this.airlineId = airlineId;
    }

    public String getAirportId() {
        return airportId;
    }

    public void setAirportId(String airportId) {
        this.airportId = airportId;
    }

    
}
