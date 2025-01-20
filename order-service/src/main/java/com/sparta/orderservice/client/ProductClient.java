package com.sparta.orderservice.client;

import com.sparta.orderservice.client.dto.ProductResponseDto;
import com.sparta.orderservice.client.dto.ProductStockUpdateRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "product-service")
public interface ProductClient {

    @GetMapping("/products/{productPk}")
    ProductResponseDto getProduct(@PathVariable("productPk") Long productPk);

    @PutMapping("/{productPk}")
    ResponseEntity<String> updateStockProduct(
            @PathVariable Long productPk,
            @RequestBody ProductStockUpdateRequestDto updateRequest);
}