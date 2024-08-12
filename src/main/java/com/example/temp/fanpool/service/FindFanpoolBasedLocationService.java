package com.example.temp.fanpool.service;

import com.example.temp.fanpool.dto.FindFanpoolBasedLocationCommand;
import com.example.temp.fanpool.dto.FindFanpoolBasedLocationResponse;

import java.util.List;

public interface FindFanpoolBasedLocationService {
    List<FindFanpoolBasedLocationResponse> doService(long userId, FindFanpoolBasedLocationCommand command);
}
