package com.example.temp.fanpool.service.impl;

import com.example.temp.fanpool.domain.FanpoolRepository;
import com.example.temp.fanpool.service.FindTeamNamesOfFanpoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FindTeamNamesOfFanpoolServiceImpl implements FindTeamNamesOfFanpoolService {

    private final FanpoolRepository fanpoolRepository;

    @Override
    @Transactional(readOnly = true)
    public String doService(long fanpoolId) {
        return fanpoolRepository.findTeamNamesById(fanpoolId);
    }
}
