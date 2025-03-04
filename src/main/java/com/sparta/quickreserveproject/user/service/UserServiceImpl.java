package com.sparta.quickreserveproject.user.service;

import com.sparta.quickreserveproject.user.dto.UserCreateRequestDto;
import com.sparta.quickreserveproject.user.dto.UserCreateResponseDto;
import com.sparta.quickreserveproject.user.dto.UserMyInfoResponseDto;
import com.sparta.quickreserveproject.user.entity.User;
import com.sparta.quickreserveproject.user.repository.UserRepository;
import com.sparta.quickreserveproject.user.util.EncryptionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.SecretKey;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserCreateResponseDto createUser(UserCreateRequestDto dto) {
        if (userRepository.existsByUserEmail(dto.getUserEmail())) {
            throw new IllegalArgumentException("Email이 이미존재합니다");
        }
        if (userRepository.existsByUserId(dto.getUserId())) {
            throw new IllegalArgumentException("Id가 이미존재합니다");
        }
        try {
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

            userRepository.save(user);

            SecretKey key = EncryptionUtil.decodeKey(user.getSecretKey());
            String decryptedUserName = EncryptionUtil.decrypt(user.getUserName(), key);
            String decryptedUserAddress = EncryptionUtil.decrypt(user.getUserAddress(), key);


            return UserCreateResponseDto.builder()
                    .userPk(user.getUserPk())
                    .userId(user.getUserId())
                    .userName(decryptedUserName)
                    .userPhoneNum(user.getUserPhoneNum())
                    .userEmail(user.getUserEmail())
                    .userAddress(decryptedUserAddress)
                    .userAddressDetail(user.getUserAddressDetail())
                    .userGender(user.getUserGender())
                    .createdAt(user.getCreatedAt())
                    .updatedAt(user.getUpdatedAt())
                    .build();
        } catch (Exception e) {
            throw new RuntimeException("유저 생성 중 에러가 발생하였습는니다", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public UserMyInfoResponseDto getMyInfo(Long userPk) {
        User user = userRepository.findById(userPk)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID를 가진 유저가 없습니다"));
        return UserMyInfoResponseDto.builder()
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
