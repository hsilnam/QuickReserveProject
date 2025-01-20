package com.sparta.productservice.service;

import com.sparta.productservice.dto.*;
import com.sparta.productservice.entity.Product;

public interface ProductService {
    ProductListResponseDto getProductList(ProductListRequestDto request);

    ProductResponseDto getProduct(Long productPk);

    Product getProductEntity(Long productPk);

    void updateProduct(ProductUpdateRequestDto dto);

    void updateProductStock(ProductStockUpdateRequestDto dto);
}
