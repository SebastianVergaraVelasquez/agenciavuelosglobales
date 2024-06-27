package com.fabiansebastianj1.payment.domain.models;

public class Payment {
    private int id;
    private String pay_date;
    private int id_pay_type;
    private Double total_pay;
    private String id_customer;
    
    

    public Payment(String pay_date, int id_pay_type, Double total_pay, String id_customer) {
        this.pay_date = pay_date;
        this.id_pay_type = id_pay_type;
        this.total_pay = total_pay;
        this.id_customer = id_customer;
    }

    public Payment(int id, String pay_date, int id_pay_type, Double total_pay, String id_customer) {
        this.id = id;
        this.pay_date = pay_date;
        this.id_pay_type = id_pay_type;
        this.total_pay = total_pay;
        this.id_customer = id_customer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPay_date() {
        return pay_date;
    }

    public void setPay_date(String pay_date) {
        this.pay_date = pay_date;
    }

    public int getId_pay_type() {
        return id_pay_type;
    }

    public void setId_pay_type(int id_pay_type) {
        this.id_pay_type = id_pay_type;
    }

    public Double getTotal_pay() {
        return total_pay;
    }

    public void setTotal_pay(Double total_pay) {
        this.total_pay = total_pay;
    }

    public String getId_customer() {
        return id_customer;
    }

    public void setId_customer(String id_customer) {
        this.id_customer = id_customer;
    }

    
    
}
