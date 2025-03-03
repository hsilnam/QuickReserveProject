package com.sparta.orderservice.client;

import com.sparta.orderservice.client.dto.CartItemResponseDto;
import com.sparta.orderservice.client.dto.CartRequestDto;
import com.sparta.orderservice.client.dto.CartResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cart-service")
public interface CartClient {

    @GetMapping("/cart")
    CartResponseDto getCart(@ModelAttribute CartRequestDto dto
//            @AuthenticationPrincipal UserDetailsImpl user // TODO: jwt
    );

    @GetMapping("/items/{itemPk}")
    CartItemResponseDto getCartItem(
            @PathVariable Long itemPk
//            @AuthenticationPrincipal UserDetailsImpl user // TODO: jwt
    );

    @DeleteMapping("/items/{itemPk}")
    String deleteCartItem(
            @PathVariable Long itemPk
//            @AuthenticationPrincipal UserDetailsImpl user // TODO: jwt
    );

}