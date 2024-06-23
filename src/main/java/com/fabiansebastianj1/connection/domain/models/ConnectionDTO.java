package com.fabiansebastianj1.connection.domain.models;

import java.sql.Date;

//Esta clase se utiliza para recibir una consulta de varias tablas.

public class ConnectionDTO {
    private int tripId;
    private int connectionId;
    private String startAirport;
    private String arriveAirport;
    private String tripDate;
    
    public ConnectionDTO(int tripId, int connectionId, String startAirport, String arriveAirport, String tripDate) {
        this.tripId = tripId;
        this.connectionId = connectionId;
        this.startAirport = startAirport;
        this.arriveAirport = arriveAirport;
        this.tripDate = tripDate;
    }

    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    public int getConnectionId() {
        return connectionId;
    }

    public void setConnectionId(int connectionId) {
        this.connectionId = connectionId;
    }

    public String getStartAirport() {
        return startAirport;
    }

    public void setStartAirport(String startAirport) {
        this.startAirport = startAirport;
    }

    public String getArriveAirport() {
        return arriveAirport;
    }

    public void setArriveAirport(String arriveAirport) {
        this.arriveAirport = arriveAirport;
    }

    public String getTripDate() {
        return tripDate;
    }

    public void setTripDate(String tripDate) {
        this.tripDate = tripDate;
    }
    
    

    
}
