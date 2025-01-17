package com.sparta.userservice.service;

import com.sparta.userservice.dto.UserCreateRequestDto;
import com.sparta.userservice.dto.UserCreateResponseDto;
import com.sparta.userservice.dto.UserMyInfoResponseDto;

public interface UserService {
    UserCreateResponseDto createUser(UserCreateRequestDto dto);

    UserMyInfoResponseDto getMyInfo(Long userPk);
}
