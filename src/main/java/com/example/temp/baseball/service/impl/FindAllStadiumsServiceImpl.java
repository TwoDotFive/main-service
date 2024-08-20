package com.example.temp.baseball.service.impl;

import com.example.temp.baseball.domain.StadiumRepository;
import com.example.temp.baseball.dto.StadiumView;
import com.example.temp.baseball.service.FindAllStadiumsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindAllStadiumsServiceImpl implements FindAllStadiumsService {

    private final StadiumRepository stadiumRepository;

    @Override
    @Transactional(readOnly = true)
    public List<StadiumView> doService() {
        return stadiumRepository.findAll()
                .stream()
                .map(StadiumView::of)
                .toList();
    }
}
