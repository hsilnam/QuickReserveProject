package com.sparta.quickreserveproject.wishList.entity;


import com.sparta.quickreserveproject.product.entity.Product;
import com.sparta.quickreserveproject.global.entity.CEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_product_wish")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    private Long userPk;

    @Column( nullable = false)
    private Long productPk;
}
