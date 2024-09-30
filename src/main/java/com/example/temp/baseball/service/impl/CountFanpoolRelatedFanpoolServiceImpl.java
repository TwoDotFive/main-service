package com.example.temp.baseball.service.impl;

import com.example.temp.baseball.service.CountFanpoolRelatedFanpoolService;
import com.example.temp.fanpool.domain.FanpoolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CountFanpoolRelatedFanpoolServiceImpl implements CountFanpoolRelatedFanpoolService {
    private final FanpoolRepository fanpoolRepository;

    @Override
    public long doService(long gameId) {
        return fanpoolRepository.countByGame(gameId);
    }
}
