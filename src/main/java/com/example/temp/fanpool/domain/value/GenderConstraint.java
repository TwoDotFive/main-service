package com.example.temp.fanpool.domain.value;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum GenderConstraint {
    ANY("성별 무관"),
    FEMALE_ONLY("여자만"),
    MALE_ONLY("남자만");

    private final String kor;
}
