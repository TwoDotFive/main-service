package com.example.temp.tour.service.impl;

import com.example.temp.common.exception.CustomException;
import com.example.temp.tour.dto.FindTourInformationDetailsCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class TourDetailsClients {
    private static final String NUM_OF_ROWS_PARAM = "numOfRows";
    private static final int NUM_OF_ROWS_PARAM_VALUE = 10;
    private static final String PAGE_NO_PARAM = "pageNo";
    private static final int PAGE_NO_PARAM_VALUE = 0;
    private static final String MOBILE_OS_PARAM = "MobileOS"; // required
    private static final String MOBILE_OS_PARAM_VALUE = "WEB"; // required
    private static final String MOBILE_APP_PARAM = "MobileApp"; // required
    private static final String MOBILE_APP_PARAM_VALUE = "fanpool"; // required
    private static final String CONTENT_TYPE_ID_PARAM = "contentTypeId";
    private static final String CONTENT_ID_PARAM = "contentId";

    private static final String TYPE_PARAM = "_type";
    private static final String TYPE_PARAM_VALUE = "json";
    private static final String SERVICE_KEY_PARAM = "serviceKey"; //required

    @Value("${tour.knto.rest-api-key}")
    private String restApiKey;
    @Value("${tour.knto.info-kor.base-url}")
    private String baseUrl;
    @Value("${tour.knto.info-kor.details-uri}")
    private String detailsUri;

    public WebClient.ResponseSpec doService(FindTourInformationDetailsCommand command) {
        URI uri = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .path(detailsUri)
                .queryParam(SERVICE_KEY_PARAM, restApiKey)
                .queryParam(MOBILE_OS_PARAM, MOBILE_OS_PARAM_VALUE)
                .queryParam(MOBILE_APP_PARAM, MOBILE_APP_PARAM_VALUE)
                .queryParam(TYPE_PARAM, TYPE_PARAM_VALUE)
                .queryParam(NUM_OF_ROWS_PARAM, NUM_OF_ROWS_PARAM_VALUE)
                .queryParam(PAGE_NO_PARAM, PAGE_NO_PARAM_VALUE)
                .queryParam(CONTENT_ID_PARAM, command.contentId())
                .queryParam(CONTENT_TYPE_ID_PARAM, command.contentTypeId())
                .build(true)
                .toUri();

        return getBlock(uri);
    }

    private WebClient.ResponseSpec getBlock(URI uri) {
        return WebClient.create().get()
                .uri(uri)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse ->
                        Mono.error(new CustomException(HttpStatus.BAD_REQUEST, "Invalid Parameter")))
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse ->
                        Mono.error(new CustomException(HttpStatus.BAD_GATEWAY, "Internal Server Error")));
    }
}
