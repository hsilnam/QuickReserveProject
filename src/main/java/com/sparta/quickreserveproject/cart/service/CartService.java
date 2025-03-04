package com.sparta.quickreserveproject.cart.service;

import com.sparta.quickreserveproject.cart.dto.CartItemAddRequestDto;
import com.sparta.quickreserveproject.cart.dto.CartItemResponseDto;
import com.sparta.quickreserveproject.cart.dto.CartRequestDto;
import com.sparta.quickreserveproject.cart.dto.CartResponseDto;

public interface CartService {
    void addItemToCart(CartItemAddRequestDto dto);

    CartResponseDto getCart(CartRequestDto dto);

    CartItemResponseDto getCartItem(Long userPk, Long itemPk);

    void deleteCartItem(Long userPk, Long itemPk);
}
