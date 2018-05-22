package com.luizalabs.test.models;

public class Product {

    private String id, ean, title, brand;
    private int stock;
    private double price;

    public Product() {
    }

    public Product(String id, String ean, String title, String brand, double price, int stock) {
        this.id = id;
        this.ean = ean;
        this.title = title;
        this.brand = brand;
        this.price = price;
        this.stock = stock;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}