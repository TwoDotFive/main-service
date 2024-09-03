package com.example.temp.tour.service;

import com.example.temp.tour.dto.UpdateTourLogRequest;

public interface UpdateTourLogService {

    void doService(Long userId, UpdateTourLogRequest request);
}
