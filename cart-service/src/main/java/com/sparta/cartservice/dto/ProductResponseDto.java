package com.sparta.cartservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDto {

        private Long productPk;
        private String productName;
        private String productDescription;
        private int productPrice;
        private int productStock;
        private Double productAvgRating;
        private Integer productReviewCount;
}
