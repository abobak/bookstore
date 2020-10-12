package com.bookstore.backend.controller;

import com.bookstore.backend.mapper.ProductMapper;
import com.bookstore.backend.repository.ProductRepository;
import com.bookstore.backend.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ContextConfiguration(classes = {ProductController.class})
@WebMvcTest
public class ProductControllerTest {

    @MockBean
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private ProductMapper productMapper;

    @Autowired
    private MockMvc mvc;

    @Test
    void shouldReturn200ForNumericProductId() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/api/v1/products/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturn400ForNonNumericProductId() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/api/v1/products/R")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturn400IfUserTriesToAlterIdOfProduct() throws Exception {
        String jsonString = "{ \"id\": \"2\", \"title\": \"Anything\", \"price\": \"10.2\"}";
        mvc.perform(MockMvcRequestBuilders
                .put("/api/v1/products/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturn200IfUserTriesToAlterProductWithValidData() throws Exception {
        String jsonString = "{ \"id\": \"2\", \"title\": \"Anything\", \"price\": \"10.2\"}";
        mvc.perform(MockMvcRequestBuilders
                .put("/api/v1/products/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
