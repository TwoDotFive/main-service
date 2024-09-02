package com.example.temp.tour.domain;

import com.example.temp.common.entity.BaseTimeEntity;
import com.example.temp.common.exception.CustomException;
import com.example.temp.common.util.IdUtil;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TourScheduleMemo extends BaseTimeEntity {

    private static final int MAX_IMAGE_COUNT = 4;

    @Id
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tour_schedule_id")
    private TourSchedule tourSchedule;

    @Embedded
    private TourScheduleMemoContent content;

    @BatchSize(size = 30)
    @OneToMany(mappedBy = "memo", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<TourScheduleMemoImage> images = new ArrayList<>();

    public TourScheduleMemo(TourSchedule tourSchedule, String content) {
        this.id = IdUtil.create();
        this.tourSchedule = tourSchedule;
        this.content = new TourScheduleMemoContent(content);
    }

    public void addImage(TourScheduleMemoImage image) {
        images.add(image);
        verifyImageCountLimit();
    }

    public void updateImages(List<TourScheduleMemoImage> images) {
        this.images.clear();
        this.images.addAll(images);
        verifyImageCountLimit();
    }

    private void verifyImageCountLimit() {
        if (this.images.size() > MAX_IMAGE_COUNT) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "이미지 개수 초과 : 최대 %d개".formatted(MAX_IMAGE_COUNT));
        }
    }

    public String getContent() {
        return content.getValue();
    }
}
