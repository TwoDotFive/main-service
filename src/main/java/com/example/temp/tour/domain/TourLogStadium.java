package com.example.temp.tour.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TourLogStadium {

    @Id
    private Long id;

    private String address;

    private String contentId;

    private String contentTypeId;

    private String name;

    private String shortenName;

    private String image;

    private String x;

    private String y;
}
