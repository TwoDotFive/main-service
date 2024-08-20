package com.example.temp.fanpool.service.impl;

import com.example.temp.fanpool.domain.Fanpool;
import com.example.temp.fanpool.domain.FanpoolRepository;
import com.example.temp.fanpool.dto.FindFanpoolBasedLocationResponse;
import com.example.temp.fanpool.service.FindFanpoolByIdService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindFanpoolByIdServiceImpl implements FindFanpoolByIdService {
    private final FanpoolRepository fanpoolRepository;

    @Override
    public FindFanpoolBasedLocationResponse doService(long id) {
        Fanpool found = fanpoolRepository.findByIdOrElseThrow(id);
        return FindFanpoolBasedLocationResponse.build(found);
    }
}
