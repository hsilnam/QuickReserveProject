package com.sparta.productservice.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductListRequestDto {
    private Long cursor;
    private int size = 10;
}
