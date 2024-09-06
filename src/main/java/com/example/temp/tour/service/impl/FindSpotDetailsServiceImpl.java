package com.example.temp.tour.service.impl;

import com.example.temp.tour.dto.FindSpotDetailsResponse;
import com.example.temp.tour.dto.FindTourInformationDetailsCommand;
import com.example.temp.tour.dto.FindTourInformationDetailsResponse;
import com.example.temp.tour.service.FindTourInformationDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class FindSpotDetailsServiceImpl implements FindTourInformationDetailsService {
    private final TourDetailsClients tourDetailsClients;

    @Override
    @Transactional(readOnly = true)
    public FindTourInformationDetailsResponse doService(FindTourInformationDetailsCommand command) {
        WebClient.ResponseSpec responseSpec = tourDetailsClients.doService(command);

        FindSpotDetailsResponse result = responseSpec.bodyToMono(FindSpotDetailsResponse.class).block();
        return result.trans();
    }
}
