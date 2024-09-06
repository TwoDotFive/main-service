package com.example.temp.tour.domain;


import com.example.temp.common.exception.CustomException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TourLogTitle {

    private static final int MAX_LENGTH = 35;

    @Column(name = "title")
    private String title;

    public TourLogTitle(String title) {
        if (title.length() > MAX_LENGTH) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "투어 로그 제목 길이 초과 : 최대 %d자".formatted(MAX_LENGTH));
        }
        this.title = title;
    }

    public String getValue() {
        return title;
    }
}
