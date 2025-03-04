package com.sparta.quickreserveproject.wishlist.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "wishlist")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Wishlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wishlist_pk")
    private Long wishlistPk;

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
