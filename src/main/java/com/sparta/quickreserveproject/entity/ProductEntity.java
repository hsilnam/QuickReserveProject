package com.sparta.quickreserveproject.entity;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "product")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_pk")
    private Long productPk;

//    private Long sellerPk; // TODO: 나중에 추가

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "product_description")
    private String productDescription;

    @Column(name = "product_price", nullable = false)
    private int productPrice;

    @Column(name = "product_stock", nullable = false)
    private int productStock;

    @Column(name = "product_avg_rating")
    private Double productAvgRating;

    @Column(name = "product_review_count")
    private Integer productReviewCount;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

//    private Long categoryPk; // TODO: 나중에 추가

    public ProductEntity() {
    }

    public ProductEntity(Long productPk, String productName, String productDescription, int productPrice, int productStock,
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
