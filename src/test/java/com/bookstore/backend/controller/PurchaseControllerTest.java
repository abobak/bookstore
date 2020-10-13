package com.bookstore.backend.controller;

import com.bookstore.backend.mapper.PurchaseMapper;
import com.bookstore.backend.repository.PurchaseRepository;
import com.bookstore.backend.service.PurchaseService;
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
@ContextConfiguration(classes = {PurchaseController.class})
@WebMvcTest
public class PurchaseControllerTest {

    @MockBean
    private PurchaseService purchaseService;

    @MockBean
    private PurchaseRepository purchaseRepository;

    @MockBean
    private PurchaseMapper purchaseMapper;

    @Autowired
    private MockMvc mvc;

    @Test
    void shouldReturn200ForValidDateRange() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/api/v1/purchases/2020-01-01/2020-01-02")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturn404ForUrlWithIncompleteDateRange() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/api/v1/purchases/2020-01-01")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

}
