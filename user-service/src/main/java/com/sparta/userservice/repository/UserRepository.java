package com.sparta.userservice.repository;


import com.sparta.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUserEmail(String userEmail);
    boolean existsByUserId(String userId);
}