package com.fabiansebastianj1.customer.domain.models;

public class CustomerDTO {
    private String id;
    private String name;
    private int age;
    private String documentType;

    public CustomerDTO(String id, String name, int age, String documentType) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.documentType = documentType;
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
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getDocumentType() {
        return documentType;
    }
    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    
}
