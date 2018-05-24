package com.luizalabs.test.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration
@WebAppConfiguration
@WebMvcTest(GroupingProductsController.class)
public class GroupingProductsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    /**
     * Test of getGroupedProductsList method, of class GroupingProductsController.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void getGroupedProductsList() throws Exception {
        mockMvc.perform(get("/products/group_by=brand")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{'data':[]}"));
    }

    /**
     * Test of filterProducts method, of class GroupingProductsController.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void filterProducts() throws Exception {
        mockMvc.perform(get("/products/filter=brand:brand1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{'data':[]}"));
    }
}