package com.example.temp.fanpool.service.impl;

import com.example.temp.fanpool.domain.Fanpool;
import com.example.temp.fanpool.domain.FanpoolRepository;
import com.example.temp.fanpool.dto.FindFanpoolBasedLocationResponse;
import com.example.temp.fanpool.service.FindFanpoolByIdService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FindFanpoolByIdServiceImpl implements FindFanpoolByIdService {
    private final FanpoolRepository fanpoolRepository;

    @Override
    @Transactional(readOnly = true)
    public FindFanpoolBasedLocationResponse doService(long id) {
        Fanpool found = fanpoolRepository.findByIdOrElseThrow(id);
        return FindFanpoolBasedLocationResponse.build(found);
    }
}
