package com.example.temp.user.service.oauth.kakao;


import com.example.temp.common.exception.CustomException;
import com.example.temp.user.service.oauth.response.KakaoProfileResponse;
import io.netty.handler.codec.http.HttpHeaderValues;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class KakaoProfileClient {

    private final KakaoUrlBuilder kakaoUrlBuilder;

    public KakaoProfileResponse getProfile(String accessToken) {

        return WebClient.create(kakaoUrlBuilder.getProfileUri())
                .post()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .header(HttpHeaders.CONTENT_TYPE, HttpHeaderValues.APPLICATION_X_WWW_FORM_URLENCODED.toString())
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse ->
                        Mono.error(new CustomException(HttpStatus.BAD_REQUEST, "잘못된 접근입니다.")))
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse ->
                        Mono.error(new CustomException(HttpStatus.BAD_GATEWAY, "Internal Server Error")))
                .bodyToMono(KakaoProfileResponse.class)
                .block();
    }
}

