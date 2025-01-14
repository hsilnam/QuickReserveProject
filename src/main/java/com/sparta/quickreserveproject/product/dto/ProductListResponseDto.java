package com.sparta.quickreserveproject.product.dto;

import lombok.*;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductListResponseDto {
    private List<Product> productList;
    private Long nextCursor;

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Product {
        private Long productPk;
        private String productName;
        private String productDescription;
        private int productPrice;
        private int productStock;
        private Double productAvgRating;
        private Integer productReviewCount;
    }
}
