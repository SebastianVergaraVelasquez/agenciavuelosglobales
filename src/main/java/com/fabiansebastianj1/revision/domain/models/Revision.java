package com.fabiansebastianj1.revision.domain.models;

import java.sql.Date;

public class Revision {
    private int id;
    private Date revisionDate;
    private int planeId;
    
    public Revision(int id, Date revisionDate, int planeId) {
        this.id = id;
        this.revisionDate = revisionDate;
        this.planeId = planeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getRevisionDate() {
        return revisionDate;
    }

    public void setRevisionDate(Date revisionDate) {
        this.revisionDate = revisionDate;
    }

    public int getPlaneId() {
        return planeId;
    }

    public void setPlaneId(int planeId) {
        this.planeId = planeId;
    }

    
}
