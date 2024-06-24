package com.fabiansebastianj1.airport.domain.models;

public class AirportDTO {
    private String id;
    private String name;
    private String cityName;

    public AirportDTO(String id, String name, String cityName) {
        this.id = id;
        this.name = name;
        this.cityName = cityName;
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
    public String getCityName() {
        return cityName;
    }
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    
}
