package com.sparta.quickreserveproject.repository;

import com.sparta.quickreserveproject.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    Page<ProductEntity> findAllByOrderByProductPkAsc(Pageable pageable);
    Page<ProductEntity> findByProductPkGreaterThanOrderByProductPkAsc(Long productPk, Pageable pageable);
}
