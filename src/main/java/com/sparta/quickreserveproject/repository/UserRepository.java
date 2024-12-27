package com.sparta.quickreserveproject.repository;
import com.sparta.quickreserveproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUserEmail(String userEmail);
}