package com.bookstore.backend.api;

import com.bookstore.backend.dto.OrderDto;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderApi {

    OrderDto placeOrder(OrderDto orderDto);

    List<OrderDto> listOrders(LocalDateTime from, LocalDateTime to);

}
