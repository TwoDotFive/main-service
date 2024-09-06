package com.example.temp.tour.service.impl;

import com.example.temp.tour.domain.TourDetails;
import com.example.temp.tour.service.FindTourInformationDetailsService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.EnumMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FindTourDetailsServiceFactory {
    private static final Map<TourDetails, FindTourInformationDetailsService> services = new EnumMap<>(TourDetails.class);

    private final FindAccommodationDetailsServiceImpl accommodationService;
    private final FindCultureDetailsServiceImpl cultureService;
    private final FindSpotDetailsServiceImpl spotService;
    private final FindShoppingDetailsServiceImpl shoppingService;
    private final FindRestaurantDetailsServiceImpl restaurantService;

    @PostConstruct
    public void init() {
        services.put(TourDetails.ACCOMMODATIONS, accommodationService);
        services.put(TourDetails.CULTURE, cultureService);
        services.put(TourDetails.SPOT, spotService);
        services.put(TourDetails.SHOPPING, shoppingService);
        services.put(TourDetails.RESTAURANT, restaurantService);
    }

    FindTourInformationDetailsService get(String contentTypeId) {
        return services.get(TourDetails.fromCode(contentTypeId));
    }
}
