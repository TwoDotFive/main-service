package com.example.temp.baseball.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Season {
    @Id
    private Long id;
    private int year;
    private String name;
    private String representativeImageUrl;
}
