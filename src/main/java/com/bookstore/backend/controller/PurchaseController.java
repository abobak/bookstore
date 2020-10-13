package com.bookstore.backend.controller;

import com.bookstore.backend.api.PurchaseApi;
import com.bookstore.backend.dto.NewPurchaseDto;
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
    public PurchaseDto placeOrder(@RequestBody NewPurchaseDto newPurchaseDto) {
        return purchaseService.submitPurchase(newPurchaseDto);
    }

    @Override
    @GetMapping(path = "/api/v1/purchases/{from}/{to}")
    public List<PurchaseDto> listOrders(@PathVariable String from, @PathVariable String to) {
        LocalDateTime fromDate = LocalDateTime.of(LocalDate.parse(from), LocalTime.MIN);
        LocalDateTime toDate = LocalDateTime.of(LocalDate.parse(to), LocalTime.MAX);
        return purchaseService.viewPurchases(fromDate, toDate);
    }
}
