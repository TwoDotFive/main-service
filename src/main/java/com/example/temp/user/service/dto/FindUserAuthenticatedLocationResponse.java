package com.example.temp.user.service.dto;

import com.example.temp.user.dto.UserAuthenticatedLocationView;

import java.util.List;

public record FindUserAuthenticatedLocationResponse(
        List<UserAuthenticatedLocationView> authenticatedLocations
) {
}
