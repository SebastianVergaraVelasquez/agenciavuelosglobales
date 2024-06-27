package com.fabiansebastianj1.pay_type.domain.models;

public class PayType {
    private int id;
    private String name;

    public PayType() {
    }

    public PayType(int id, String name) {
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
