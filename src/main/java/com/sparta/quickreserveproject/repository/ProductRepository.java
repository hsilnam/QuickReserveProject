package com.sparta.quickreserveproject.repository;

import com.sparta.quickreserveproject.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    @Query(value = "SELECT p FROM Product p ORDER BY p.productPk ASC LIMIT :size")
    List<ProductEntity> findTopByOrderByIdAsc(@Param("size") int size);


    @Query(value = "SELECT p FROM Product p WHERE p.productPk > :cursor ORDER BY p.productPk ASC LIMIT :size")
    List<ProductEntity> findByIdGreaterThanOrderByIdAsc(@Param("cursor") Long cursor, @Param("size") int size);
}
