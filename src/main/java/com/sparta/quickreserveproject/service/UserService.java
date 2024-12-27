package com.sparta.quickreserveproject.service;

import com.sparta.quickreserveproject.dto.UserCreateRequestDto;
import com.sparta.quickreserveproject.dto.UserCreateResponseDto;

public interface UserService {
    UserCreateResponseDto createUser(UserCreateRequestDto dto);
}
