package com.example.temp.tour.domain;

import com.example.temp.common.entity.BaseTimeEntity;
import com.example.temp.common.util.IdUtil;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TourPlace extends BaseTimeEntity {

    @Id
    private Long id;

    private Integer contentId;

    private Integer contentTypeId;

    private String name;

    private String address;

    private Double x;

    private Double y;

    private String thumbnail;

    @Builder
    public TourPlace(Integer contentId, Integer contentTypeId, String name, String address, Double x, Double y, String thumbnail) {
        this.id = IdUtil.create();
        this.contentId = contentId;
        this.contentTypeId = contentTypeId;
        this.name = name;
        this.address = address;
        this.x = x;
        this.y = y;
        this.thumbnail = thumbnail;
    }
}
