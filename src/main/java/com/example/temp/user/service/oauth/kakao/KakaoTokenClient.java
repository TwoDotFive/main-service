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

    public static final String GRANT_TYPE = "grant_type";
    public static final String AUTHORIZATION_CODE = "authorization_code";
    public static final String CLIENT_ID = "client_id";
    public static final String REDIRECT_URI = "redirect_uri";
    public static final String CODE = "code";

    private final KakaoUrlBuilder kakaoUrlBuilder;

    public String getAccessToken(String code) {
        KakaoTokenResponse kakaoTokenResponse = WebClient.create(kakaoUrlBuilder.getTokenUri())
                .post()
                .uri(uriBuilder -> uriBuilder
                        .queryParam(GRANT_TYPE, AUTHORIZATION_CODE)
                        .queryParam(CLIENT_ID, kakaoUrlBuilder.getClientId())
                        .queryParam(REDIRECT_URI, kakaoUrlBuilder.getRedirectUri())
                        .queryParam(CODE, code)
                        .build(true))
                .header(HttpHeaders.CONTENT_TYPE, HttpHeaderValues.APPLICATION_X_WWW_FORM_URLENCODED.toString())
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse ->
                        Mono.error(new CustomException(HttpStatus.BAD_REQUEST, "Invalid Parameter")))
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse ->
                        Mono.error(new CustomException(HttpStatus.BAD_GATEWAY, "Internal Server Error")))
                .bodyToMono(KakaoTokenResponse.class)
                .block();

        return kakaoTokenResponse.getAccessToken();
    }

}
