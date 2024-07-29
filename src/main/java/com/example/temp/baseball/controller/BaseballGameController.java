package com.example.temp.baseball.controller;

import com.example.temp.baseball.service.FindAllSchedulesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/baseball-game")
public class BaseballGameController {

    private final FindAllSchedulesService findAllSchedulesService;

    @GetMapping("/schedules")
    public void findAllSchedules() {
        // user가 응원하는 팀의 스케줄을 보여줌
        findAllSchedulesService.doService(1L);
    }
}
