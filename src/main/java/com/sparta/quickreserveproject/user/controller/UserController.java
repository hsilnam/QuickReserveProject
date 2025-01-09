package com.sparta.quickreserveproject.user.controller;

import com.sparta.quickreserveproject.user.dto.UserCreateRequestDto;
import com.sparta.quickreserveproject.user.dto.UserCreateResponseDto;
import com.sparta.quickreserveproject.user.dto.UserMyInfoResponseDto;
import com.sparta.quickreserveproject.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public UserCreateResponseDto createUser(@RequestBody UserCreateRequestDto dto) {
        return userService.createUser(dto);
    }

    @GetMapping("/myinfo")
    public UserMyInfoResponseDto getMyInfo(//            @AuthenticationPrincipal UserDetailsImpl user // TODO: jwt
    ) {
//        Long userPk = user.getUser().getUserPk(); // TODO: JWT에서 유저 ID 추출
        Long userPk = 1L;
        return userService.getMyInfo(userPk);
    }
}
