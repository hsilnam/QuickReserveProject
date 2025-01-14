package com.sparta.quickreserveproject.order.controller;

import com.sparta.quickreserveproject.order.dto.OrderPlaceDirectRequestDto;
import com.sparta.quickreserveproject.order.dto.OrderPlaceCartRequestDto;
import com.sparta.quickreserveproject.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/cart")
    public ResponseEntity<String> placeOrderFromCart(
            @RequestBody OrderPlaceCartRequestDto dto
            //            @AuthenticationPrincipal UserDetailsImpl user // TODO: jwt
    ) {
//        Long userPk = user.getUser().getUserPk(); // TODO: JWT에서 유저 ID 추출
        Long userPk = 1L;
        dto.setUserPk(userPk);
        orderService.placeOrderFromCart(dto);
        return ResponseEntity.ok("성공적으로 주문이 완료되었습니다");
    }

    @PostMapping("/direct")
    public ResponseEntity<String> placeDirectOrder(
            @RequestBody OrderPlaceDirectRequestDto dto
            //            @AuthenticationPrincipal UserDetailsImpl user // TODO: jwt
    ) {
//        Long userPk = user.getUser().getUserPk(); // TODO: JWT에서 유저 ID 추출
        Long userPk = 1L;
        dto.setUserPk(userPk);
        orderService.placeDirectOrder(dto);
        return ResponseEntity.ok("성공적으로 주문이 완료되었습니다");
    }
}
