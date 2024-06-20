package com.fabiansebastianj1.employee.domain.models;

import java.sql.Date;

public class Employee {
    private String id;
    private String name;
    private int rolId;
    private Date ingressDate;
    private int airlineId;
    private int airportId;
    
    public Employee(String id, String name, int rolId, Date ingressDate, int airlineId, int airportId) {
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

    public Date getIngressDate() {
        return ingressDate;
    }

    public void setIngressDate(Date ingressDate) {
        this.ingressDate = ingressDate;
    }

    public int getAirlineId() {
        return airlineId;
    }

    public void setAirlineId(int airlineId) {
        this.airlineId = airlineId;
    }

    public int getAirportId() {
        return airportId;
    }

    public void setAirportId(int airportId) {
        this.airportId = airportId;
    }

    
}
