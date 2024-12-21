package com.sparta.quickreserveproject.repository;

import com.sparta.quickreserveproject.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findAllByOrderByProductPkAsc(Pageable pageable);
    Page<Product> findByProductPkGreaterThanOrderByProductPkAsc(Long productPk, Pageable pageable);
}
