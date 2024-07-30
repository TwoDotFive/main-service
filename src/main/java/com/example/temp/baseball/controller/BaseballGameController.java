package com.example.temp.baseball.controller;

import com.example.temp.baseball.dto.GameScheduleResponse;
import com.example.temp.baseball.dto.GameSchedulesRequest;
import com.example.temp.baseball.service.FindAllSchedulesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/baseball-game")
public class BaseballGameController {

    private final FindAllSchedulesService findAllSchedulesService;

    @GetMapping("/schedules")
    public ResponseEntity<List<GameScheduleResponse>> findAllSchedules(
            @RequestBody GameSchedulesRequest schedulesRequest
    ) {
        // user가 응원하는 팀의 스케줄을 보여줌
        List<GameScheduleResponse> result = findAllSchedulesService.doService(schedulesRequest);
        return ResponseEntity.ok(result);
    }
}
