package com.sparta.quickreserveproject.order.repository;

import com.sparta.quickreserveproject.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}