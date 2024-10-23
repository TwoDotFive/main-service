package com.example.temp.fanpool.controller;

import com.example.temp.fanpool.service.FindTeamNamesOfFanpoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/fanpool")
public class AdminFanpoolController {

    private final FindTeamNamesOfFanpoolService findTeamNamesOfFanpoolService;

    @GetMapping("/{fanpoolId}/teams")
    public ResponseEntity<String> findGameTeamsOfFanpool(@PathVariable("fanpoolId") long fanpoolId) {
        String response = findTeamNamesOfFanpoolService.doService(fanpoolId);
        return ResponseEntity.ok(response);
    }
}
