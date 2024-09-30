package com.example.temp.fanpool.service;

import com.example.temp.fanpool.dto.FindFanpoolBasedLocationResponse;

public interface FindFanpoolByIdService {
    FindFanpoolBasedLocationResponse doService(long id, long userId);
}
