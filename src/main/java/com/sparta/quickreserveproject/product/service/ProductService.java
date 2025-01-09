package com.sparta.quickreserveproject.product.service;

import com.sparta.quickreserveproject.product.dto.ProductDto;
import com.sparta.quickreserveproject.product.dto.ProductListDto;
import com.sparta.quickreserveproject.product.entity.Product;

public interface ProductService {
    ProductListDto.Response getProductList(ProductListDto.Request request);
    ProductDto.Response getProduct(Long productPk);

    Product getProductEntity(Long productPk);
}
