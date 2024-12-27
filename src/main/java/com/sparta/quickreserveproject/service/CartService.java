package com.sparta.quickreserveproject.service;

import com.sparta.quickreserveproject.dto.CartItemAddRequestDto;

public interface CartService {
    void addItemToCart(CartItemAddRequestDto dto);
}
