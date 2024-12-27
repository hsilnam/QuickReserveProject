package com.sparta.quickreserveproject.service;

import com.sparta.quickreserveproject.dto.CartItemAddRequestDto;
import com.sparta.quickreserveproject.dto.CartRequestDto;
import com.sparta.quickreserveproject.dto.CartResponseDto;

public interface CartService {
    void addItemToCart(CartItemAddRequestDto dto);

    CartResponseDto getCart(CartRequestDto dto);
}
