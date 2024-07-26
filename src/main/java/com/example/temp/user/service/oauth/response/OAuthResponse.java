package com.example.temp.user.service.oauth.response;

import com.example.temp.user.domain.value.PlatformType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class OAuthResponse {
    private Long platformId;
    private PlatformType platformType;
    private String email;
    private String name;
    private String profileImageUrl;

    @Builder
    private OAuthResponse(Long platformId, PlatformType platformType, String email, String name, String profileImageUrl) {
        this.platformId = platformId;
        this.platformType = platformType;
        this.email = email;
        this.name = name;
        this.profileImageUrl = profileImageUrl;
    }
}