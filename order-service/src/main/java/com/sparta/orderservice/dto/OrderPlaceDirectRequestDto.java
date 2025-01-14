package com.sparta.quickreserveproject.order.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderPlaceDirectRequestDto {
    private Long userPk;
    private List<OrderPlaceDirectRequestDto.OrderItem> orderItems;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderItem {
        private Long productPk;
        private int quantity;
    }
}
