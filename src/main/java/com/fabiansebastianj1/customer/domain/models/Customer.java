package com.fabiansebastianj1.customer.domain.models;

public class Customer {
    
    private String id;
    private String name;
    private int age;
    private int documentTypeId;
    
    public Customer(String id, String name, int age, int documentTypeId) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.documentTypeId = documentTypeId;
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

    public int getDocumentTypeId() {
        return documentTypeId;
    }

    public void setDocumentTypeId(int documentTypeId) {
        this.documentTypeId = documentTypeId;
    }

    
}
