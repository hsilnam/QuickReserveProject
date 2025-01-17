package com.sparta.cartservice.client;

import com.sparta.cartservice.dto.ProductResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service")
public interface ProductClient {

    @GetMapping("/products/{productPk}")
    ProductResponseDto getProduct(@PathVariable("productPk") Long productPk);
}