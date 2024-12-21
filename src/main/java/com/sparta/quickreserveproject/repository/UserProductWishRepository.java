package com.sparta.quickreserveproject.repository;

import com.sparta.quickreserveproject.entity.UserProductWish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserProductWishRepository extends JpaRepository<UserProductWish, Long> {
}
