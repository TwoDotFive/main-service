package com.example.temp.tour.domain;

import com.example.temp.common.entity.BaseTimeEntity;
import com.example.temp.common.util.IdUtil;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Getter
@SQLRestriction("deleted = false")
@SQLDelete(sql = "UPDATE tour_log_place_memo_image SET deleted = true WHERE id = ?")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TourScheduleMemoImage extends BaseTimeEntity {

    @Id
    private Long id;

    @JoinColumn(name = "memo_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private TourScheduleMemo memo;

    @Column(nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private Integer sequence;

    private Boolean deleted;

    public TourScheduleMemoImage(TourScheduleMemo memo, String imageUrl, Integer sequence) {
        this.id = IdUtil.create();
        this.memo = memo;
        this.imageUrl = imageUrl;
        this.sequence = sequence;
        this.deleted = false;
    }
}
