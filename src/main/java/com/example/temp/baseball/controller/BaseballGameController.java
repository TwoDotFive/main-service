package com.example.temp.baseball.controller;

import com.example.temp.baseball.dto.*;
import com.example.temp.baseball.service.CreateGameSchedulesService;
import com.example.temp.baseball.service.FindAllSchedulesDuringThisWeekService;
import com.example.temp.baseball.service.FindGamesByTeamService;
import com.example.temp.common.entity.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/baseball/game")
public class BaseballGameController {
    private final FindGamesByTeamService findGamesByTeamService;
    private final FindAllSchedulesDuringThisWeekService findAllSchedulesDuringThisWeekService;
    private final CreateGameSchedulesService createGameSchedulesService;

    @GetMapping
    public ResponseEntity<FindGamesByTeamResponse> findByTeam(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @RequestParam(value = "teamId") long teamId,
            @RequestParam(value = "startDate", required = false) LocalDateTime startDate,
            @RequestParam(value = "endDate", required = false) LocalDateTime endDate
    ) {
        FindGamesByTeamResponse result = findGamesByTeamService.doService(new FindGamesByTeamCommand(teamId, startDate, endDate));
        return ResponseEntity.ok(result);
    }

    @GetMapping("/schedules")
    public ResponseEntity<GameScheduleResponse> findAllSchedules(
            @AuthenticationPrincipal CustomUserDetails authenticatedUser,
            @RequestParam(name = "year", required = true) int year
    ) {
        GameScheduleResponse result = findAllSchedulesDuringThisWeekService.doService(authenticatedUser.getFavoriteTeam(), year);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/schedules")
    public ResponseEntity<Void> save(
            @AuthenticationPrincipal CustomUserDetails authenticatedUser,
            @RequestBody List<GameSchedulesRequest> request
    ) {
        createGameSchedulesService.doService(authenticatedUser.isAdmin(), request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
