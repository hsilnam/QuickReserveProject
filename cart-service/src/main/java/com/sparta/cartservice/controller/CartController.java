package com.sparta.cartservice.controller;

import com.sparta.cartservice.dto.CartItemAddRequestDto;
import com.sparta.cartservice.dto.CartItemResponseDto;
import com.sparta.cartservice.dto.CartRequestDto;
import com.sparta.cartservice.dto.CartResponseDto;
import com.sparta.cartservice.service.CartService;
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


    @GetMapping("/items/{itemPk}")
    public ResponseEntity<CartItemResponseDto> getCartItem(
            @PathVariable Long itemPk
//            @AuthenticationPrincipal UserDetailsImpl user // TODO: jwt
    ) {
//        Long userPk = user.getUser().getUserPk(); // TODO: JWT에서 유저 ID 추출
        Long userPk = 1L;
        CartItemResponseDto response = cartService.getCartItem(userPk, itemPk);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/items/{itemPk}")
    public ResponseEntity<String> deleteCartItem(
            @PathVariable Long itemPk
//            @AuthenticationPrincipal UserDetailsImpl user // TODO: jwt
    ) {
//        Long userPk = user.getUser().getUserPk(); // TODO: JWT에서 유저 ID 추출
        Long userPk = 1L;
        cartService.deleteCartItem(userPk, itemPk);
        return ResponseEntity.ok("카트 아이템을 성공적으로 삭제했습니다");
    }
}
