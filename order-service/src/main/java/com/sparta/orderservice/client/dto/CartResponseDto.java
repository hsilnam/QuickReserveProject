package com.sparta.orderservice.client.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CartResponseDto {
    private List<CartItem> productList;
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
