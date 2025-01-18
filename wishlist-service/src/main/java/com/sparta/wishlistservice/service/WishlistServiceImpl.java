package com.sparta.wishlistservice.service;

import com.sparta.wishlistservice.client.ProductClient;
import com.sparta.wishlistservice.dto.ProductResponseDto;
import com.sparta.wishlistservice.dto.WishlistAddRequestDto;
import com.sparta.wishlistservice.dto.WishlistRequestDto;
import com.sparta.wishlistservice.dto.WishlistResponseDto;
import com.sparta.wishlistservice.entity.Wishlist;
import com.sparta.wishlistservice.repository.WishlistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WishlistServiceImpl implements WishlistService {
    private final WishlistRepository userProductWishRepository;
    private final ProductClient productClient;

    @Override
    public void addWishProduct(WishlistAddRequestDto dto) {
        ProductResponseDto product = productClient.getProduct(dto.getProductPk());

        if (product == null) {
            throw new IllegalArgumentException("상품을 찾을 수 없습니다.");
        }

        Wishlist userProductWish = Wishlist.builder()
                .userPk(dto.getUserPk())
                .productPk(dto.getProductPk())
                .build();
        userProductWishRepository.save(userProductWish);
    }

    @Override
    public WishlistResponseDto getWishList(WishlistRequestDto dto) {
        Pageable pageable = PageRequest.of(0, dto.getSize());
        Page<Wishlist> wishPage = (dto.getCursor() == null) ?
                userProductWishRepository.findAllByOrderByWishlistPkAsc(pageable) :
                userProductWishRepository.findByWishlistPkGreaterThanOrderByWishlistPkAsc(dto.getCursor(), pageable);

        List<WishlistResponseDto.WishProduct> wishDtoList = wishPage.stream()
                .map(wishProduct -> {
                            try {
                                ProductResponseDto product = productClient.getProduct(wishProduct.getProductPk());
                                return new WishlistResponseDto.WishProduct(
                                        product.getProductPk(),
                                        product.getProductName(),
                                        product.getProductDescription(),
                                        product.getProductPrice(),
                                        product.getProductStock(),
                                        product.getProductAvgRating(),
                                        product.getProductReviewCount()
                                );
                            } catch (Exception e) {
                                // Product 정보를 찾을 수 없는 경우 기본값 처리
                                return null;
                            }
                        }
                )
                .filter(product -> product != null)
                .collect(Collectors.toList());

        Long nextCursor = wishPage.hasNext() ?
                wishDtoList.get(wishDtoList.size() - 1).getProductPk() : null;
        return new WishlistResponseDto(wishDtoList, nextCursor);
    }
}
