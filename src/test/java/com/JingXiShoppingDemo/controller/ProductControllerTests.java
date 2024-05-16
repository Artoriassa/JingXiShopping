package com.JingXiShoppingDemo.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        // 在每个测试之前设置测试数据
    }

    @Test
    public void testGetAllProducts() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/products"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3))
                .andDo(print());
    }

    @Test
    public void testGetProductBySku() throws Exception {
        String sku = "SKU001";
        mockMvc.perform(MockMvcRequestBuilders.get("/api/products/{sku}", sku))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.sku").value(sku))
                .andDo(print());
    }

    @Test
    public void testGetProductByInvalidSku() throws Exception {
        String sku = "INVALID_SKU";
        mockMvc.perform(MockMvcRequestBuilders.get("/api/products/{sku}", sku))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().string("Product not found."))
                .andDo(print());
    }

    @Test
    public void testGetProductsByCategory() throws Exception {
        String category = "Category A";
        mockMvc.perform(MockMvcRequestBuilders.get("/api/products/categories/{category}", category))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2))
                .andDo(print());
    }

    @Test
    public void testSearchProductsByKeyword() throws Exception {
        String keyword = "product";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/products/search")
                        .contentType(MediaType.TEXT_PLAIN)
                        .content(keyword))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3))
                .andDo(print());
    }
}