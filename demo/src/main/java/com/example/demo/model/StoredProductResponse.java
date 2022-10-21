package com.example.demo.model;

public class StoredProductResponse {


    private String name;

    private String brand;

    private String reason = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public StoredProductResponse(){}

    public StoredProductResponse(String name, String brand, String reason) {
        this.name = name;
        this.brand = brand;
        this.reason = reason;
    }


}
