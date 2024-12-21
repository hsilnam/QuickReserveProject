package com.sparta.quickreserveproject.dto;

import java.util.List;

public class UserProductWishListDto {
    public static class Request {
        private Long cursor;
        private int size = 10;
        private Long userPk;

        public Request() {
        }

        public Request(Long cursor, int size, Long userPk) {
            this.cursor = cursor;
            this.size = size;
            this.userPk = userPk;
        }

        public Long getCursor() {
            return cursor;
        }

        public int getSize() {
            return size;
        }

        public Long getUserPk() {
            return userPk;
        }

        public void setCursor(Long cursor) {
            this.cursor = cursor;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public void setUserPk(Long userPk) {
            this.userPk = userPk;
        }
    }

    public static class Response {
        private List<UserProductWishListDto.Response.Wish> wishList;
        private Long nextCursor;

        public Response() {
        }

        public Response(List<UserProductWishListDto.Response.Wish> wishList, Long nextCursor) {
            this.wishList = wishList;
            this.nextCursor = nextCursor;
        }

        public List<UserProductWishListDto.Response.Wish> getWishList() {
            return wishList;
        }

        public static class Wish {
            private Long productPk;
            private String productName;
            private String productDescription;
            private int productPrice;
            private int productStock;
            private Double productAvgRating;
            private Integer productReviewCount;

            public Wish() {
            }

            public Wish(Long productPk, String productName, String productDescription, int productPrice, int productStock,
                        Double productAvgRating, Integer productReviewCount) {
                this.productPk = productPk;
                this.productName = productName;
                this.productDescription = productDescription;
                this.productPrice = productPrice;
                this.productStock = productStock;
                this.productAvgRating = productAvgRating;
                this.productReviewCount = productReviewCount;
            }

            public Long getProductPk() {
                return productPk;
            }

            public String getProductName() {
                return productName;
            }

            public String getProductDescription() {
                return productDescription;
            }

            public int getProductPrice() {
                return productPrice;
            }

            public int getProductStock() {
                return productStock;
            }

            public Double getProductAvgRating() {
                return productAvgRating;
            }

            public Integer getProductReviewCount() {
                return productReviewCount;
            }
        }
    }
}
