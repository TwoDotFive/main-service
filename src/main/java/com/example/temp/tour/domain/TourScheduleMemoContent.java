package com.example.temp.tour.domain;

import com.example.temp.common.exception.CustomException;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TourScheduleMemoContent {

    private static final int MAX_LENGTH = 1000;

    private String content;

    public TourScheduleMemoContent(String content) {
        if (content.length() > MAX_LENGTH) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "메모 길이 초과 : 최대 %d자".formatted(MAX_LENGTH));
        }
        this.content = content;
    }

    public String getValue() {
        return content;
    }
}
