package com.bookstore.backend.api;

import com.bookstore.backend.dto.PurchaseDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface PurchaseApi {

    PurchaseDto placeOrder(PurchaseDto purchaseDto);

    List<PurchaseDto> listOrders(LocalDate from, LocalDate to);

}
