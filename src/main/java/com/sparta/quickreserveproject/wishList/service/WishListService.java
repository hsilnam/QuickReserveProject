package com.sparta.quickreserveproject.wishList.service;

import com.sparta.quickreserveproject.wishList.dto.WishListAddRequestDto;
import com.sparta.quickreserveproject.user.dto.UserProductWishListDto;

public interface WishListService {
    void addWish(WishListAddRequestDto dto);

    UserProductWishListDto.Response getWishList(UserProductWishListDto.Request dto);
}
