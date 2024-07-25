package com.example.temp.user.service;

import com.example.temp.user.dto.AuthLoginResponse;
import com.example.temp.user.service.oauth.response.OAuthResponse;

public interface CreateUserService {
    AuthLoginResponse doService(OAuthResponse response);
}
