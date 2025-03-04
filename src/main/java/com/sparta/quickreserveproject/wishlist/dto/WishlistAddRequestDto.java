package com.sparta.quickreserveproject.wishlist.dto;

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
