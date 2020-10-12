package com.bookstore.backend.controller;

import com.bookstore.backend.api.OrderApi;
import com.bookstore.backend.dto.OrderDto;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class OrderController implements OrderApi {

    @Override
    @PostMapping(path = "/api/v1/orders")
    public OrderDto placeOrder(@RequestBody OrderDto orderDto) {
        return null;
    }

    @Override
    @GetMapping(path = "/api/v1/orders/{from}/{to}")
    public List<OrderDto> listOrders(@PathVariable LocalDateTime from, @PathVariable LocalDateTime to) {
        return null;
    }
}
