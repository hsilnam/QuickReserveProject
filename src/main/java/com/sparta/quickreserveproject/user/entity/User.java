package com.sparta.quickreserveproject.user.entity;

import com.sparta.quickreserveproject.user.util.PasswordUtil;
import com.sparta.quickreserveproject.user.util.EncryptionUtil;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;

@Entity
@EntityListeners(value = AuditingEntityListener.class)
@Table(name = "user")
@SQLDelete(sql = "UPDATE user SET deleted_at = now() WHERE user_pk = ?") // ✅ user 테이블로 수정
@Where(clause = "deleted_at is null")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
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

    @CreatedDate
    @Column(name = "CREATED_AT", updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;

    @Setter
    @Column(name = "DELETED_AT")
    private LocalDateTime deletedAt;

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

            if (!userPw.startsWith("$2a$")) {
                this.userPw = PasswordUtil.hashPassword(userPw);
            }
        } catch (Exception e) {
            throw new RuntimeException("유저 데이터 암호화 과정 중 에러가 발생하였습니다", e);
        }
    }

    @PostLoad
    private void decryptSensitiveData() {
        try {
            if (secretKey != null) {
                SecretKey key = EncryptionUtil.decodeKey(secretKey);
                this.userName = EncryptionUtil.decrypt(userName, key);
                this.userAddress = EncryptionUtil.decrypt(userAddress, key);
            }
        } catch (Exception e) {
            throw new RuntimeException("유저 데이터 복호화 과정 중 에러가 발생하였습니다", e);
        }
    }
}
