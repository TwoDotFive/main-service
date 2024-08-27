package com.example.temp.fanpool.dto;

import lombok.Getter;

@Getter
public class UpdateFanpoolStateCommand {
    private long fanpoolId;
    private long userId;
    private UpdateFanpoolStateRequest body;
}
