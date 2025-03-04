package com.sparta.quickreserveproject.user.dto;

import com.sparta.quickreserveproject.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateResponseDto {
    private Long userPk;
    private String userId;
    private String userName;
    private String userPhoneNum;
    private String userEmail;
    private String userAddress;
    private String userAddressDetail;
    private User.Gender userGender;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
