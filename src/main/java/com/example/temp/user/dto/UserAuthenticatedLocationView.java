package com.example.temp.user.dto;

import com.example.temp.geo.dto.AddressInformationView;
import com.example.temp.user.domain.UserAuthenticatedLocation;

public record UserAuthenticatedLocationView(
        long id,
        boolean representative,
        AddressInformationView addressInformation
) {
    public UserAuthenticatedLocationView(UserAuthenticatedLocation location) {
        this(
                location.getId(),
                location.isRepresentative(),
                new AddressInformationView(location.getAddress())
        );
    }
}