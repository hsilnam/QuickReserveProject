package com.sparta.wishlistservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WishlistRequestDto {
    private Long userPk;
    private Long cursor;
    private int size = 10;
}
