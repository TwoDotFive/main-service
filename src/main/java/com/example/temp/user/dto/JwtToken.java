package com.example.temp.user.dto;

import lombok.Getter;

@Getter
public class JwtToken {

    private final String accessToken;

    public JwtToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
