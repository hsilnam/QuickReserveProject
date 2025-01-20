package com.sparta.productservice.service;

import com.sparta.productservice.dto.*;
import com.sparta.productservice.entity.Product;
import com.sparta.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

@Service
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
        updateFieldsUsingReflection(product, dto);

        productRepository.save(product);
    }


    @Override
    public void updateProductStock(ProductStockUpdateRequestDto dto) {
        Product product = getProductEntity(dto.getProductPk());
        if (product == null) {
            throw new IllegalArgumentException("해당 상품을 찾을 수 없습니다");
        }
        product.setProductStock(dto.getProductStock());

        productRepository.save(product);
    }

    private void updateFieldsUsingReflection(Product product, ProductUpdateRequestDto dto) {
        Field[] dtoFields = dto.getClass().getDeclaredFields();
        Field[] entityFields = product.getClass().getDeclaredFields();

        for (Field dtoField : dtoFields) {
            dtoField.setAccessible(true);
            try {
                Object value = dtoField.get(dto);
                if (value != null) {
                    for (Field entityField : entityFields) {
                        entityField.setAccessible(true);
                        if (entityField.getName().equals(dtoField.getName()) && entityField.getType().equals(dtoField.getType())) {
                            entityField.set(product, value);
                            break;
                        }
                    }
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException("업데이트에 실패했습니다");
            }
        }
    }

    @Override
    public Product getProductEntity(Long productPk) {
        return productRepository.findById(productPk)
                .orElse(null);
    }


}
