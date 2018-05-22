package com.luizalabs.test.controllers;

import com.google.gson.Gson;
import com.luizalabs.test.models.Product;
import com.luizalabs.test.models.ProductList;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GroupingProductsController {

    private List<ProductList> groupedProducts = new ArrayList<>();
    private List<Product> rawProductsList = new ArrayList<>();

    // POST
    @PostMapping("/products")
    @ResponseStatus(value = HttpStatus.CREATED, reason = "The products list was updated successfully.")
    public void createProductsList(@RequestBody String inputList) {

        JSONArray parsedRawProductsList = new JSONArray(inputList);

        for (int i = 0; i < parsedRawProductsList.length(); i++) {
            JSONObject parsedProduct = parsedRawProductsList.getJSONObject(i);
            Gson gson = new Gson();
            Product singleProduct = gson.fromJson(parsedProduct.toString(), Product.class);
            rawProductsList.add(singleProduct);
        }
    }

    // GET
    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return rawProductsList;
    }

    // GETone
    @GetMapping("/products/{id}")
    public Product getOneProduct(@PathVariable("id") String productId) {

        for (Product product : rawProductsList) {
            if (product.getId().equals(productId)) {
                return product;
            }
        }

        throw new IllegalArgumentException();

    }

    // Exception Handler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "The informed ID does not exist.")
    @ExceptionHandler(IllegalArgumentException.class)
    public void badExceptionHandler() {
        // Response sent.
    }
}