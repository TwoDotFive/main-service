package com.example.temp.user.service.oauth.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class OAuthResponse {
    private String platformId;
    private String platformType;
    private String email;
    private String name;
    private String profileImageUrl;

    @Builder
    private OAuthResponse(String platformId, String platformType, String email, String name, String profileImageUrl) {
        this.platformId = platformId;
        this.platformType = platformType;
        this.email = email;
        this.name = name;
        this.profileImageUrl = profileImageUrl;
    }
}