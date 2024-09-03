package com.example.temp.tour.service;

import com.example.temp.tour.dto.RegisterTourLogRequest;

public interface RegisterTourLogService {

    Long doService(Long userId, RegisterTourLogRequest command);
}
