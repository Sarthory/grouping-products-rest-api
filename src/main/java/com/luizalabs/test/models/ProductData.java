package com.luizalabs.test.models;

import java.util.List;

public class ProductData {

    private List<Product> data;

    public ProductData(List<Product> data) {
        this.data = data;
    }

    public List<Product> getData() {
        return data;
    }

    public void setData(List<Product> data) {
        this.data = data;
    }
}