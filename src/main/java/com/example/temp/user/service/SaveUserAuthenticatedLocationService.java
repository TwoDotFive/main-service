package com.example.temp.user.service;

import com.example.temp.user.dto.UserAuthenticatedLocationRequest;

public interface SaveUserAuthenticatedLocationService {
    void doService(Long userId, UserAuthenticatedLocationRequest request);
}
