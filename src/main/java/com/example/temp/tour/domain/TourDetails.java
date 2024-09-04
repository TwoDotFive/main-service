package com.example.temp.tour.domain;


import com.example.temp.common.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public enum TourDetails {
    // 12:관광지, 14:문화관광, 25:여행코스, 28:레포츠, 32:숙박, 38:쇼핑, 39:음식점
    // Spot, Course, LeisureSports, Accommodations, Shopping, Restaurant
    SPOT("12"),
    CULTURE("14"),
    COURSE("25"),
    LEISURE_SPORTS("28"),
    ACCOMMODATIONS("32"),
    SHOPPING("38"),
    RESTAURANT("39");

    private final String contentTypeId;

    private static final Map<String, TourDetails> CODE_TO_DETAIL_MAP;

    static {
        Map<String, TourDetails> map = new HashMap<>();
        for (TourDetails detail : values()) {
            map.put(detail.contentTypeId, detail);
        }
        CODE_TO_DETAIL_MAP = Collections.unmodifiableMap(map);
    }

    public static TourDetails fromCode(String contentTypeId) {
        TourDetails detail = CODE_TO_DETAIL_MAP.get(contentTypeId);
        if (detail == null) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "잘못된 contentTypeId " + contentTypeId);
        }
        return detail;
    }
}
