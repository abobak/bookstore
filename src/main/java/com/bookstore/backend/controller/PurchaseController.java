package com.bookstore.backend.controller;

import com.bookstore.backend.api.PurchaseApi;
import com.bookstore.backend.dto.PurchaseDto;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class PurchaseController implements PurchaseApi {

    @Override
    @PostMapping(path = "/api/v1/orders")
    public PurchaseDto placeOrder(@RequestBody PurchaseDto purchaseDto) {
        return null;
    }

    @Override
    @GetMapping(path = "/api/v1/orders/{from}/{to}")
    public List<PurchaseDto> listOrders(@PathVariable LocalDateTime from, @PathVariable LocalDateTime to) {
        return null;
    }
}
