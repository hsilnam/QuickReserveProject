package com.sparta.cartservice.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(value = AuditingEntityListener.class)
@Table(name = "cart_item")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItem{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartItemPk;

    @ManyToOne
    @JoinColumn(name = "cart_pk")
    private Cart cart;

    @Column(nullable = false)
    private Long productPk;

    @Column(nullable = false)
    private int cartItemQuantity;

    @Column(nullable = false)
    private int cartItemPrice;

    @CreatedDate
    @Column(name = "CREATED_AT", updatable = false)
    private LocalDateTime createdAt;
}
