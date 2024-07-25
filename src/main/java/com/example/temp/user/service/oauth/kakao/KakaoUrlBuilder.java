package com.example.temp.user.service.oauth.kakao;

import com.example.temp.user.service.oauth.OAuthUrlBuilder;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class KakaoUrlBuilder implements OAuthUrlBuilder {

    @Value("${oauth2.kakao.authorization-uri}")
    private String authorizationUri;
    @Value("${oauth2.kakao.token-uri}")
    private String tokenUri;
    @Value("${oauth2.kakao.profile-uri}")
    private String profileUri;

    @Value("${oauth2.kakao.client-id}")
    private String clientId;
    @Value("${oauth2.kakao.redirect-uri}")
    private String redirectUri;
    @Value("${oauth2.kakao.client-secret}")
    private String clientSecret;

    @Override
    public String authorize() {
        return authorizationUri
                + "?response_type=code"
                + "&client_id=" + clientId
                + "&redirect_uri=" + redirectUri
                + "&scope=openid";
    }
}