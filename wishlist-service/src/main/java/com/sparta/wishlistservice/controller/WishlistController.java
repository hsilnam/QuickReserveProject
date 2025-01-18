package com.sparta.wishlistservice.controller;

import com.sparta.wishlistservice.dto.WishlistAddRequestDto;
import com.sparta.wishlistservice.dto.WishlistRequestDto;
import com.sparta.wishlistservice.dto.WishlistResponseDto;
import com.sparta.wishlistservice.service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wishlist")
@RequiredArgsConstructor
public class WishlistController {
    private final WishlistService wishListService;

    @PostMapping
    public ResponseEntity<String> addWishProduct(
            @RequestBody WishlistAddRequestDto dto
//            @AuthenticationPrincipal UserDetailsImpl user // TODO: jwt
    ) {
//        Long userPk = user.getUser().getUserPk(); // TODO: JWT에서 유저 ID 추출
        Long userPk = 1L;
        dto.setUserPk(userPk);
        wishListService.addWishProduct(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("위시리스트에 추가 완료");
    }

    @GetMapping
    public ResponseEntity<WishlistResponseDto> getWishList(
            @ModelAttribute WishlistRequestDto dto
//            @AuthenticationPrincipal UserDetailsImpl user // TODO: jwt
    ) {
//        Long userPk = user.getUser().getUserPk(); // TODO: JWT에서 유저 ID 추출
        Long userPk = 1L;
        dto.setUserPk(userPk);
        WishlistResponseDto response = wishListService.getWishList(dto);
        return ResponseEntity.ok(response);
    }
}
