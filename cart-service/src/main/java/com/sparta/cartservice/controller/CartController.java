package com.sparta.cartservice.controller;

import com.sparta.quickreserveproject.cart.dto.CartItemAddRequestDto;
import com.sparta.quickreserveproject.cart.dto.CartRequestDto;
import com.sparta.quickreserveproject.cart.dto.CartResponseDto;
import com.sparta.quickreserveproject.cart.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public ResponseEntity<CartResponseDto> getCart(
            @ModelAttribute CartRequestDto dto
//            @AuthenticationPrincipal UserDetailsImpl user // TODO: jwt
    ) {
//        Long userPk = user.getUser().getUserPk(); // TODO: JWT에서 유저 ID 추출
        Long userPk = 1L;
        dto.setUserPk(userPk);
        CartResponseDto response = cartService.getCart(dto);
        return ResponseEntity.ok(response);
    }
}
