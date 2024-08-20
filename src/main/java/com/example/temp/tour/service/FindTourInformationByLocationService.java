package com.example.temp.tour.service;

import com.example.temp.tour.dto.FindTourInformationByLocationCommand;
import com.example.temp.tour.dto.FindTourInformationResult;

public interface FindTourInformationByLocationService {

    FindTourInformationResult doService(FindTourInformationByLocationCommand command);
}
