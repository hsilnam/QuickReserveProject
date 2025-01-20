package com.sparta.orderservice.client.dto;

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
