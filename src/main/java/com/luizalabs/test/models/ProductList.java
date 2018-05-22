package com.luizalabs.test.models;

import java.util.List;

public class ProductList {

    private String description;
    private List<Product> items;

    public ProductList() {
    }

    public ProductList(String description, List<Product> items) {
        this.description = description;
        this.items = items;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Product> getItems() {
        return items;
    }

    public void setItems(List<Product> items) {
        this.items = items;
    }
}