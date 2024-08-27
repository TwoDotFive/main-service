package com.example.temp.fanpool.service;

import com.example.temp.fanpool.dto.FindFilteredFanpoolResponse;
import com.example.temp.fanpool.dto.command.FindFilteredFanpoolCommand;

public interface FindFilteredFanpoolService {
    FindFilteredFanpoolResponse doService(FindFilteredFanpoolCommand command);
}
