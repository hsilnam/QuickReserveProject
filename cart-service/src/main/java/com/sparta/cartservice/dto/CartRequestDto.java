package com.sparta.cartservice.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartRequestDto {
    private Long cursor;
    private int size = 10;
    private Long userPk;
}
