package com.example.temp.user.service.impl;

import com.example.temp.user.domain.User;
import com.example.temp.user.domain.UserRepository;
import com.example.temp.user.dto.AuthLoginResponse;
import com.example.temp.user.dto.JwtToken;
import com.example.temp.user.service.CreateUserService;
import com.example.temp.user.service.oauth.response.OAuthResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicBoolean;

@Service
@Slf4j
@RequiredArgsConstructor
public class CreateUserServiceImpl implements CreateUserService {

    private final UserRepository userRepository;
    private final JwtTokenService jwtService;

    @Override
    @Transactional
    public AuthLoginResponse doService(OAuthResponse oAuthResponse) {
        // OAuth 로그인을 시도한 사용자 정보가 DB에 존재하는지 확인 후 없다면 등록
        AtomicBoolean firstLogin = new AtomicBoolean(false);
        User findUser = userRepository.findByPlatformTypeAndPlatformId(oAuthResponse.getPlatformType(), oAuthResponse.getPlatformId())
                .orElseGet(() -> {
                    User saveUser = User.build(oAuthResponse);
                    firstLogin.set(true);
                    return userRepository.save(saveUser);
                });

        // JWT Access Token, Refresh Token 발급
        JwtToken tokens = jwtService.createJwtToken(findUser);
        log.info("access token:{}", tokens.getAccessToken());
        log.info("refresh token:{}", tokens.getRefreshToken());

        return AuthLoginResponse.builder()
                .accessToken(tokens.getAccessToken())
                .refreshToken(tokens.getRefreshToken())
                .role(findUser.getUserRole())
                .firstLogin(firstLogin.get())
                .build();
    }

}
