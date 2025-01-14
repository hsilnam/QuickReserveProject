package com.sparta.quickreserveproject.user.entity;

import com.sparta.quickreserveproject.global.entity.CUDEntity;
import com.sparta.quickreserveproject.global.util.EncryptionUtil;
import com.sparta.quickreserveproject.global.util.PasswordUtil;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.crypto.SecretKey;

@Entity
@Table(name = "user")
@SQLDelete(sql = "UPDATE product SET deleted_at = now() WHERE product_pk = ?")
@Where(clause = "deleted_at is null")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends CUDEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userPk;

    @Column(nullable = false, unique = true)
    private String userId;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String userPw;

    @Column(nullable = false)
    private String userPhoneNum;

    @Column(nullable = false, unique = true)
    private String userEmail;

    @Column(nullable = false)
    private String userAddress;

    private String userAddressDetail;

    @Enumerated(EnumType.STRING)
    private Gender userGender;

    private String userSocialType;

    private String userSocialId;

    public enum Gender {
        MALE, FEMALE
    }

    private String secretKey;

    @PrePersist
    @PreUpdate
    private void encryptSensitiveData() {
        try {
            SecretKey key = (secretKey == null) ? EncryptionUtil.generateKey() : EncryptionUtil.decodeKey(secretKey);
            if (secretKey == null) {
                this.secretKey = EncryptionUtil.encodeKey(key);
            }
            this.userName = EncryptionUtil.encrypt(userName, key);
            this.userAddress = EncryptionUtil.encrypt(userAddress, key);
            this.userPw = PasswordUtil.hashPassword(userPw);
        } catch (Exception e) {
            throw new RuntimeException("유저 데이터 암호화 과정 중 에러가 발생하였습니다", e);
        }
    }

    @PostLoad
    private void decryptSensitiveData() {
        try {
            SecretKey key = EncryptionUtil.decodeKey(secretKey);
            this.userName = EncryptionUtil.decrypt(userName, key);
            this.userAddress = EncryptionUtil.decrypt(userAddress, key);
        } catch (Exception e) {
            throw new RuntimeException("유저 데이터 복호화 과정 중 에러가 발생하였습니다", e);
        }
    }
}