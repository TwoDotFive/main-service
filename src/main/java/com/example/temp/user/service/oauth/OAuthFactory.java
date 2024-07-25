package com.example.temp.user.service.oauth;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OAuthFactory {

    private OAuthUrlBuilder oAuthURLBuilder;
    private OAuthAdapter oAuthAdapter;

    @Builder
    private OAuthFactory(OAuthUrlBuilder oAuthURLBuilder, OAuthAdapter oAuthAdapter) {
        this.oAuthURLBuilder = oAuthURLBuilder;
        this.oAuthAdapter = oAuthAdapter;
    }
}

