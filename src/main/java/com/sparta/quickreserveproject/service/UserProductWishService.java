package com.sparta.quickreserveproject.service;

import com.sparta.quickreserveproject.dto.UserProductWishAddDto;

public interface UserProductWishService {
    void addWish(UserProductWishAddDto.Request dto);
}
