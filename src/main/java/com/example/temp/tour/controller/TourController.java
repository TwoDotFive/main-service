package com.example.temp.tour.controller;

import com.example.temp.tour.dto.FindTourInformationByLocationCommand;
import com.example.temp.tour.dto.FindTourInformationDetailsCommand;
import com.example.temp.tour.dto.FindTourInformationDetailsResponse;
import com.example.temp.tour.dto.FindTourInformationResponse;
import com.example.temp.tour.service.FindTourInformationByLocationService;
import com.example.temp.tour.service.impl.FindTourDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tour")
public class TourController {

    private final FindTourInformationByLocationService findTourInformationByLocationService;
    private final FindTourDetailsServiceImpl findTourDetailsService;

    @GetMapping("/info")
    public ResponseEntity<FindTourInformationResponse> findTourInformation(
            @RequestParam(name = "pageSize") int pageSize,
            @RequestParam(name = "pageNumber") int pageNumber,
            @RequestParam(name = "x") String x,
            @RequestParam(name = "y") String y,
            @RequestParam(name = "radius") String radius,
            @RequestParam(name = "contentType") String contentTypeId
    ) {
        FindTourInformationByLocationCommand command = new FindTourInformationByLocationCommand(
                pageSize, pageNumber, x, y, radius, contentTypeId);
        FindTourInformationResponse response = findTourInformationByLocationService.doService(command);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/info/details")
    public ResponseEntity<FindTourInformationDetailsResponse> findDetails(
            @RequestParam(name = "contentTypeId") String contentTypeId,
            @RequestParam(name = "contentId") String contentId
    ) {
        FindTourInformationDetailsCommand command = new FindTourInformationDetailsCommand(contentId, contentTypeId);
        FindTourInformationDetailsResponse response = findTourDetailsService.doService(command);
        return ResponseEntity.ok(response);
    }
}
