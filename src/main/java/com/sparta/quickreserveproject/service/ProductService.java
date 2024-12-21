package com.sparta.quickreserveproject.service;

import com.sparta.quickreserveproject.dto.ProductDto;
import com.sparta.quickreserveproject.dto.ProductListDto;
import com.sparta.quickreserveproject.entity.Product;

public interface ProductService {
    ProductListDto.Response getProductList(ProductListDto.Request request);
    ProductDto.Response getProduct(Long productPk);

    Product getProductEntity(Long productPk);
}
