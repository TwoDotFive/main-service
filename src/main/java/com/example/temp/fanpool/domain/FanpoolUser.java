package com.example.temp.fanpool.domain;

import com.example.temp.common.util.IdUtil;
import com.example.temp.user.domain.User;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class FanpoolUser {
    @Id
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Fanpool fanpool;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public static FanpoolUser build(Fanpool fanpool, User user) {
        FanpoolUser ret = new FanpoolUser();
        ret.id = IdUtil.create();
        ret.fanpool = fanpool;
        ret.user = user;
        return ret;
    }
}
