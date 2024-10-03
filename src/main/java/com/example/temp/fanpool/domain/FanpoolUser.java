package com.example.temp.fanpool.domain;

import com.example.temp.common.util.IdUtil;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class FanpoolUser {
    @Id
    private Long id;

    private Long fanpoolId;

    private Long userId;

    public static FanpoolUser build(Long fanpoolId, Long userId) {
        FanpoolUser ret = new FanpoolUser();
        ret.id = IdUtil.create();
        ret.fanpoolId = fanpoolId;
        ret.userId = userId;
        return ret;
    }
}
