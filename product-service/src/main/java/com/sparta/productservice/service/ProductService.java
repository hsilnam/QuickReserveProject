package com.sparta.quickreserveproject.product.service;

import com.sparta.quickreserveproject.product.dto.ProductListResponseDto;
import com.sparta.quickreserveproject.product.dto.ProductResponseDto;
import com.sparta.quickreserveproject.product.dto.ProductListRequestDto;
import com.sparta.quickreserveproject.product.entity.Product;

public interface ProductService {
    ProductListResponseDto getProductList(ProductListRequestDto request);
    ProductResponseDto getProduct(Long productPk);

    Product getProductEntity(Long productPk);
}
