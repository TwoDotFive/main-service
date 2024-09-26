package com.example.temp.tour.service.impl;

import com.example.temp.tour.controller.dto.TourLogStadiumView;
import com.example.temp.tour.domain.TourLogStadiumRepository;
import com.example.temp.tour.service.FindAllTourLogStadiumService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindAllTourLogStadiumServiceImpl implements FindAllTourLogStadiumService {

    private final TourLogStadiumRepository tourLogStadiumRepository;

    @Override
    public List<TourLogStadiumView> doService() {
        return tourLogStadiumRepository.findAll()
                .stream()
                .map(TourLogStadiumView::of)
                .toList();
    }
}
