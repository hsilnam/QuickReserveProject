package com.sparta.orderservice.service;

import com.sparta.orderservice.dto.OrderPlaceCartRequestDto;
import com.sparta.orderservice.dto.OrderPlaceDirectRequestDto;

public interface OrderService {
    void placeOrderFromCart(OrderPlaceCartRequestDto dto);

    void placeDirectOrder(OrderPlaceDirectRequestDto dto);
}
