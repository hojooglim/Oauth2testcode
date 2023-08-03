package com.example.myblogagain.user.entity;

import com.example.myblogagain.config.info.ProviderType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String nickname;

    @Column
    private ProviderType providerType;

    @Column
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;


    @Builder
    public User(String email, String password, UserRoleEnum role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public User(String email, String nickname, ProviderType providerType,
            UserRoleEnum userRoleEnum) {
        this.email = email;
        this.nickname = nickname;
        this.providerType = providerType;
        this.role = userRoleEnum;
    }

    public void updateEmail(String email) {
        this.email = email;
    }
}
