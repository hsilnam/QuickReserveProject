package com.sparta.quickreserveproject.user.repository;
import com.sparta.quickreserveproject.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUserEmail(String userEmail);
    boolean existsByUserId(String userId);
}