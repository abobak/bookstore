package com.bookstore.backend.controller;

import com.bookstore.backend.api.PurchaseApi;
import com.bookstore.backend.dto.PurchaseDto;
import com.bookstore.backend.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PurchaseController implements PurchaseApi {

    private final PurchaseService purchaseService;

    @Override
    @PostMapping(path = "/api/v1/purchases")
    public PurchaseDto placeOrder(@RequestBody PurchaseDto purchaseDto) {
        return purchaseService.submitPurchase(purchaseDto);
    }

    @Override
    @GetMapping(path = "/api/v1/purchases/{from}/{to}")
    public List<PurchaseDto> listOrders(@PathVariable LocalDate from, @PathVariable LocalDate to) {
        LocalDateTime fromDate = LocalDateTime.of(from, LocalTime.MIN);
        LocalDateTime toDate = LocalDateTime.of(to, LocalTime.MAX);
        return purchaseService.viewPurchases(fromDate, toDate);
    }
}
