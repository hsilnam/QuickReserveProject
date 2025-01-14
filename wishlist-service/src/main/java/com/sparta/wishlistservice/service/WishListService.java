package com.sparta.wishlistservice.service;

import com.sparta.wishlistservice.dto.WishListAddRequestDto;
import com.sparta.quickreserveproject.user.dto.UserProductWishListDto;

public interface WishListService {
    void addWish(WishListAddRequestDto dto);

    UserProductWishListDto.Response getWishList(UserProductWishListDto.Request dto);
}
