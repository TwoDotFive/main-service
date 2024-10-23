package com.example.temp.user.dto;

import com.example.temp.user.domain.value.UserRole;
import lombok.Builder;
import lombok.Getter;

@Getter
public class AuthLoginResponse {

    private final String accessToken;
    private final UserRole role;
    private final boolean firstLogin;

    @Builder
    public AuthLoginResponse(String accessToken, UserRole role, boolean firstLogin) {
        this.accessToken = accessToken;
        this.role = role;
        this.firstLogin = firstLogin;
    }
}
