package com.example.temp.baseball.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Team {
    @Id
    private Long id;
    private Long season;
    private String name;
    private String representativeImageUrl;
    // 구단 (enum으로 할지 DB에 저장할지)
}
