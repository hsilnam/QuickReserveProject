package com.sparta.quickreserveproject.wishList.service;

import com.sparta.quickreserveproject.wishList.dto.WishListAddDto;
import com.sparta.quickreserveproject.user.dto.UserProductWishListDto;

public interface WishListService {
    void addWish(WishListAddDto.Request dto);

    UserProductWishListDto.Response getWishList(UserProductWishListDto.Request dto);
}
