package com.sparta.quickreserveproject.dto;

public class UserProductWishAddDto {
    public static class Request {
        private Long userPk;
        private Long productPk;

        public Request() {
        }

        public Request(Long userPk, Long productPk) {
            this.userPk = userPk;
            this.productPk = productPk;
        }

        public void setUserPk(Long userPk) {
            this.userPk = userPk;
        }

        public void setProductPk(Long productPk) {
            this.productPk = productPk;
        }

        public Long getUserPk() {
            return userPk;
        }

        public Long getProductPk() {
            return productPk;
        }
    }
}
