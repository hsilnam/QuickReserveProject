package com.sparta.cartservice.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItemAddRequestDto {
    private Long userPk;
    private Long productPk;
    private int quantity;
}
