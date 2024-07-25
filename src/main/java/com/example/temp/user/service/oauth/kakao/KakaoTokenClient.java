package com.example.temp.user.service.oauth.kakao;

import com.example.temp.common.exception.CustomException;
import com.example.temp.user.service.oauth.response.KakaoTokenResponse;
import io.netty.handler.codec.http.HttpHeaderValues;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class KakaoTokenClient {

    private final KakaoUrlBuilder kakaoUrlBuilder;

    public String getAccessToken(String code) {
        KakaoTokenResponse kakaoTokenResponse = WebClient.create(kakaoUrlBuilder.getTokenUri())
                .post()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("grant_type", "authorization_code")
                        .queryParam("client_id", kakaoUrlBuilder.getClientId())
                        .queryParam("redirect_uri", kakaoUrlBuilder.getRedirectUri())
                        .queryParam("code", code)
                        .build(true))
                .header(HttpHeaders.CONTENT_TYPE, HttpHeaderValues.APPLICATION_X_WWW_FORM_URLENCODED.toString())
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse ->
                        Mono.error(new CustomException(HttpStatus.BAD_GATEWAY, "Invalid Parameter")))
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse ->
                        Mono.error(new CustomException(HttpStatus.BAD_GATEWAY, "Internal Server Error")))
                .bodyToMono(KakaoTokenResponse.class)
                .block();

        return kakaoTokenResponse.getAccessToken();
    }

}
