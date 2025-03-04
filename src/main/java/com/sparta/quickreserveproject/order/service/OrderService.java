package com.sparta.quickreserveproject.order.service;

import com.sparta.quickreserveproject.order.dto.OrderPlaceCartRequestDto;
import com.sparta.quickreserveproject.order.dto.OrderPlaceDirectRequestDto;

public interface OrderService {
    void placeOrderFromCart(OrderPlaceCartRequestDto dto);

    void placeDirectOrder(OrderPlaceDirectRequestDto dto);
}
