package com.sparta.quickreserveproject.order.service;

import com.sparta.quickreserveproject.order.dto.OrderPlaceDirectRequestDto;
import com.sparta.quickreserveproject.order.dto.OrderPlaceCartRequestDto;

public interface OrderService {
    void placeOrderFromCart(OrderPlaceCartRequestDto dto);

    void placeDirectOrder(OrderPlaceDirectRequestDto dto);
}
