package com.bookstore.backend.api;

import com.bookstore.backend.dto.PurchaseDto;

import java.util.List;

public interface PurchaseApi {

    PurchaseDto placeOrder(PurchaseDto purchaseDto);

    List<PurchaseDto> listOrders(String from, String to);

}
