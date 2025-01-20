package com.sparta.orderservice.client.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductStockUpdateRequestDto {
        private Long productPk;
        private int productStock;
}
