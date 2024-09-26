package com.example.temp.tour.controller;

import com.example.temp.common.entity.CustomUserDetails;
import com.example.temp.tour.controller.dto.TourLogStadiumView;
import com.example.temp.tour.dto.*;
import com.example.temp.tour.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tour")
public class TourController {

    private final FindTourLogService findTourLogService;
    private final UpdateTourLogService updateTourLogService;
    private final DeleteTourLogService deleteTourLogService;
    private final RegisterTourLogService registerTourLogService;
    private final FindTourDetailsService findTourDetailsService;
    private final FindRecentTourLogListService findRecentTourLogListService;
    private final FindTourLogBookmarkIdService findTourLogBookmarkIdService;
    private final FindAllTourLogStadiumService findAllTourLogStadiumService;
    private final DeleteTourLogBookmarkService deleteTourLogBookmarkService;
    private final RegisterTourLogBookmarkService registerTourLogBookmarkService;
    private final FindTourInformationByLocationService findTourInformationByLocationService;
    private final FindUserBookmarkedTourLogListService findUserBookmarkedTourLogListService;
    private final FindRecentTourLogListByStadiumService findRecentTourLogListByStadiumService;


    @GetMapping("/info")
    public ResponseEntity<FindTourInformationResponse> findTourInformation(
            @RequestParam(name = "pageSize") int pageSize,
            @RequestParam(name = "pageNumber") int pageNumber,
            @RequestParam(name = "x") String x,
            @RequestParam(name = "y") String y,
            @RequestParam(name = "radius") String radius,
            @RequestParam(name = "contentTypeId", required = false) String contentTypeId
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

    @PostMapping("/log")
    public ResponseEntity<String> registerTourLog(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestBody RegisterTourLogRequest request
    ) {
        Long tourLogId = registerTourLogService.doService(userDetails.getId(), request);
        return ResponseEntity.ok(String.valueOf(tourLogId));
    }

    @GetMapping("/log")
    public ResponseEntity<TourLogView> findTourLog(@RequestParam(name = "id") Long tourLogId) {
        TourLogView response = findTourLogService.doService(tourLogId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/log")
    public ResponseEntity<Void> updateTourLog(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestBody UpdateTourLogRequest request
    ) {
        updateTourLogService.doService(userDetails.getId(), request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/log")
    public ResponseEntity<Void> deleteTourLog(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @RequestParam(name = "id") Long tourLogId
    ) {
        deleteTourLogService.doService(customUserDetails.getId(), tourLogId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/list")
    public ResponseEntity<FindRecentTourLogListResponse> findRecentTourLogList(
            @RequestParam(name = "stadiumId", required = false) Long stadiumId,
            @RequestParam(name = "lastId", defaultValue = "" + Long.MAX_VALUE) Long lastTourLogId,
            @RequestParam(name = "pageSize", defaultValue = "6") Integer pageSize
    ) {
        List<TourLogPreview> result;
        if (stadiumId == null) {
            result = findRecentTourLogListService.doService(lastTourLogId, pageSize);
        } else {
            result = findRecentTourLogListByStadiumService.doService(stadiumId, lastTourLogId, pageSize);
        }
        FindRecentTourLogListResponse response = new FindRecentTourLogListResponse(result);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/log/{tourLogId}/bookmark")
    public ResponseEntity<String> registerBookmark(
            @PathVariable("tourLogId") Long tourLogId,
            @AuthenticationPrincipal CustomUserDetails customUserDetails
    ) {
        Long bookmarkId = registerTourLogBookmarkService.doService(customUserDetails.getId(), tourLogId);
        return ResponseEntity.ok(String.valueOf(bookmarkId));
    }

    @DeleteMapping("/log/{tourLogId}/bookmark")
    public ResponseEntity<Void> deleteBookmark(
            @PathVariable("tourLogId") Long tourLogId,
            @AuthenticationPrincipal CustomUserDetails customUserDetails
    ) {
        deleteTourLogBookmarkService.doService(customUserDetails.getId(), tourLogId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/log/{tourLogId}/bookmark")
    public ResponseEntity<String> findBookmarkId(
            @PathVariable("tourLogId") Long tourLogId,
            @AuthenticationPrincipal CustomUserDetails customUserDetails
    ) {
        Long bookmarkId = findTourLogBookmarkIdService.doService(customUserDetails.getId(), tourLogId);
        return ResponseEntity.ok(String.valueOf(bookmarkId));
    }

    @GetMapping("/log/bookmark")
    public ResponseEntity<FindUserBookmarkedTourLogListResponse> findUserBookmarkedTourLogList(
            @RequestParam(name = "lastId", defaultValue = "" + Long.MAX_VALUE) Long lastBookmarkId,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        List<BookmarkedTourLogPreview> result = findUserBookmarkedTourLogListService.doService(
                userDetails.getId(), lastBookmarkId, pageSize);
        FindUserBookmarkedTourLogListResponse response = new FindUserBookmarkedTourLogListResponse(result);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/stadium/list")
    public ResponseEntity<FindAllTourLogStadiumResponse> findAllStadium() {
        List<TourLogStadiumView> stadiumViews = findAllTourLogStadiumService.doService();
        FindAllTourLogStadiumResponse response = new FindAllTourLogStadiumResponse(stadiumViews);
        return ResponseEntity.ok(response);
    }

}
