package com.example.temp.user.domain;

import com.example.temp.common.entity.BaseTimeEntity;
import com.example.temp.common.util.IdUtil;
import com.example.temp.user.domain.value.Nickname;
import com.example.temp.user.domain.value.PlatformType;
import com.example.temp.user.domain.value.UserRole;
import com.example.temp.user.service.oauth.response.OAuthResponse;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity implements UserDetails {
    @Id
    private Long id;
    private Long platformId;
    private PlatformType platformType;
    private String email;
    private String name;
    @Embedded
    private Nickname nickname;
    private String profileImageUrl;
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

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public String getPassword() {
        return this.email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
}
