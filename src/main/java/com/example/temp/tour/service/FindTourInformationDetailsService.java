package com.example.temp.tour.service;

import com.example.temp.tour.dto.FindTourInformationDetailsCommand;
import com.example.temp.tour.dto.FindTourInformationDetailsResponse;

public interface FindTourInformationDetailsService {
    FindTourInformationDetailsResponse doService(FindTourInformationDetailsCommand command);
}