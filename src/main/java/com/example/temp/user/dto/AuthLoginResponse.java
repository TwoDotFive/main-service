package com.example.temp.user.dto;

import com.example.temp.user.domain.value.UserRole;
import lombok.Builder;
import lombok.Getter;

@Getter
public class AuthLoginResponse {
    private String accessToken;
    private String refreshToken;
    private UserRole role;
    private boolean firstLogin;

    @Builder
    public AuthLoginResponse(String accessToken, String refreshToken, UserRole role, boolean firstLogin) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.role = role;
        this.firstLogin = firstLogin;
    }
}
