package com.sparta.quickreserveproject.wishList.dto;

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
