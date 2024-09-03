package com.example.temp.fanpool.service;

import com.example.temp.fanpool.dto.CreateFanpoolRequest;
import com.example.temp.fanpool.dto.FanpoolView;

public interface CreateFanpoolService {
    FanpoolView doService(long userId, CreateFanpoolRequest request);
}
