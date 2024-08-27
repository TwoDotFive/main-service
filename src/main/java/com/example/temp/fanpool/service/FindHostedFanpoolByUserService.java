package com.example.temp.fanpool.service;

import com.example.temp.fanpool.dto.FindHostedFanpoolByUserCommand;
import com.example.temp.fanpool.dto.FindHostedFanpoolByUserResponse;

public interface FindHostedFanpoolByUserService {
    FindHostedFanpoolByUserResponse doService(FindHostedFanpoolByUserCommand command);
}
