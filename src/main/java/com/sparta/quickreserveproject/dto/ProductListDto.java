package com.sparta.quickreserveproject.dto;

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
            return cursor;
        }

        public int getSize() {
            return size;
        }

        public void setCursor(Long cursor) {
            this.cursor = cursor;
        }

        public void setSize(int size) {
            this.size = size;
        }
    }

    public static class Response {
        private List<Product> productList;
        private Long nextCursor;

        public Response() {
        }

        public Response(List<Product> productList, Long nextCursor) {
            this.productList = productList;
            this.nextCursor = nextCursor;
        }

        public List<Product> getProductList() {
            return productList;
        }

        public Long getNextCursor() {
            return nextCursor;
        }

        public static class Product {
            private Long productPk;
            private String productName;
            private String productDescription;
            private int productPrice;
            private int productStock;
            private Double productAvgRating;
            private Integer productReviewCount;

            public Product() {
            }

            public Product(Long productPk, String productName, String productDescription, int productPrice, int productStock,
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
