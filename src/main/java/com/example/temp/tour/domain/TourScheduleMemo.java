package com.example.temp.tour.domain;

import com.example.temp.common.entity.BaseTimeEntity;
import com.example.temp.common.util.IdUtil;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TourScheduleMemo extends BaseTimeEntity {

    @Id
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tour_schedule_id")
    private TourSchedule tourSchedule;

    private String content;

    @OneToMany(mappedBy = "memo", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TourScheduleMemoImage> images = new ArrayList<>();

    public TourScheduleMemo(TourSchedule tourSchedule, String content) {
        this.id = IdUtil.create();
        this.tourSchedule = tourSchedule;
        this.content = content;
    }

    public void addImage(TourScheduleMemoImage image) {
        images.add(image);
    }
}