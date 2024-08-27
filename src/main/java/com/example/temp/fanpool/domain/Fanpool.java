package com.example.temp.fanpool.domain;

import com.example.temp.baseball.domain.Game;
import com.example.temp.common.entity.BaseTimeEntity;
import com.example.temp.common.util.IdUtil;
import com.example.temp.fanpool.domain.value.FanpoolType;
import com.example.temp.fanpool.domain.value.GenderConstraint;
import com.example.temp.fanpool.dto.CreateFanpoolRequest;
import com.example.temp.geo.entity.Address;
import com.example.temp.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Fanpool extends BaseTimeEntity {
    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User hostUser;

    @ManyToOne(fetch = FetchType.LAZY)
    private Game game;

    private String title;
    @ManyToOne(fetch = FetchType.LAZY)
    private Address departFrom;
    private LocalDateTime departAt;
    private Integer numberOfPeople;
    private Integer currentNumberOfPeople;

    @Enumerated(EnumType.STRING)
    private FanpoolType fanpoolType;
    @Enumerated(EnumType.STRING)
    private GenderConstraint genderConstraint;
    private String memo;

    public static Fanpool build(User hostUser, Game game, Address address, CreateFanpoolRequest request) {
        Fanpool ret = new Fanpool();
        ret.id = IdUtil.create();
        ret.game = game;
        ret.title = request.getTitle();
        ret.hostUser = hostUser;
        ret.departFrom = address;
        ret.departAt = request.getDepartAt();
        ret.numberOfPeople = request.getNumberOfPeople();
        ret.currentNumberOfPeople = 1;
        ret.fanpoolType = request.getFanpoolType();
        ret.genderConstraint = request.getGenderConstraint();
        ret.memo = request.getMemo();
        return ret;
    }
}
