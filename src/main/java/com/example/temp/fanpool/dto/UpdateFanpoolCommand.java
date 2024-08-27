package com.example.temp.fanpool.dto;

import lombok.Getter;

@Getter
public class UpdateFanpoolCommand {
    private long userId;
    private long fanpoolId;
    private UpdateFanpoolRequest body;

    public UpdateFanpoolCommand(long userId, long fanpoolId, UpdateFanpoolRequest body) {
        this.userId = userId;
        this.fanpoolId = fanpoolId;
        this.body = body;
    }
}
