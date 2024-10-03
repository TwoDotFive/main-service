package com.example.temp.tour.domain;

import com.example.temp.common.entity.BaseTimeEntity;
import com.example.temp.common.util.IdUtil;
import com.example.temp.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TourLog extends BaseTimeEntity implements Persistable<Long> {

    @Id
    private Long id;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @JoinColumn(name = "stadium_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private TourLogStadium stadium;

    @Embedded
    private TourLogTitle title;

    @Column(name = "image_url")
    private String image;

    @OneToMany(mappedBy = "tourLog", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<TourSchedule> schedules = new ArrayList<>();

    public TourLog(User user, TourLogStadium stadium, String title, String image) {
        this.id = IdUtil.create();
        this.user = user;
        this.stadium = stadium;
        this.title = new TourLogTitle(title);
        this.image = image;
    }

    public void addTourSchedule(TourSchedule tourSchedule) {
        schedules.add(tourSchedule);
    }

    public String getTitle() {
        return title.getValue();
    }

    public void updateTitle(String title) {
        this.title = new TourLogTitle(title);
    }

    public void updateImage(String image) {
        this.image = image;
    }

    public void updateTourSchedules(List<TourSchedule> schedules) {
        this.schedules.clear();
        this.schedules.addAll(schedules);
    }

    @Override
    public boolean isNew() {
        return getCreatedAt() == null;
    }
}
