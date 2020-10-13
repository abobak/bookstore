package com.bookstore.backend.api;

import com.bookstore.backend.dto.NewPurchaseDto;
import com.bookstore.backend.dto.PurchaseDto;

import java.util.List;

public interface PurchaseApi {

    PurchaseDto placeOrder(NewPurchaseDto newPurchaseDto);

    List<PurchaseDto> listOrders(String from, String to);

}
