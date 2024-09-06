package com.example.temp.tour.service.impl;

import com.example.temp.tour.dto.FindRestaurantDetailsResponse;
import com.example.temp.tour.dto.FindTourInformationDetailsCommand;
import com.example.temp.tour.dto.FindTourInformationDetailsResponse;
import com.example.temp.tour.service.FindTourInformationDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class FindRestaurantDetailsServiceImpl implements FindTourInformationDetailsService {
    private final TourDetailsClients tourDetailsClients;

    @Override
    public FindTourInformationDetailsResponse doService(FindTourInformationDetailsCommand command) {
        WebClient.ResponseSpec responseSpec = tourDetailsClients.doService(command);

        FindRestaurantDetailsResponse result = responseSpec.bodyToMono(FindRestaurantDetailsResponse.class).block();
        return result.trans();
    }
}
