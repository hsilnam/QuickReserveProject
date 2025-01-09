package com.sparta.quickreserveproject.wishList.entity;


import com.sparta.quickreserveproject.product.entity.Product;
import com.sparta.quickreserveproject.global.entity.CEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "user_product_wish")
public class WishList extends CEntity {

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

    public WishList() {
    }

    public WishList(Long user, Product product) {
        this.user = user;
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }
}
