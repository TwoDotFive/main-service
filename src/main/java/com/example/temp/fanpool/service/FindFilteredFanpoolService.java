package com.example.temp.fanpool.service;

import com.example.temp.fanpool.dto.FindFilteredFanpoolResponse;
import com.example.temp.fanpool.dto.FindFilteredFanpoolCommand;

public interface FindFilteredFanpoolService {
    FindFilteredFanpoolResponse doService(long userId, FindFilteredFanpoolCommand command);
}
