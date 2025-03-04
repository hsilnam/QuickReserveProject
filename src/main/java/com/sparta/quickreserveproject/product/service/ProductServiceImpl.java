package com.sparta.quickreserveproject.product.service;

import com.sparta.quickreserveproject.product.dto.*;
import com.sparta.quickreserveproject.product.entity.Product;
import com.sparta.quickreserveproject.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductListResponseDto getProductList(ProductListRequestDto dto) {
        Pageable pageable = PageRequest.of(0, dto.getSize());
        Page<Product> productPage = (dto.getCursor() == null) ?
                productRepository.findAllByOrderByProductPkAsc(pageable) :
                productRepository.findByProductPkGreaterThanOrderByProductPkAsc(dto.getCursor(), pageable);

        List<ProductListResponseDto.Product> productDtoList = productPage.stream()
                .map(product -> ProductListResponseDto.Product.builder()
                        .productPk(product.getProductPk())
                        .productName(product.getProductName())
                        .productDescription(product.getProductDescription())
                        .productPrice(product.getProductPrice())
                        .productStock(product.getProductStock())
                        .productAvgRating(product.getProductAvgRating())
                        .productReviewCount(product.getProductReviewCount())
                        .build())
                .collect(Collectors.toList());

        Long nextCursor = productPage.hasNext() ?
                productDtoList.get(productDtoList.size() - 1).getProductPk() : null;
        return new ProductListResponseDto(productDtoList, nextCursor);
    }

    @Override
    public ProductResponseDto getProduct(Long productPk) {
        Product product = getProductEntity(productPk);
        if (product == null) {
            throw new IllegalArgumentException("해당 상품을 찾을 수 없습니다");
        }

        return ProductResponseDto.builder()
                .productPk(product.getProductPk())
                .productName(product.getProductName())
                .productDescription(product.getProductDescription())
                .productPrice(product.getProductPrice())
                .productStock(product.getProductStock())
                .productAvgRating(product.getProductAvgRating())
                .productReviewCount(product.getProductReviewCount())
                .build();
    }


    @Override
    public void updateProduct(ProductUpdateRequestDto dto) {
        Product product = getProductEntity(dto.getProductPk());
        if (product == null) {
            throw new IllegalArgumentException("해당 상품을 찾을 수 없습니다");
        }

        if (dto.getProductName() != null) product.setProductName(dto.getProductName());
        if (dto.getProductDescription() != null) product.setProductDescription(dto.getProductDescription());
        if (dto.getProductPrice() != null) product.setProductPrice(dto.getProductPrice());
        if (dto.getProductStock() != null) product.setProductStock(dto.getProductStock());
        if (dto.getProductAvgRating() != null) product.setProductAvgRating(dto.getProductAvgRating());
        if (dto.getProductReviewCount() != null) product.setProductReviewCount(dto.getProductReviewCount());

        productRepository.save(product);
    }


    @Override
    public void updateProductStock(ProductStockUpdateRequestDto dto) {
        Product product = getProductEntity(dto.getProductPk());
        if (product == null) {
            throw new IllegalArgumentException("해당 상품을 찾을 수 없습니다");
        }
        System.out.println(dto.getProductPk());
        System.out.println(dto.getProductStock());
        product.setProductStock(dto.getProductStock());

        productRepository.save(product);
    }

    @Override
    public Product getProductEntity(Long productPk) {
        return productRepository.findById(productPk)
                .orElse(null);
    }


}
