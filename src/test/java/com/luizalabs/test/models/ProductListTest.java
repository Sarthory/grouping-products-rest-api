package com.luizalabs.test.models;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.*;

public class ProductListTest {

    /**
     * Test of eanGrouping method, of class ProductList.
     */
    @Test
    public void testEanGrouping() {
        System.out.println("eanGrouping ++++++++++++++++++");

        List<Product> allProducts = new ArrayList<>();

        List<Product> sortedProducts1 = new ArrayList<>();
        List<Product> sortedProducts2 = new ArrayList<>();
        List<Product> sortedProducts3 = new ArrayList<>();

        List<ProductList> expResult = new ArrayList<>();

        Product prod1 = new Product("123", "111111", "teste1", "marca1", 100.50, 10);
        Product prod2 = new Product("456", "111111", "teste2", "marca1", 200.50, 5);
        Product prod3 = new Product("789", "222222", "teste1", "marca2", 300.50, 12);
        Product prod4 = new Product("101", "333333", "teste3", "marca3", 400.50, 20);

        ProductList prodList1 = new ProductList("111111", sortedProducts1);
        ProductList prodList2 = new ProductList("222222", sortedProducts2);
        ProductList prodList3 = new ProductList("333333", sortedProducts3);

        allProducts.add(prod1);
        allProducts.add(prod2);
        allProducts.add(prod3);
        allProducts.add(prod4);

        sortedProducts1.add(prod1);
        sortedProducts1.add(prod2);
        sortedProducts2.add(prod3);
        sortedProducts3.add(prod4);

        expResult.add(prodList1);
        expResult.add(prodList2);
        expResult.add(prodList3);

        List<ProductList> result = ProductList.eanGrouping(allProducts);

        assertEquals(expResult.get(0).getDescription(), result.get(0).getDescription());
        assertEquals(expResult.get(1).getDescription(), result.get(1).getDescription());
        assertEquals(expResult.get(2).getDescription(), "333333");
        assertEquals("333333", result.get(2).getDescription());
    }

    /**
     * Test of titleGrouping method, of class ProductList.
     */
    @Test
    public void testTitleGrouping() {
        System.out.println("titleGrouping ++++++++++++++++++");

        List<Product> allProducts = new ArrayList<>();

        List<Product> sortedProducts1 = new ArrayList<>();
        List<Product> sortedProducts2 = new ArrayList<>();
        List<Product> sortedProducts3 = new ArrayList<>();

        List<ProductList> expResult = new ArrayList<>();

        Product prod1 = new Product("123", "111111", "teste1 teste1 teste1", "marca1", 100.50, 10);
        Product prod2 = new Product("456", "111111", "teste2", "marca1", 200.50, 5);
        Product prod3 = new Product("789", "222222", "teste1 teste1 teste1", "marca2", 300.50, 12);
        Product prod4 = new Product("101", "333333", "teste3 teste3", "marca3", 400.50, 20);

        ProductList prodList1 = new ProductList("teste1 teste1 teste1", sortedProducts1);

        ProductList prodList2 = new ProductList("teste2", sortedProducts2);
        ProductList prodList3 = new ProductList("teste3 teste3", sortedProducts3);

        allProducts.add(prod1);
        allProducts.add(prod2);
        allProducts.add(prod3);
        allProducts.add(prod4);

        sortedProducts1.add(prod1);
        sortedProducts1.add(prod3);
        sortedProducts2.add(prod2);
        sortedProducts3.add(prod4);

        expResult.add(prodList1);
        expResult.add(prodList2);
        expResult.add(prodList3);

        List<ProductList> result = ProductList.titleGrouping(allProducts);

        assertEquals(expResult.get(0).getDescription(), result.get(0).getDescription());
        assertEquals(expResult.get(1).getDescription(), result.get(1).getDescription());
        assertEquals(expResult.get(2).getDescription(), "teste3 teste3");
        assertEquals("teste3 teste3", result.get(2).getDescription());
    }

    /**
     * Test of brandGrouping method, of class ProductList.
     */
    @Test
    public void testBrandGrouping() {
        System.out.println("brandGrouping ++++++++++++++++++");

        List<Product> allProducts = new ArrayList<>();

        List<Product> sortedProducts1 = new ArrayList<>();
        List<Product> sortedProducts2 = new ArrayList<>();
        List<Product> sortedProducts3 = new ArrayList<>();

        List<ProductList> expResult = new ArrayList<>();

        Product prod1 = new Product("123", "111111", "teste1", "marca1", 100.50, 10);
        Product prod2 = new Product("456", "111111", "teste2", "marca1", 200.50, 5);
        Product prod3 = new Product("789", "222222", "teste1", "marca2", 300.50, 12);
        Product prod4 = new Product("101", "333333", "teste3", "marca3 marca3", 400.50, 20);

        ProductList prodList1 = new ProductList("marca1", sortedProducts1);
        ProductList prodList2 = new ProductList("marca2", sortedProducts2);
        ProductList prodList3 = new ProductList("marca3 marca3", sortedProducts3);

        allProducts.add(prod1);
        allProducts.add(prod2);
        allProducts.add(prod3);
        allProducts.add(prod4);

        sortedProducts1.add(prod1);
        sortedProducts1.add(prod2);
        sortedProducts2.add(prod3);
        sortedProducts3.add(prod4);

        expResult.add(prodList1);
        expResult.add(prodList2);
        expResult.add(prodList3);

        List<ProductList> result = ProductList.brandGrouping(allProducts);

        assertEquals(expResult.get(0).getDescription(), result.get(0).getDescription());
        assertEquals(expResult.get(1).getDescription(), result.get(1).getDescription());
        assertEquals(expResult.get(2).getDescription(), "marca3 marca3");
        assertEquals("marca3 marca3", result.get(2).getDescription());

    }

}
