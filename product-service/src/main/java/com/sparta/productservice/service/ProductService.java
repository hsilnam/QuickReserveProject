package com.sparta.productservice.service;

import com.sparta.productservice.dto.ProductListRequestDto;
import com.sparta.productservice.dto.ProductListResponseDto;
import com.sparta.productservice.dto.ProductResponseDto;
import com.sparta.productservice.entity.Product;

public interface ProductService {
    ProductListResponseDto getProductList(ProductListRequestDto request);
    ProductResponseDto getProduct(Long productPk);

    Product getProductEntity(Long productPk);
}
