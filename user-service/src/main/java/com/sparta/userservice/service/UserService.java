package com.sparta.quickreserveproject.user.service;

import com.sparta.quickreserveproject.user.dto.UserCreateRequestDto;
import com.sparta.quickreserveproject.user.dto.UserCreateResponseDto;
import com.sparta.quickreserveproject.user.dto.UserMyInfoResponseDto;

public interface UserService {
    UserCreateResponseDto createUser(UserCreateRequestDto dto);

    UserMyInfoResponseDto getMyInfo(Long userPk);
}
