package com.sparta.quickreserveproject.cart.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItemResponseDto {
    private Long cartItemPk;
    private Long productPk;
    private int cartItemQuantity;
    private int cartItemPrice;
}
