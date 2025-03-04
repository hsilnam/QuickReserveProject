package com.sparta.quickreserveproject.cart.controller;

import com.sparta.quickreserveproject.cart.dto.CartItemAddRequestDto;
import com.sparta.quickreserveproject.cart.dto.CartItemResponseDto;
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
    public ResponseEntity<String> addItemToCart( // TODO: 수량이 0으로 들어왔을 시 추가 하지 않는 로직 필요
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
