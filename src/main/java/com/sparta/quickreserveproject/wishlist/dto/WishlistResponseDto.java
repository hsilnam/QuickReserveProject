package com.sparta.quickreserveproject.wishlist.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WishlistResponseDto {
    private List<WishProduct> wishList;
    private Long nextCursor;

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class WishProduct {
        private Long productPk;
        private String productName;
        private String productDescription;
        private int productPrice;
        private int productStock;
        private Double productAvgRating;
        private Integer productReviewCount;
    }
}
