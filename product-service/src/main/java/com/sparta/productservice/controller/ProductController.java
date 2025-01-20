package com.sparta.productservice.controller;

import com.sparta.productservice.dto.*;
import com.sparta.productservice.service.ProductService;
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

    @PutMapping("/{productPk}")
    public ResponseEntity<String> updateProduct(
            @PathVariable Long productPk,
            @RequestBody ProductUpdateRequestDto dto
    ) {
        dto.setProductPk(productPk);
        productService.updateProduct(dto);
        return ResponseEntity.ok("상품 정보가 성공적으로 업데이트되었습니다.");
    }

    @PutMapping("/{productPk}")
    public ResponseEntity<String> updateProductStock(
            @PathVariable Long productPk,
            @RequestBody ProductStockUpdateRequestDto dto
    ) {
        dto.setProductPk(productPk);
        productService.updateProductStock(dto);
        return ResponseEntity.ok("상품 정보가 성공적으로 업데이트되었습니다.");
    }

}
