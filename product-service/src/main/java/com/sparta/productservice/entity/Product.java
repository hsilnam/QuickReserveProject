package com.sparta.productservice.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(value = AuditingEntityListener.class)
@Table(name = "product")
@SQLDelete(sql = "UPDATE product SET deleted_at = now() WHERE product_pk = ?")
@Where(clause = "deleted_at is null")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product{

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

//    private Long categoryPk; // TODO: 나중에 추가

    @CreatedDate
    @Column(name = "CREATED_AT", updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;

    @Setter
    @Column(name = "DELETED_AT")
    private LocalDateTime deletedAt;
}
