package com.example.temp.baseball.domain.value;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Getter
public enum GameState {
    BEFORE_START("경기 전"),
    IN_PROGRESS("경기중"),
    TERMINATED("경기종료"),
    CANCELED("취소"),
    CANCELED_RAIN_OUT("우천시 취소");

    private final String kor;

    public static GameState determineState(LocalDateTime startAt) {
        LocalDateTime now = LocalDateTime.now();

        // 평균 야구 경기 시간을 3시간 10분 Duration으로 정의
        Duration gameDuration = Duration.ofHours(3).plusMinutes(10);

        if (now.isBefore(startAt)) {
            return GameState.BEFORE_START;
        }
        if (now.isAfter(startAt) && now.isBefore(startAt.plus(gameDuration))) {
            return GameState.IN_PROGRESS;
        }
        return GameState.TERMINATED;
    }
}
