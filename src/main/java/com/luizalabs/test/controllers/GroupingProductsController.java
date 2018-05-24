package com.luizalabs.test.controllers;

import com.google.gson.Gson;
import com.luizalabs.test.models.Product;
import com.luizalabs.test.models.ProductData;
import com.luizalabs.test.models.ProductList;

import java.util.*;

import com.luizalabs.test.models.ProductListData;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static java.util.Comparator.comparing;

@RestController
@RequestMapping(value = "/products", produces = "application/json")
public class GroupingProductsController {

    private List<Product> rawProductsList = new ArrayList<>();
    private List<Product> filteredProductsList = new ArrayList<>();
    private List<Product> sortedProductsList = new ArrayList<>();
    private List<ProductList> groupedProductsList = new ArrayList<>();

    // POST
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED, reason = "The products list was updated successfully.")
    public void createProductsList(@RequestBody String inputList) {

        JSONArray parsedRawProductsList = new JSONArray(inputList);
        rawProductsList.clear();

        // Convert JSON entry into Java Objects
        int i = 0;
        while (i < parsedRawProductsList.length()) {
            JSONObject parsedProduct = parsedRawProductsList.getJSONObject(i);
            Gson gson = new Gson();
            Product singleProduct = gson.fromJson(parsedProduct.toString(), Product.class);
            rawProductsList.add(singleProduct);
            i++;
        }
    }

    // GET default grouped by EAN (according to priority order)
    @GetMapping
    public ProductListData getAll() {

        groupedProductsList.clear();
        groupedProductsList.addAll(ProductList.eanGrouping(rawProductsList));

        return new ProductListData(groupedProductsList);
    }

    // GET group_by
    @GetMapping("/group_by={term}")
    public ProductListData getGroupedProductsList(@PathVariable String term) {

        if (term.equals("ean") || term.equals("title") || term.equals("brand")) {

            switch (term) {
                case "ean":
                    groupedProductsList.clear();
                    groupedProductsList.addAll(ProductList.eanGrouping(rawProductsList));
                    break;
                case "title":
                    groupedProductsList.clear();
                    groupedProductsList.addAll(ProductList.titleGrouping(rawProductsList));
                    break;
                case "brand":
                    groupedProductsList.clear();
                    groupedProductsList.addAll(ProductList.brandGrouping(rawProductsList));
                    break;
            }

            return new ProductListData(groupedProductsList);

        } else {

            throw new IllegalArgumentException();

        }
    }

    // GET filter
    @GetMapping("/filter={term}:{value}")
    public ProductData filterProducts(@PathVariable String term, @PathVariable String value) {

        if (term.equals("id") || term.equals("ean") || term.equals("title") || term.equals("brand")) {

            switch (term) {
                case "id":
                    filteredProductsList.clear();
                    for (Product product : rawProductsList) {
                        if (product.getId().equals(value)) {
                            filteredProductsList.add(product);
                        }
                    }
                    break;
                case "ean":
                    filteredProductsList.clear();
                    for (Product product : rawProductsList) {
                        if (product.getEan().equals(value)) {
                            filteredProductsList.add(product);
                        }
                    }
                    break;
                case "title":
                    filteredProductsList.clear();
                    for (Product product : rawProductsList) {
                        if (product.getTitle().contains(value)) {
                            filteredProductsList.add(product);
                        }
                    }
                    break;
                case "brand":
                    filteredProductsList.clear();
                    for (Product product : rawProductsList) {
                        if (product.getBrand().contains(value)) {
                            filteredProductsList.add(product);
                        }
                    }
                    break;
            }

            // Default sorting
            filteredProductsList.sort(comparing(Product::getStock));
            filteredProductsList.sort(comparing(Product::getPrice));
            filteredProductsList.sort(comparing(Product::getStock).reversed());

            return new ProductData(filteredProductsList);

        } else {

            throw new IllegalArgumentException();

        }
    }

    // GET order_by
    @GetMapping("/order_by={criteria}:{direction}")
    public ProductData orderProducts(@PathVariable String criteria, @PathVariable String direction) {

        sortedProductsList.clear();
        sortedProductsList.addAll(rawProductsList);

        if ((criteria.equals("stock") || criteria.equals("price")) && (direction.equals("asc") || direction.equals("desc"))) {

            switch (criteria) {
                case "stock":

                    if (direction.equals("asc"))
                        sortedProductsList.sort(comparing(Product::getStock));

                    else if (direction.equals("desc"))
                        sortedProductsList.sort(comparing(Product::getStock).reversed());

                    break;
                case "price":

                    if (direction.equals("asc"))
                        sortedProductsList.sort(comparing(Product::getPrice));

                    else if (direction.equals("desc"))
                        sortedProductsList.sort(comparing(Product::getPrice).reversed());

                    break;
            }

            return new ProductData(sortedProductsList);

        } else {

            throw new IllegalArgumentException();

        }
    }

    // Exception handler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Requested products not found in the available products list.")
    @ExceptionHandler(IllegalArgumentException.class)
    public void badExceptionHandler() {
        // Response sent.
    }
}