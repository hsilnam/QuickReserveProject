package com.sparta.quickreserveproject.product.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductListRequestDto {
    private Long cursor;
    private int size = 10;
}
