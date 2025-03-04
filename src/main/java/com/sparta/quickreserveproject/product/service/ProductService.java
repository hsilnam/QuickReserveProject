package com.sparta.quickreserveproject.product.service;

import com.sparta.quickreserveproject.product.dto.*;
import com.sparta.quickreserveproject.product.entity.Product;

public interface ProductService {
    ProductListResponseDto getProductList(ProductListRequestDto request);

    ProductResponseDto getProduct(Long productPk);

    Product getProductEntity(Long productPk);

    void updateProduct(ProductUpdateRequestDto dto);

    void updateProductStock(ProductStockUpdateRequestDto dto);
}
