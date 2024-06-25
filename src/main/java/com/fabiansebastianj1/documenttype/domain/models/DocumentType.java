package com.fabiansebastianj1.documenttype.domain.models;

public class DocumentType {
    private int id;
    private String name;

    public DocumentType() {
    }

    public DocumentType(String name) {
        this.name = name;
    }

    public DocumentType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
