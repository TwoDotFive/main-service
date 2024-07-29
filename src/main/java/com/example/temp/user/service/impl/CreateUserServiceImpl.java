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

import java.util.HashMap;

@Service
@Slf4j
@RequiredArgsConstructor
public class CreateUserServiceImpl implements CreateUserService {

    private final UserRepository userRepository;
    private final JwtTokenService jwtService;

    private static final String ROLE_CLAIM = "role";
    private static final String NAME_CLAIM = "name";
    private static final String PROFILE_IMAGE_CLAIM = "profileImageUrl";

    @Override
    @Transactional
    public AuthLoginResponse doService(OAuthResponse oAuthResponse) {
        // OAuth 로그인을 시도한 사용자 정보가 DB에 존재하는지 확인 후 없다면 등록
        User findUser = userRepository.findByEmail(oAuthResponse.getEmail())
                .orElseGet(() -> {
                    User saveUser = User.build(oAuthResponse);
                    return userRepository.save(saveUser);
                });

        // 기존 회원의 경우 name, profileImageUrl 변하면 update
        findUser.updateProfile(oAuthResponse.getNickname(), oAuthResponse.getProfileImageUrl());

        // JWT Access Token, Refresh Token 발급
        JwtToken tokens = createJwtToken(findUser);
        log.info("access token:{}", tokens.getAccessToken());
        log.info("refresh token:{}", tokens.getRefreshToken());

        return AuthLoginResponse.builder()
                .accessToken(tokens.getAccessToken())
                .refreshToken(tokens.getRefreshToken())
                .role(findUser.getUserRole())
                .build();
    }

    private JwtToken createJwtToken(User user) {
        // JWT 토큰 생성을 위한 claims 생성
        HashMap<String, String> claims = new HashMap<>();
        claims.put(ROLE_CLAIM, user.getUserRole().name());
        claims.put(NAME_CLAIM, user.getName());
        claims.put(PROFILE_IMAGE_CLAIM, user.getProfileImageUrl());

        // Access Token 생성
        final String accessToken = jwtService.generateAccessToken(claims, user);
        // Refresh Token 생성
        final String refreshToken = jwtService.generateRefreshToken(claims, user);

        log.info(">>>> {} generate Tokens", user.getName());

        // Refresh Token 저장 - REDIS
//        RefreshToken rt = RefreshToken.builder()
//                .refreshToken(refreshToken)
//                .email(user.getEmail())
//                .build();
//        refreshTokenService.saveRefreshToken(rt);

        return JwtToken.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
