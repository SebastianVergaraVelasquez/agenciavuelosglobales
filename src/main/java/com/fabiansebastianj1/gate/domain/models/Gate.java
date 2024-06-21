package com.fabiansebastianj1.gate.domain.models;

public class Gate {
    private int id;
    private String gateNumber;
    private String airportId;

    public Gate(int id, String gateNumber, String airportId) {
        this.id = id;
        this.gateNumber = gateNumber;
        this.airportId = airportId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGateNumber() {
        return gateNumber;
    }

    public void setGateNumber(String gateNumber) {
        this.gateNumber = gateNumber;
    }

    public String getAirportId() {
        return airportId;
    }

    public void setAirportId(String airportId) {
        this.airportId = airportId;
    }
     
}
