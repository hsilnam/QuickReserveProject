package com.sparta.quickreserveproject.controller;

import com.sparta.quickreserveproject.dto.UserCreateRequestDto;
import com.sparta.quickreserveproject.dto.UserCreateResponseDto;
import com.sparta.quickreserveproject.service.UserService;
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
}
