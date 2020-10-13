package com.bookstore.backend.api;

import com.bookstore.backend.dto.NewPurchaseDto;
import com.bookstore.backend.dto.PurchaseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

@Api(value = "purchase")
public interface PurchaseApi {

    @ApiOperation(value = "Place an order")
    PurchaseDto placeOrder(NewPurchaseDto newPurchaseDto);

    @ApiOperation(value = "List all orders create between provided dates in yyyy-MM-dd format")
    List<PurchaseDto> listOrders(String from, String to);

}
