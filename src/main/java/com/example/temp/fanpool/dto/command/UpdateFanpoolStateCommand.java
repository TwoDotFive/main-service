package com.example.temp.fanpool.dto.command;

import com.example.temp.fanpool.dto.UpdateFanpoolStateRequest;
import lombok.Getter;

@Getter
public class UpdateFanpoolStateCommand {
    private long fanpoolId;
    private long userId;
    private UpdateFanpoolStateRequest body;

    public UpdateFanpoolStateCommand(long fanpoolId, long userId, UpdateFanpoolStateRequest body) {
        this.fanpoolId = fanpoolId;
        this.userId = userId;
        this.body = body;
    }
}
