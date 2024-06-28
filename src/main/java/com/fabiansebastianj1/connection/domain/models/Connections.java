package com.fabiansebastianj1.connection.domain.models;

public class Connections {

    private int id;
    private String connection_number;
    private int id_trip;
    private Integer id_plane;
    private String id_airport;
    private int id_trip_status;

    public Connections() {
    }

    public Connections(int id, String connection_number, int id_trip, Integer id_plane, String id_airport, int id_trip_status) {
        this.id = id;
        this.connection_number = connection_number;
        this.id_trip = id_trip;
        this.id_plane = id_plane;
        this.id_airport = id_airport;
        this.id_trip_status = id_trip_status;
    }

    public Connections(String connection_number, int id_trip, Integer id_plane, String id_airport, int id_trip_status) {
        this.connection_number = connection_number;
        this.id_trip = id_trip;
        this.id_plane = id_plane;
        this.id_airport = id_airport;
        this.id_trip_status = id_trip_status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getConnection_number() {
        return connection_number;
    }

    public void setConnection_number(String connection_number) {
        this.connection_number = connection_number;
    }

    public int getId_trip() {
        return id_trip;
    }

    public void setId_trip(int id_trip) {
        this.id_trip = id_trip;
    }

    public Integer getId_plane() {
        return id_plane;
    }

    public void setId_plane(Integer id_plane) {
        this.id_plane = id_plane;
    }

    public String getId_airport() {
        return id_airport;
    }

    public void setId_airport(String id_airport) {
        this.id_airport = id_airport;
    }

    public int getId_trip_status() {
        return id_trip_status;
    }

    public void setId_trip_status(int id_trip_status) {
        this.id_trip_status = id_trip_status;
    }

    
}
