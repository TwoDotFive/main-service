package com.example.temp.fanpool.controller;

import com.example.temp.common.entity.CustomUserDetails;
import com.example.temp.fanpool.dto.CreateFanpoolRequest;
import com.example.temp.fanpool.dto.FanpoolInformationView;
import com.example.temp.fanpool.dto.FindFanpoolBasedLocationCommand;
import com.example.temp.fanpool.dto.FindFanpoolBasedLocationResponse;
import com.example.temp.fanpool.service.CreateFanpoolService;
import com.example.temp.fanpool.service.FindFanpoolBasedLocationService;
import com.example.temp.fanpool.service.FindFanpoolByIdService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/fanpool")
@RequiredArgsConstructor
public class FanpoolController {
    private final CreateFanpoolService createFanpoolService;
    private final FindFanpoolByIdService findFanpoolByIdService;
    private final FindFanpoolBasedLocationService findFanpoolBasedLocationService;

    @GetMapping
    public ResponseEntity<List<FindFanpoolBasedLocationResponse>> getAllFanpool(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @PageableDefault(size = 10, page = 0) Pageable pageable,
            @RequestParam("dongCd") String dongCd,
            @RequestParam("gameId") long gameId,
            @RequestParam("departAt") LocalDateTime departAt
    ) {
        // TODO 날짜, 경기, 위치, 팀 각각 필터링
        FindFanpoolBasedLocationCommand command = new FindFanpoolBasedLocationCommand(dongCd, gameId, departAt, pageable);
        List<FindFanpoolBasedLocationResponse> result = findFanpoolBasedLocationService.doService(customUserDetails.getId(), command);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{fanpoolId}")
    public ResponseEntity<FindFanpoolBasedLocationResponse> getFanpool(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @PathVariable("fanpoolId") long fanpoolId
    ) {
        FindFanpoolBasedLocationResponse result = findFanpoolByIdService.doService(fanpoolId);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<FanpoolInformationView> save(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestBody CreateFanpoolRequest request
    ) {
        FanpoolInformationView result = createFanpoolService.doService(userDetails.getId(), request);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
}
