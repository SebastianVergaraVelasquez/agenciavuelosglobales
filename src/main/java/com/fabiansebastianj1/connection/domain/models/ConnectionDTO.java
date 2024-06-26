package com.fabiansebastianj1.connection.domain.models;

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
    private Double price;
    private int airlineId;
    private String airlineName;
    
    public ConnectionDTO(int tripId, int connection_id, String connectionNumber, int planeId, String startAirport, String arriveAirport, String tripDate, Double price) {
        this.tripId = tripId;
        this.connectionId = connection_id;
        this.connectionNumber = connectionNumber;
        this.planeId = planeId;
        this.startAirport = startAirport;
        this.arriveAirport = arriveAirport;
        this.tripDate = tripDate;
        this.price = price;
    }

    public ConnectionDTO(int tripId, int connectionId, String connectionNumber, int planeId, String planePlates, int airlineId, String airlineName, String startAirport,
            String arriveAirport, String tripDate, Double price) {
        this.tripId = tripId;
        this.connectionId = connectionId;
        this.connectionNumber = connectionNumber;
        this.planeId = planeId;
        this.planePlates = planePlates;
        this.airlineId = airlineId;
        this.airlineName= airlineName;
        this.startAirport = startAirport;
        this.arriveAirport = arriveAirport;
        this.tripDate = tripDate;
        this.price = price;
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

    public int getAirlineId() {
        return airlineId;
    }

    public void setAirlineId(int airlineId) {
        this.airlineId = airlineId;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    
 
}
