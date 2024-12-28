package com.sparta.quickreserveproject.service;

import com.sparta.quickreserveproject.dto.OrderPlaceDirectRequestDto;
import com.sparta.quickreserveproject.dto.OrderPlaceCartRequestDto;

public interface OrderService {
    void placeOrderFromCart(OrderPlaceCartRequestDto dto);

    void placeDirectOrder(OrderPlaceDirectRequestDto dto);
}
