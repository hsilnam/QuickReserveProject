package com.sparta.quickreserveproject.repository;

import com.sparta.quickreserveproject.entity.CartItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    Page<CartItem> findAllByCart_User_UserPkOrderByCartItemPkAsc(Long userPk, Pageable pageable);

    Page<CartItem> findByCart_User_UserPkAndCartItemPkGreaterThanOrderByCartItemPkAsc(Long userPk, Long cursor, Pageable pageable);
}