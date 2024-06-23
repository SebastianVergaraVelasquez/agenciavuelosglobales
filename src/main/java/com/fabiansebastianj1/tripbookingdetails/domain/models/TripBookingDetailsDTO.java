package com.fabiansebastianj1.tripbookingdetails.domain.models;

public class TripBookingDetailsDTO {
    private int id;
    private int tripBookingId;
    private int idTrip;
    private String customerId;
    private String customerName;
    private String fareName;
    
    public TripBookingDetailsDTO(int id, int tripBookingId, int idTrip, String customerId, String customerName,
            String fareName) {
        this.id = id;
        this.tripBookingId = tripBookingId;
        this.idTrip = idTrip;
        this.customerId = customerId;
        this.customerName = customerName;
        this.fareName = fareName;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getTripBookingId() {
        return tripBookingId;
    }
    public void setTripBookingId(int tripBookingId) {
        this.tripBookingId = tripBookingId;
    }
    public int getIdTrip() {
        return idTrip;
    }
    public void setIdTrip(int idTrip) {
        this.idTrip = idTrip;
    }
    public String getCustomerId() {
        return customerId;
    }
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public String getFareName() {
        return fareName;
    }
    public void setFareName(String fareName) {
        this.fareName = fareName;
    }

    

    
}
