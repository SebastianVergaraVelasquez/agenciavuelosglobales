package com.fabiansebastianj1.passenger.domain.models;

public class Passenger {
    private int id;
    private String nif;
    private String name;
    private int age;
    private String seat;
    private int documentTypeId;
    private int tripBookingDetailId;
    
    public Passenger(int id, String nif, String name, int age, String seat, int documentTypeId,
            int tripBookingDetailId) {
        this.id = id;
        this.nif = nif;
        this.name = name;
        this.age = age;
        this.seat = seat;
        this.documentTypeId = documentTypeId;
        this.tripBookingDetailId = tripBookingDetailId;
    }

    public Passenger(String nif, String name, int age, String seat, int documentTypeId, int tripBookingDetailId) {
        this.nif = nif;
        this.name = name;
        this.age = age;
        this.seat = seat;
        this.documentTypeId = documentTypeId;
        this.tripBookingDetailId = tripBookingDetailId;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public int getDocumentTypeId() {
        return documentTypeId;
    }

    public void setDocumentTypeId(int documentTypeId) {
        this.documentTypeId = documentTypeId;
    }

    public int getTripBookingDetailId() {
        return tripBookingDetailId;
    }

    public void setTripBookingDetailId(int tripBookingDetailId) {
        this.tripBookingDetailId = tripBookingDetailId;
    }

    
}
