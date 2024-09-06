package com.example.temp.tour.domain;

import com.example.temp.common.entity.BaseTimeEntity;
import com.example.temp.common.util.IdUtil;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TourSchedule extends BaseTimeEntity {

    @Id
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Integer day;

    @Column(nullable = false)
    private Integer sequence; // 방문 순서

    @JoinColumn(name = "tour_log_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private TourLog tourLog;

    @JoinColumn(name = "tour_place_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private TourPlace tourPlace;

    @Setter
    @OneToOne(mappedBy = "tourSchedule", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    private TourScheduleMemo memo;

    public TourSchedule(Long userId, TourLog tourLog, Integer day, Integer sequence, TourPlace tourPlace) {
        this.id = IdUtil.create();
        this.userId = userId;
        this.tourLog = tourLog;
        this.day = day;
        this.sequence = sequence;
        this.tourPlace = tourPlace;
    }
}
