package com.sparta.quickreserveproject.service;

import com.sparta.quickreserveproject.dto.UserCreateRequestDto;
import com.sparta.quickreserveproject.dto.UserCreateResponseDto;
import com.sparta.quickreserveproject.dto.UserMyInfoResponseDto;

public interface UserService {
    UserCreateResponseDto createUser(UserCreateRequestDto dto);

    UserMyInfoResponseDto getMyInfo(Long userPk);
}
