package com.sparta.wishlistservice.service;

import com.sparta.wishlistservice.dto.WishlistAddRequestDto;
import com.sparta.wishlistservice.dto.WishlistRequestDto;
import com.sparta.wishlistservice.dto.WishlistResponseDto;

public interface WishlistService {
    void addWishProduct(WishlistAddRequestDto dto);

    WishlistResponseDto getWishList(WishlistRequestDto dto);
}
