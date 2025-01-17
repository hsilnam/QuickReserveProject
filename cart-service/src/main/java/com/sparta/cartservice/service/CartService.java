package com.sparta.cartservice.service;

import com.sparta.cartservice.dto.CartItemAddRequestDto;
import com.sparta.cartservice.dto.CartRequestDto;
import com.sparta.cartservice.dto.CartResponseDto;

public interface CartService {
    void addItemToCart(CartItemAddRequestDto dto);

    CartResponseDto getCart(CartRequestDto dto);
}
