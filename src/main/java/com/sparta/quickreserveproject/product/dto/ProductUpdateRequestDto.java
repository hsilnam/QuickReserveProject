package com.sparta.quickreserveproject.product.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductUpdateRequestDto {
        private Long productPk;
        private String productName;
        private String productDescription;
        private Integer productPrice;
        private Integer productStock;
        private Double productAvgRating;
        private Integer productReviewCount;
}
