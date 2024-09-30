package com.example.temp.baseball.controller;

import com.example.temp.baseball.dto.FindGamesByTeamCommand;
import com.example.temp.baseball.dto.FindGamesByTeamResponse;
import com.example.temp.baseball.dto.GameScheduleResponse;
import com.example.temp.baseball.dto.GameSchedulesRequest;
import com.example.temp.baseball.service.CountFanpoolRelatedFanpoolService;
import com.example.temp.baseball.service.CreateGameSchedulesService;
import com.example.temp.baseball.service.FindAllSchedulesDuringThisWeekService;
import com.example.temp.baseball.service.FindGamesByTeamService;
import com.example.temp.common.dto.IdResponse;
import com.example.temp.common.entity.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
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
    private final CountFanpoolRelatedFanpoolService countFanpoolRelatedFanpoolService;

    @GetMapping
    public ResponseEntity<FindGamesByTeamResponse> findByTeam(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @RequestParam(value = "teamId", required = false, defaultValue = "0") Long teamId,

            @RequestParam(value = "startDate", required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDateTime startDate,

            @RequestParam(value = "endDate", required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDateTime endDate
    ) {
        FindGamesByTeamCommand command = new FindGamesByTeamCommand(teamId, startDate, endDate);
        FindGamesByTeamResponse result = findGamesByTeamService.doService(command);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{gameId}")
    public ResponseEntity<IdResponse> countFanpool(
            @PathVariable(value = "gameId") String gameId
    ) {
        long result = countFanpoolRelatedFanpoolService.doService(Long.parseLong(gameId));
        return ResponseEntity.ok(IdResponse.build(result));
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
