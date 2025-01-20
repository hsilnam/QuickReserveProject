package com.sparta.productservice.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductStockUpdateRequestDto {
        private Long productPk;
        private Integer productStock;
}
