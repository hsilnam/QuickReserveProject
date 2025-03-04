package com.sparta.quickreserveproject.wishlist.service;

import com.sparta.quickreserveproject.wishlist.dto.WishlistAddRequestDto;
import com.sparta.quickreserveproject.wishlist.dto.WishlistRequestDto;
import com.sparta.quickreserveproject.wishlist.dto.WishlistResponseDto;

public interface WishlistService {
    void addWishProduct(WishlistAddRequestDto dto);

    WishlistResponseDto getWishList(WishlistRequestDto dto);
}
