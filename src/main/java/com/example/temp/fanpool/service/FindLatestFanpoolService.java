package com.example.temp.fanpool.service;

import com.example.temp.fanpool.dto.FindLatestFanpoolResponse;

public interface FindLatestFanpoolService {
    FindLatestFanpoolResponse doService(long userId);
}
