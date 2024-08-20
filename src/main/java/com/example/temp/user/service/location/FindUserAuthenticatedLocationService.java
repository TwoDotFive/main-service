package com.example.temp.user.service.location;

import com.example.temp.user.service.dto.FindUserAuthenticatedLocationResponse;

public interface FindUserAuthenticatedLocationService {
    FindUserAuthenticatedLocationResponse doService(long userId);
}
