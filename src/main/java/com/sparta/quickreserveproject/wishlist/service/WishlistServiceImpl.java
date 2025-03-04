package com.sparta.quickreserveproject.wishlist.service;

import com.sparta.quickreserveproject.product.entity.Product;
import com.sparta.quickreserveproject.product.repository.ProductRepository;
import com.sparta.quickreserveproject.wishlist.dto.WishlistAddRequestDto;
import com.sparta.quickreserveproject.wishlist.dto.WishlistRequestDto;
import com.sparta.quickreserveproject.wishlist.dto.WishlistResponseDto;
import com.sparta.quickreserveproject.wishlist.entity.Wishlist;
import com.sparta.quickreserveproject.wishlist.repository.WishlistRepository;
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
    private final WishlistRepository wishlistRepository;
    private final ProductRepository productRepository;

    @Override
    public void addWishProduct(WishlistAddRequestDto dto) {
        Product product = productRepository.findById(dto.getProductPk())
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다."));

        Wishlist wishlist = Wishlist.builder()
                .userPk(dto.getUserPk())
                .productPk(dto.getProductPk())
                .build();
        wishlistRepository.save(wishlist);
    }

    @Override
    public WishlistResponseDto getWishList(WishlistRequestDto dto) {
        Pageable pageable = PageRequest.of(0, dto.getSize());
        Page<Wishlist> wishPage = (dto.getCursor() == null) ?
                wishlistRepository.findAllByOrderByWishlistPkAsc(pageable) :
                wishlistRepository.findByWishlistPkGreaterThanOrderByWishlistPkAsc(dto.getCursor(), pageable);

        List<WishlistResponseDto.WishProduct> wishDtoList = wishPage.stream()
                .map(wishProduct -> productRepository.findById(wishProduct.getProductPk())
                        .map(product -> new WishlistResponseDto.WishProduct(
                                product.getProductPk(),
                                product.getProductName(),
                                product.getProductDescription(),
                                product.getProductPrice(),
                                product.getProductStock(),
                                product.getProductAvgRating(),
                                product.getProductReviewCount()
                        ))
                        .orElse(null))
                .filter(product -> product != null)
                .collect(Collectors.toList());

        Long nextCursor = wishPage.hasNext() ?
                wishDtoList.get(wishDtoList.size() - 1).getProductPk() : null;
        return new WishlistResponseDto(wishDtoList, nextCursor);
    }
}
