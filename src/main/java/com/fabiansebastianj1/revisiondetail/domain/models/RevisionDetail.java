package com.fabiansebastianj1.revisiondetail.domain.models;

public class RevisionDetail {

    private String id;
    private String description;
    private String id_employee;

    public RevisionDetail() {
    }

    public RevisionDetail(String id, String description, String id_employee) {
        this.id = id;
        this.description = description;
        this.id_employee = id_employee;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId_employee() {
        return id_employee;
    }

    public void setId_employee(String id_employee) {
        this.id_employee = id_employee;
    }
}
