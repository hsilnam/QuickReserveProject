package com.sparta.quickreserveproject.dto;

import com.sparta.quickreserveproject.entity.User;
import lombok.*;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateRequestDto {
    private String userId;
    private String userName;
    private String userPw;
    private String userPhoneNum;
    private String userEmail;
    private String userAddress;
    private String userAddressDetail;
    private User.Gender userGender;
    private String userSocialType;
    private String userSocialId;
}
