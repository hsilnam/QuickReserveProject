package com.sparta.cartservice.repository;


import com.sparta.cartservice.entity.CartItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    Page<CartItem> findByCart_CartPkOrderByCartItemPkAsc(Long cartPk, Pageable pageable);

    Page<CartItem> findByCart_CartPkAndCartItemPkGreaterThanOrderByCartItemPkAsc(Long cartPk, Long cursor, Pageable pageable);
}