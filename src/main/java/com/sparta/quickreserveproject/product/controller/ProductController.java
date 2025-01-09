package com.sparta.quickreserveproject.product.controller;

import com.sparta.quickreserveproject.product.dto.ProductDto;
import com.sparta.quickreserveproject.product.dto.ProductListDto;
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
    public ResponseEntity<ProductListDto.Response> getProductList(
            @ModelAttribute ProductListDto.Request dto
    ) {
        ProductListDto.Response response = productService.getProductList(dto);
        return ResponseEntity.ok(response);
    }



    @GetMapping("/{productPk}")
    public ResponseEntity<ProductDto.Response> getProduct(@PathVariable Long productPk) {
        ProductDto.Response response = productService.getProduct(productPk);
        return ResponseEntity.ok(response);
    }
}
