package com.luizalabs.test.models;

import java.util.ArrayList;
import java.util.List;

import static java.util.Comparator.comparing;

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

    // Helper methods
    public static List<ProductList> eanGrouping(List<Product> products) {

        List<ProductList> newList = new ArrayList<>();
        List<String> EANs = new ArrayList<>();
        EANs.clear();

        for (Product prod : products) {
            String EAN = prod.getEan();
            if (!EANs.contains(EAN)) {
                newList.add(craftProductList(products, EAN, "ean"));
            }
            EANs.add(EAN);
        }

        return newList;
    }

    public static List<ProductList> titleGrouping(List<Product> products) {

        List<ProductList> newList = new ArrayList<>();
        List<String> titles = new ArrayList<>();
        List<String> descrTitle = new ArrayList<>();

        titles.clear();

        for (Product prod : products) {

            String productTitle = prod.getTitle(),
                    arr[] = productTitle.split(" ", 4);

            if (arr.length > 2) {
                descrTitle.add(arr[0] + " " + arr[1] + " " + arr[2]);
            } else if (arr.length > 1) {
                descrTitle.add(arr[0] + " " + arr[1]);
            } else {
                descrTitle.add(productTitle);
            }

            String stripTitle = String.join("", descrTitle);

            if (!titles.contains(stripTitle)) {
                newList.add(craftProductList(products, stripTitle, "title"));
            }
            titles.add(stripTitle);
            descrTitle.clear();
        }

        return newList;
    }

    public static List<ProductList> brandGrouping(List<Product> products) {

        List<ProductList> newList = new ArrayList<>();
        List<String> brands = new ArrayList<>();
        brands.clear();

        for (Product prod : products) {

            String brand = prod.getBrand();

            if (!brands.contains(brand)) {
                newList.add(craftProductList(products, brand, "brand"));
            }
            brands.add(brand);
        }

        return newList;
    }

    private static ProductList craftProductList(List<Product> list, String descriptionTerm, String criteria) {

        List<Product> newList = new ArrayList<>();

        switch (criteria) {
            case "ean": //EAN
                newList.clear();
                for (Product prod : list) {
                    if (prod.getEan().equals(descriptionTerm)) {
                        newList.add(prod);
                    }
                }
                break;
            case "title": // Title similarity
                newList.clear();
                for (Product prod : list) {
                    if (prod.getTitle().contains(descriptionTerm)) {
                        newList.add(prod);
                    }
                }
                break;
            case "brand": // Brand
                newList.clear();
                for (Product prod : list) {
                    if (prod.getBrand().equals(descriptionTerm)) {
                        newList.add(prod);
                    }
                }
                break;
        }

        // Default sorting
        newList.sort(comparing(Product::getStock));
        newList.sort(comparing(Product::getPrice));
        newList.sort(comparing(Product::getStock).reversed());

        return new ProductList(descriptionTerm, newList);
    }
}