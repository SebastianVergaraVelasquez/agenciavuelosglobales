package com.fabiansebastianj1.connection.domain.models;

import java.sql.Date;

//Esta clase se utiliza para recibir una consulta de varias tablas.

public class ConnectionDTO {
    private int tripId;
    private int connectionId;
    private String connectionNumber;
    private String startAirport;
    private String arriveAirport;
    private int planeId;
    private String planePlates;
    private String tripDate;
    
    public ConnectionDTO(int tripId, int connection_id, String connectionNumber, String startAirport, String arriveAirport, String tripDate) {
        this.tripId = tripId;
        this.connectionId = connection_id;
        this.connectionNumber = connectionNumber;
        this.startAirport = startAirport;
        this.arriveAirport = arriveAirport;
        this.tripDate = tripDate;
    }

    //Este es para mostrar la información mofificable de la escala
    public ConnectionDTO( int connectionId, String connectionNumber, int tripId, String startAirport, int planeId, String planePlates) {
        this.connectionId = connectionId;
        this.tripId = tripId;
        this.connectionNumber = connectionNumber;
        this.startAirport = startAirport;
        this.planeId = planeId;
        this.planePlates = planePlates;
    }

    public ConnectionDTO(String connectionNumber, String startAirport) {
        this.connectionNumber = connectionNumber;
        this.startAirport = startAirport;
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

    public String getConnectionNumber() {
        return connectionNumber;
    }

    public void setConnectionNumber(String connectionNumber) {
        this.connectionNumber = connectionNumber;
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

    public int getPlaneId() {
        return planeId;
    }

    public void setPlaneId(int planeId) {
        this.planeId = planeId;
    }

    public String getPlanePlates() {
        return planePlates;
    }

    public void setPlanePlates(String planePlates) {
        this.planePlates = planePlates;
    }

    public String getTripDate() {
        return tripDate;
    }

    public void setTripDate(String tripDate) {
        this.tripDate = tripDate;
    }

    
 
}
