package com.example.temp.tour.domain;

import com.example.temp.common.entity.BaseTimeEntity;
import com.example.temp.common.util.IdUtil;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TourLogBookmark extends BaseTimeEntity implements Persistable<Long> {

    @Id
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long tourLogId;

    public TourLogBookmark(Long userId, Long tourLogId) {
        this.id = IdUtil.create();
        this.userId = userId;
        this.tourLogId = tourLogId;
    }

    @Override
    public boolean isNew() {
        return this.getCreatedAt() == null;
    }
}
