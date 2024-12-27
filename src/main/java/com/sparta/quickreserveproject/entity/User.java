package com.sparta.quickreserveproject.entity;

import com.sparta.quickreserveproject.global.entity.CUDEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

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
}