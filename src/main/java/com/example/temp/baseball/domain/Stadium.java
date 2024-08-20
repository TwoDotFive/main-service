package com.example.temp.baseball.domain;

import com.example.temp.geo.entity.Address;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Stadium {

    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Address address;

    // 경기장 줄임말 (예시: 잠실종합운동장잠실야구장 -> 잠실)
    @Column(nullable = false)
    private String shortenName;

    public String getStadiumName() {
        return address.getBuildName();
    }
}
