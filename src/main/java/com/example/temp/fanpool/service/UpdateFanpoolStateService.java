package com.example.temp.fanpool.service;

import com.example.temp.fanpool.dto.command.UpdateFanpoolStateCommand;

public interface UpdateFanpoolStateService {
    void doService(UpdateFanpoolStateCommand command);
}
