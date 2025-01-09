package com.sparta.quickreserveproject.cart.entity;


import com.sparta.quickreserveproject.product.entity.Product;
import com.sparta.quickreserveproject.global.entity.CEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cart_item")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItem extends CEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartItemPk;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_pk", nullable = false)
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_pk", nullable = false)
    private Product product;

    @Column(nullable = false)
    private int cartItemQuantity;

    @Column(nullable = false)
    private int cartItemPrice;
}
