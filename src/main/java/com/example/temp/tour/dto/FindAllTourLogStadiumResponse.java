package com.example.temp.tour.dto;

import com.example.temp.tour.controller.dto.TourLogStadiumView;

import java.util.List;

public record FindAllTourLogStadiumResponse(List<TourLogStadiumView> stadiums) {
}
