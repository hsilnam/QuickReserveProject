package com.sparta.quickreserveproject.wishlist.repository;

import com.sparta.quickreserveproject.wishlist.entity.Wishlist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
    Page<Wishlist> findAllByOrderByWishlistPkAsc(Pageable pageable);
    Page<Wishlist> findByWishlistPkGreaterThanOrderByWishlistPkAsc(Long wishlistPk, Pageable pageable);
}
