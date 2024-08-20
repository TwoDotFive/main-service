package com.example.temp.tour.service;

import com.example.temp.common.exception.CustomException;
import com.example.temp.tour.dto.FindTourInformationByLocationCommand;
import com.example.temp.tour.dto.FindTourInformationByLocationHttpResponse;
import com.example.temp.tour.dto.FindTourInformationResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;

@Service
public class FindTourInformationByLocationServiceImpl implements FindTourInformationByLocationService {

    private static final String NUM_OF_ROWS_PARAM = "numOfRows";
    private static final String PAGE_NO_PARAM = "pageNo";
    private static final String MOBILE_OS_PARAM = "MobileOS"; // required
    private static final String MOBILE_OS_PARAM_VALUE = "WEB"; // required
    private static final String MOBILE_APP_PARAM = "MobileApp"; // required
    private static final String MOBILE_APP_PARAM_VALUE = "fanpool"; // required
    private static final String TYPE_PARAM = "_type";
    private static final String TYPE_PARAM_VALUE = "json";
    private static final String LIST_YN_PARAM = "listYN";
    private static final String LIST_YN_PARAM_VALUE_Y = "Y";
    private static final String LIST_YN_PARAM_VALUE_N = "N";
    // 정렬구분 (A=제목순, C=수정일순, D=생성일순) 대표이미지가반드시있는정렬(O=제목순, Q=수정일순, R=생성일순)
    private static final String ARRANGE_PARAM = "arrange";
    private static final String MAP_X_PARAM = "mapX"; // required
    private static final String MAP_Y_PARAM = "mapY"; //required
    private static final String RADIUS_PARAM = "radius"; //required
    private static final String CONTENT_TYPE_ID_PARAM = "contentTypeId";
    private static final String MODIFIED_TIME_PARAM = "modifiedtime";
    private static final String SERVICE_KEY_PARAM = "serviceKey"; //required

    @Value("${tour.knto.rest-api-key}")
    private String restApiKey;
    @Value("${tour.knto.info-kor.base-url}")
    private String baseUrl;
    @Value("${tour.knto.info-kor.location-based-list-uri}")
    private String locationBasedListUri;

    public FindTourInformationResult doService(FindTourInformationByLocationCommand command) {
        URI uri = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .path(locationBasedListUri)
                .queryParam(SERVICE_KEY_PARAM, restApiKey)
                .queryParam(MOBILE_OS_PARAM, MOBILE_OS_PARAM_VALUE)
                .queryParam(MOBILE_APP_PARAM, MOBILE_APP_PARAM_VALUE)
                .queryParam(MAP_X_PARAM, command.x())
                .queryParam(MAP_Y_PARAM, command.y())
                .queryParam(RADIUS_PARAM, command.radius()) // 여기까지 필수
                .queryParam(NUM_OF_ROWS_PARAM, command.numOfRows())
                .queryParam(PAGE_NO_PARAM, command.pageNo())
                .queryParam(CONTENT_TYPE_ID_PARAM, command.contentTypeId())
                .queryParam(TYPE_PARAM, TYPE_PARAM_VALUE)
                .build(true)
                .toUri();

        FindTourInformationByLocationHttpResponse response = getBlock(uri);
        return FindTourInformationResult.of(response);
    }

    private FindTourInformationByLocationHttpResponse getBlock(URI uri) {
        return WebClient.create().get()
                .uri(uri)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse ->
                        Mono.error(new CustomException(HttpStatus.BAD_REQUEST, "Invalid Parameter")))
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse ->
                        Mono.error(new CustomException(HttpStatus.BAD_GATEWAY, "Internal Server Error")))
                .bodyToMono(FindTourInformationByLocationHttpResponse.class)
                .block();
    }
}
