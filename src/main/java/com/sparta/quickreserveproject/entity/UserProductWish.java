package com.sparta.quickreserveproject.entity;


import com.sparta.quickreserveproject.global.entity.CEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "user_product_wish")
public class UserProductWish extends CEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_product_wish_pk")
    private Long userProductWishPk;

    /* TODO: 나중에 교체
    @ManyToOne
    @JoinColumn(name = "user_pk", nullable = false)
    private User userPk; //
     */
    @Column
    private Long user;

    @ManyToOne
    @JoinColumn(name = "product_pk", nullable = false)
    private Product product;

    public UserProductWish() {
    }

    public UserProductWish(Long user, Product product) {
        this.user = user;
        this.product = product;
    }
}
