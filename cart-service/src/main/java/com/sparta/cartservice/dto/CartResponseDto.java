package com.sparta.cartservice.dto;

import lombok.*;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CartResponseDto {
    private List<CartResponseDto.CartItem> productList;
    private Long nextCursor;

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CartItem {
        private Long cartItemPk;
        private Long productPk;
        private String productName;
        private int quantity;
        private double price;
    }
}
