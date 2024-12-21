package com.sparta.quickreserveproject.controller;

import com.sparta.quickreserveproject.dto.UserProductWishAddDto;
import com.sparta.quickreserveproject.dto.UserProductWishListDto;
import com.sparta.quickreserveproject.service.UserProductWishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wishList")
public class UserProductWishController {

    @Autowired
    private UserProductWishService userProductWishService;

    @PostMapping
    public ResponseEntity<String> addWish(
            @RequestBody UserProductWishAddDto.Request dto
//            @AuthenticationPrincipal UserDetailsImpl user // TODO: jwt
    ) {
//        Long userPk = user.getUser().getUserPk(); // TODO: JWT에서 유저 ID 추출
        Long userPk = 1L;
        dto.setUserPk(userPk);
        userProductWishService.addWish(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("위시리스트에 추가 완료");
    }

    @GetMapping
    public ResponseEntity<UserProductWishListDto.Response> getWishList(
            @ModelAttribute UserProductWishListDto.Request dto
//            @AuthenticationPrincipal UserDetailsImpl user // TODO: jwt
    ) {
//        Long userPk = user.getUser().getUserPk(); // TODO: JWT에서 유저 ID 추출
        Long userPk = 1L;
        dto.setUserPk(userPk);
        UserProductWishListDto.Response response = userProductWishService.getWishList(dto);
        return ResponseEntity.ok(response);
    }
}
