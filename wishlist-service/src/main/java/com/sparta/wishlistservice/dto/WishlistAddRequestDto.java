package com.sparta.wishlistservice.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WishlistAddRequestDto {
        private Long userPk;
        private Long productPk;
}
