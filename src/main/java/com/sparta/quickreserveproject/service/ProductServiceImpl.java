package com.sparta.quickreserveproject.service;

import com.sparta.quickreserveproject.dto.ProductDto;
import com.sparta.quickreserveproject.dto.ProductListDto;
import com.sparta.quickreserveproject.entity.Product;
import com.sparta.quickreserveproject.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductListDto.Response getProductList(ProductListDto.Request dto) {
        Pageable pageable = PageRequest.of(0, dto.getSize());
        Page<Product> productPage = (dto.getCursor() == null) ?
                productRepository.findAllByOrderByProductPkAsc(pageable) :
                productRepository.findByProductPkGreaterThanOrderByProductPkAsc(dto.getCursor(), pageable);

        List<ProductListDto.Response.Product> productDtoList = productPage.stream()
                .map(product -> new ProductListDto.Response.Product(product.getProductPk(), product.getProductName(),
                        product.getProductDescription(), product.getProductPrice(), product.getProductStock(),
                        product.getProductAvgRating(), product.getProductReviewCount()))
                .collect(Collectors.toList());

        Long nextCursor = productPage.hasNext() ?
                productDtoList.get(productDtoList.size() - 1).getProductPk() : null;
        return new ProductListDto.Response(productDtoList, nextCursor);
    }

    @Override
    public ProductDto.Response getProduct(Long productPk) {
        Product product = getProductEntity(productPk);
        if(product == null) {
            throw new IllegalArgumentException("해당 상품을 찾을 수 없습니다");
        }

        return new ProductDto.Response(product.getProductPk(), product.getProductName(),
                product.getProductDescription(), product.getProductPrice(), product.getProductStock(),
                product.getProductAvgRating(), product.getProductReviewCount());
    }

    @Override
    public Product getProductEntity(Long productPk) {
        return productRepository.findById(productPk)
                .orElse(null);
    }


}
