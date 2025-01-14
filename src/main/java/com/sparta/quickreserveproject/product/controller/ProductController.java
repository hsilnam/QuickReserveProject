package com.sparta.quickreserveproject.product.controller;

import com.sparta.quickreserveproject.product.dto.ProductListResponseDto;
import com.sparta.quickreserveproject.product.dto.ProductResponseDto;
import com.sparta.quickreserveproject.product.dto.ProductListRequestDto;
import com.sparta.quickreserveproject.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<ProductListResponseDto> getProductList(
            @ModelAttribute ProductListRequestDto dto
    ) {
        ProductListResponseDto response = productService.getProductList(dto);
        return ResponseEntity.ok(response);
    }



    @GetMapping("/{productPk}")
    public ResponseEntity<ProductResponseDto> getProduct(@PathVariable Long productPk) {
        ProductResponseDto response = productService.getProduct(productPk);
        return ResponseEntity.ok(response);
    }
}
