package com.example.temp.baseball.service.impl;

import com.example.temp.baseball.dto.GameScheduleResponse;
import com.example.temp.baseball.service.FindAllSchedulesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindAllSchedulesServiceImpl implements FindAllSchedulesService {
    @Override
    public List<GameScheduleResponse> doService(long teamId) {
        return List.of();
    }
}
