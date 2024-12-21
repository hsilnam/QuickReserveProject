package com.sparta.quickreserveproject.repository;

import com.sparta.quickreserveproject.entity.Product;
import com.sparta.quickreserveproject.entity.UserProductWish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserProductWishRepository extends JpaRepository<UserProductWish, Long> {
    Page<UserProductWish> findAllByOrderByUserProductWishPkAsc(Pageable pageable);
    Page<UserProductWish> findByUserProductWishPkGreaterThanOrderByUserProductWishPkAsc(Long userProductWishPk, Pageable pageable);
}
