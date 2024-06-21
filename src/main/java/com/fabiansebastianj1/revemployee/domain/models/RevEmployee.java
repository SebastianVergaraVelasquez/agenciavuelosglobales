package com.fabiansebastianj1.revemployee.domain.models;

public class RevEmployee {
    private String id_employee;
    private int id_revision;

    public RevEmployee() {
    }

    public RevEmployee(String id_employee, int id_revision) {
        this.id_employee = id_employee;
        this.id_revision = id_revision;
    }

    public String getId_employee() {
        return id_employee;
    }

    public void setId_employee(String id_employee) {
        this.id_employee = id_employee;
    }

    public int getId_revision() {
        return id_revision;
    }

    public void setId_revision(int id_revision) {
        this.id_revision = id_revision;
    }
}
