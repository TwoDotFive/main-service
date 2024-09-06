package com.example.temp.tour.service.impl;

import com.example.temp.tour.dto.FindTourInformationDetailsCommand;
import com.example.temp.tour.dto.FindTourInformationDetailsResponse;
import com.example.temp.tour.service.FindTourDetailsService;
import com.example.temp.tour.service.FindTourInformationDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindTourDetailsServiceImpl implements FindTourDetailsService {

    private final FindTourDetailsServiceFactory findTourDetailsServiceFactory;

    @Override
    public FindTourInformationDetailsResponse doService(FindTourInformationDetailsCommand command) {
        FindTourInformationDetailsService service = findTourDetailsServiceFactory.get(command.contentTypeId());
        return service.doService(command);
    }
}
