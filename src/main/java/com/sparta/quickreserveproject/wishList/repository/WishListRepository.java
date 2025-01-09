package com.sparta.quickreserveproject.wishList.repository;

import com.sparta.quickreserveproject.wishList.entity.WishList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishListRepository extends JpaRepository<WishList, Long> {
    Page<WishList> findAllByOrderByUserProductWishPkAsc(Pageable pageable);
    Page<WishList> findByUserProductWishPkGreaterThanOrderByUserProductWishPkAsc(Long userProductWishPk, Pageable pageable);
}
