package com.example.temp.user.service.impl;

import com.example.temp.user.domain.value.PlatformType;
import com.example.temp.user.service.oauth.OAuthAdapter;
import com.example.temp.user.service.oauth.OAuthFactory;
import com.example.temp.user.service.oauth.OAuthUrlBuilder;
import com.example.temp.user.service.oauth.kakao.KakaoUrlBuilder;
import com.example.temp.user.service.oauth.kakao.OAuthKakaoAdapter;
import com.example.temp.user.service.oauth.response.OAuthResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class OAuthService {

    private final Map<PlatformType, OAuthFactory> adapterMap;

    public OAuthService(OAuthKakaoAdapter oAuthKakaoAdapter, KakaoUrlBuilder kakaoURLBuilder) {
        // 객체 생성 시점에 맵 구축
        this.adapterMap = new HashMap<>() {{
            put(PlatformType.KAKAO, OAuthFactory.builder()
                    .oAuthAdapter(oAuthKakaoAdapter)
                    .oAuthURLBuilder(kakaoURLBuilder)
                    .build());
        }};
    }

    public String loginPage(String platformType) {
        return adapterMap.get(PlatformType.fromName(platformType))
                .getOAuthURLBuilder()
                .authorize();
    }

    public OAuthResponse login(String platformType, String code) {
        OAuthFactory factory = getOAuthFactory(PlatformType.fromName(platformType));
        OAuthAdapter adapter = factory.getOAuthAdapter();
        log.info(">>>> {} Login Start", platformType);

        String accessToken = adapter.getToken(code);
        OAuthResponse profile = adapter.getProfile(accessToken);
        log.info(">>>> {} Login Success", platformType);

        return profile;
    }

    private OAuthFactory getOAuthFactory(PlatformType platformType) {
        return adapterMap.get(platformType);
    }
}