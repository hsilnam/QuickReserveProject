package com.sparta.quickreserveproject.dto;

import com.sparta.quickreserveproject.entity.ProductEntity;

import java.util.List;

public class ProductListDto {
    public static class Request {
        private Long cursor;
        private int size = 10;

        public Request() {

        }

        public Request(Long cursor, int size) {
            this.cursor = cursor;
            this.size = size;
        }

        public Long getCursor() {
            return this.cursor;
        }

        public int getSize() {
            return this.size;
        }
    }

    public static class Response {
        private List<ProductDto> productList;
        private Long nextCursor;

        public Response() {
        }

        public Response(List<ProductDto> productList, Long nextCursor) {
            this.productList = productList;
            this.nextCursor = nextCursor;
        }

        public static class ProductDto {
            private Long productPk;
            private String productName;
            private String productDescription;
            private int productPrice;
            private int productStock;
            private Double productAvgRating;
            private Integer productReviewCount;

            public ProductDto(Long productPk, String productName, String productDescription, int productPrice, int productStock,
                              Double productAvgRating, Integer productReviewCount) {
                this.productPk = productPk;
                this.productName = productName;
                this.productDescription = productDescription;
                this.productPrice = productPrice;
                this.productStock = productStock;
                this.productAvgRating = productAvgRating;
                this.productReviewCount = productReviewCount;
            }


        }
    }
}
