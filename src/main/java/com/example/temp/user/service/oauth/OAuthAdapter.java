package com.example.temp.user.service.oauth;

import com.example.temp.user.service.oauth.response.OAuthResponse;

public interface OAuthAdapter {

    String getToken(String tokenURL);

    OAuthResponse getProfile(String accessToken);

}
