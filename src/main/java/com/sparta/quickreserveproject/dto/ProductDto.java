package com.sparta.quickreserveproject.dto;

public class ProductDto {
    public static class Response {

        private Long productPk;
        private String productName;
        private String productDescription;
        private int productPrice;
        private int productStock;
        private Double productAvgRating;
        private Integer productReviewCount;

        public Response() {
        }

        public Response(Long productPk, String productName, String productDescription, int productPrice, int productStock,
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
