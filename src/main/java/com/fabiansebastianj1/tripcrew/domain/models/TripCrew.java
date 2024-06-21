package com.fabiansebastianj1.tripcrew.domain.models;

public class TripCrew {
    private String employeeId;
    private int connectionId;
    
    public TripCrew(String employeeId, int connectionId) {
        this.employeeId = employeeId;
        this.connectionId = connectionId;
    }
    public String getEmployeeId() {
        return employeeId;
    }
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
    public int getConnectionId() {
        return connectionId;
    }
    public void setConnectionId(int connectionId) {
        this.connectionId = connectionId;
    }

    
}
