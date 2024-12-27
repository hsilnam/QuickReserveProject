package com.sparta.quickreserveproject.controller;

import com.sparta.quickreserveproject.dto.CartItemAddRequestDto;
import com.sparta.quickreserveproject.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/items")
    public ResponseEntity<String> addItemToCart(
            @RequestBody CartItemAddRequestDto dto
            //            @AuthenticationPrincipal UserDetailsImpl user // TODO: jwt
    ) {
//        Long userPk = user.getUser().getUserPk(); // TODO: JWT에서 유저 ID 추출
        Long userPk = 1L;
        dto.setUserPk(userPk);
        cartService.addItemToCart(dto);
        return ResponseEntity.ok("카트에 성공적으로 담았습니다");
    }
}
