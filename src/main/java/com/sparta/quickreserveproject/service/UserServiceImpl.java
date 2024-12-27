package com.sparta.quickreserveproject.service;

import com.sparta.quickreserveproject.dto.UserCreateRequestDto;
import com.sparta.quickreserveproject.dto.UserCreateResponseDto;
import com.sparta.quickreserveproject.entity.User;
import com.sparta.quickreserveproject.global.util.EncryptionUtil;
import com.sparta.quickreserveproject.repository.UserRepository;
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
}
