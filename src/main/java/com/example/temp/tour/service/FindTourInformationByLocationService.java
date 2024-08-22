package com.example.temp.tour.service;

import com.example.temp.tour.dto.FindTourInformationByLocationCommand;
import com.example.temp.tour.dto.FindTourInformationResponse;

public interface FindTourInformationByLocationService {

    FindTourInformationResponse doService(FindTourInformationByLocationCommand command);
}
