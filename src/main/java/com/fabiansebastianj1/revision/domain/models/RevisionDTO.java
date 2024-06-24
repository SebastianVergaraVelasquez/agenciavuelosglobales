package com.fabiansebastianj1.revision.domain.models;

public class RevisionDTO {
    private int id;
    private String revisionDate;
    private int planeId;
    private String employeeName;
    private String description;
    
    public RevisionDTO(int id, String revisionDate, int planeId, String employeeName, String description) {
        this.id = id;
        this.revisionDate = revisionDate;
        this.planeId = planeId;
        this.employeeName = employeeName;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRevisionDate() {
        return revisionDate;
    }

    public void setRevisionDate(String revisionDate) {
        this.revisionDate = revisionDate;
    }

    public int getPlaneId() {
        return planeId;
    }

    public void setPlaneId(int planeId) {
        this.planeId = planeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
}
