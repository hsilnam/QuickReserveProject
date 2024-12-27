package com.sparta.quickreserveproject.service;

import com.sparta.quickreserveproject.dto.UserCreateRequestDto;
import com.sparta.quickreserveproject.dto.UserCreateResponseDto;
import com.sparta.quickreserveproject.entity.User;
import com.sparta.quickreserveproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserCreateResponseDto createUser(UserCreateRequestDto dto) {
        if (userRepository.existsByUserEmail(dto.getUserEmail())) {
            throw new IllegalArgumentException("Email이 이미존재합니다");
        }

        User user = User.builder()
                .userId(dto.getUserId())
                .userName(dto.getUserName())
                .userPw(dto.getUserPw())
                .userPhoneNum(dto.getUserPhoneNum())
                .userEmail(dto.getUserEmail())
                .userAddress(dto.getUserAddress())
                .userAddressDetail(dto.getUserAddressDetail())
                .userGender(dto.getUserGender())
                .userSocialType(dto.getUserSocialType())
                .userSocialId(dto.getUserSocialId())
                .build();

        user = userRepository.save(user);
        return UserCreateResponseDto.builder()
                .userPk(user.getUserPk())
                .userId(user.getUserId())
                .userName(user.getUserName())
                .userPhoneNum(user.getUserPhoneNum())
                .userEmail(user.getUserEmail())
                .userAddress(user.getUserAddress())
                .userAddressDetail(user.getUserAddressDetail())
                .userGender(user.getUserGender())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}
