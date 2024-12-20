package com.sparta.quickreserveproject.service;

import com.sparta.quickreserveproject.dto.ProductListDto;

public interface ProductService {
    ProductListDto.Response getProductList(ProductListDto.Request request);
}
