package com.sparta.quickreserveproject.service;

import com.sparta.quickreserveproject.dto.UserProductWishAddDto;
import com.sparta.quickreserveproject.dto.UserProductWishListDto;

public interface UserProductWishService {
    void addWish(UserProductWishAddDto.Request dto);

    UserProductWishListDto.Response getWishList(UserProductWishListDto.Request dto);
}
