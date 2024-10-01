package com.example.temp.fanpool.domain;

import com.example.temp.baseball.domain.Game;
import com.example.temp.common.entity.BaseTimeEntity;
import com.example.temp.common.util.IdUtil;
import com.example.temp.fanpool.domain.value.FanpoolState;
import com.example.temp.fanpool.domain.value.FanpoolType;
import com.example.temp.fanpool.domain.value.GenderConstraint;
import com.example.temp.fanpool.dto.CreateFanpoolRequest;
import com.example.temp.fanpool.dto.UpdateFanpoolRequest;
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

    @ManyToOne(fetch = FetchType.LAZY)
    private Address departFrom;

    private String title;
    private LocalDateTime departAt;
    private int numberOfPeople;
    private int currentNumberOfPeople;
    private String memo;

    @Enumerated(EnumType.STRING)
    private FanpoolType fanpoolType;

    @Enumerated(EnumType.STRING)
    private GenderConstraint genderConstraint;

    @Enumerated(EnumType.STRING)
    private FanpoolState state;

    public static Fanpool build(User hostUser, Game game, Address address, CreateFanpoolRequest request) {
        Fanpool ret = new Fanpool();
        ret.id = IdUtil.create();
        ret.game = game;
        ret.title = request.getTitle();
        ret.hostUser = hostUser;
        ret.departFrom = address;
        ret.departAt = request.getDepartAt();
        ret.numberOfPeople = request.getNumberOfPeople();
        ret.currentNumberOfPeople = 0;
        ret.fanpoolType = request.getFanpoolType();
        ret.genderConstraint = request.getGenderConstraint();
        ret.memo = request.getMemo();
        ret.state = FanpoolState.GATHER;
        return ret;
    }

    public void updateInfo(UpdateFanpoolRequest req) {
        title = req.getTitle();
        departAt = req.getDepartAt();
        numberOfPeople = req.getNumberOfPeople();
        fanpoolType = FanpoolType.valueOf(req.getFanpoolType().toUpperCase());
        genderConstraint = GenderConstraint.valueOf(req.getGenderConstraint().toUpperCase());
        memo = req.getMemo();
        departFrom = req.getDepartFrom().toEntity();
        currentNumberOfPeople = req.getCurrentOfPeople();
    }

    public void updateGame(Game game) {
        this.game = game;
    }

    public boolean isNotHostUser(long userId) {
        return hostUser.getId() != userId;
    }

    public void updateState(String state) {
        this.state = FanpoolState.valueOf(state.toUpperCase());
    }

    public boolean alreadyFull() {
        return currentNumberOfPeople == numberOfPeople;
    }
}
