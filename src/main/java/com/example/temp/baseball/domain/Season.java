package com.example.temp.baseball.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Season {
    @Id
    private Long id;
    private int year;
    private String name;
    private String representativeImageUrl;
}
