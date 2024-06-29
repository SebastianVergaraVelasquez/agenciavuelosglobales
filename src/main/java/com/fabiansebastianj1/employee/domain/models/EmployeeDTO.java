package com.fabiansebastianj1.employee.domain.models;


public class EmployeeDTO {
    private String id;
    private String name;
    private int rolId;
    private String roleName;
    private String ingressDate;
    private int airlineId;
    private String airlineName;
    private String airportId;
    private String cityId;
    private String cityName;
    
    public EmployeeDTO(String id, String name, int rolId, String roleName, String ingressDate, int airlineId,
            String airlineName, String airportId, String cityId, String cityName) {
        this.id = id;
        this.name = name;
        this.rolId = rolId;
        this.roleName = roleName;
        this.ingressDate = ingressDate;
        this.airlineId = airlineId;
        this.airlineName = airlineName;
        this.airportId = airportId;
        this.cityId = cityId;
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

    public int getRolId() {
        return rolId;
    }

    public void setRolId(int rolId) {
        this.rolId = rolId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getIngressDate() {
        return ingressDate;
    }

    public void setIngressDate(String ingressDate) {
        this.ingressDate = ingressDate;
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

    public String getAirportId() {
        return airportId;
    }

    public void setAirportId(String airportId) {
        this.airportId = airportId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

   
}
