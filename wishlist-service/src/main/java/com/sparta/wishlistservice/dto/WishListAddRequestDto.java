package com.sparta.wishlistservice.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WishListAddRequestDto {
        private Long userPk;
        private Long productPk;
}
