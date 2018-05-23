package com.luizalabs.test.models;

import java.util.List;

public class ProductListData {

    private List<ProductList> data;

    public ProductListData(List<ProductList> data) {
        this.data = data;
    }

    public List<ProductList> getData() {
        return data;
    }

    public void setData(List<ProductList> data) {
        this.data = data;
    }
}