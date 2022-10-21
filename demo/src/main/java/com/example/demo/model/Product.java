package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Product {

    private String name;

    private String category;

    private String stock;

    private int price;

    private String brand;

    private String reason;

    public void setCategory(String category) {
        this.category = category;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategory() {
        return category;
    }

    public String getStock() {
        return stock;
    }

    public int getPrice() {
        return price;
    }

    public String getBrand() {
        return brand;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Product() {}

    public Product(@JsonProperty("name") String name,
                   @JsonProperty("category") String category,
                   @JsonProperty("stock") String stock,
                   @JsonProperty("price") int price,
                   @JsonProperty("brand") String brand,
                   String reason) {
        this.name = name;
        this.category = category;
        this.stock = stock;
        this.price = price;
        this.brand = brand;
        this.reason = null;
    }
}
