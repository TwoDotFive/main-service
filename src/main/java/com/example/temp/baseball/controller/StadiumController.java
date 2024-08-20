package com.example.temp.baseball.controller;

import com.example.temp.baseball.dto.FindStadiumListResponse;
import com.example.temp.baseball.dto.StadiumView;
import com.example.temp.baseball.service.FindAllStadiumsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/baseball/stadium")
public class StadiumController {

    private final FindAllStadiumsService findAllStadiumsService;

    @GetMapping("/list")
    public ResponseEntity<FindStadiumListResponse> findStadiumList() {
        List<StadiumView> stadiumViews = findAllStadiumsService.doService();
        FindStadiumListResponse response = new FindStadiumListResponse(stadiumViews);
        return ResponseEntity.ok(response);
    }

}
