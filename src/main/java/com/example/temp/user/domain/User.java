package com.example.temp.user.domain;

import com.example.temp.common.entity.BaseTimeEntity;
import com.example.temp.common.util.IdUtil;
import com.example.temp.user.domain.value.Nickname;
import com.example.temp.user.domain.value.PlatformType;
import com.example.temp.user.domain.value.UserRole;
import com.example.temp.user.service.oauth.response.OAuthResponse;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity {
    @Id
    private Long id;
    private Long platformId;
    @Enumerated(EnumType.STRING)
    private PlatformType platformType;
    private String email;
    private String name;
    @Embedded
    private Nickname nickname;
    private String profileImageUrl;
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    public static User build(OAuthResponse response) {
        User user = new User();
        user.id = IdUtil.create();
        user.platformId = response.getPlatformId();
        user.platformType = response.getPlatformType();
        user.email = response.getEmail();
        user.name = response.getNickname();
        // TODO nickname 정책 확인
        user.nickname = new Nickname("temp");
        user.profileImageUrl = response.getProfileImageUrl();
        user.userRole = UserRole.USER;
        return user;
    }

    public String getNickname() {
        return nickname.getValue();
    }

    public void updateProfile(String name, String profileImageUrl) {
        this.name = name;
        this.profileImageUrl = profileImageUrl;
    }

}
