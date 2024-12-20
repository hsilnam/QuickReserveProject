package com.sparta.quickreserveproject.service;

import com.sparta.quickreserveproject.dto.ProductDto;
import com.sparta.quickreserveproject.dto.ProductListDto;
import com.sparta.quickreserveproject.entity.ProductEntity;
import com.sparta.quickreserveproject.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductListDto.Response getProductList(ProductListDto.Request dto) {
        List<ProductEntity> productList = (dto.getCursor() == null) ?
                (productRepository.findTopByOrderByIdAsc(dto.getSize())) :
                productRepository.findByIdGreaterThanOrderByIdAsc(dto.getCursor(), dto.getSize());

        List<ProductListDto.Response.Product> prodcutDtoList = productList.stream()
                .map(product -> new ProductListDto.Response.Product(product.getProductPk(), product.getProductName(),
                        product.getProductDescription(), product.getProductPrice(), product.getProductStock(),
                        product.getProductAvgRating(), product.getProductReviewCount()))
                .collect(Collectors.toList());

        Long nextCursor = (productList.size() == dto.getSize()) ? productList.get(productList.size() - 1).getProductPk() : null;
        return new ProductListDto.Response(prodcutDtoList, nextCursor);
    }

    @Override
    public ProductDto.Response getProduct(Long productPk) {
        ProductEntity product = productRepository.findById(productPk)
                .orElseThrow(() -> new IllegalArgumentException("해당 상품을 찾을 수 없습니다"));

        return new ProductDto.Response(product.getProductPk(), product.getProductName(),
                product.getProductDescription(), product.getProductPrice(), product.getProductStock(),
                product.getProductAvgRating(), product.getProductReviewCount());
    }


}
