package com.example.temp.user.service.location.impl;

import com.example.temp.user.domain.User;
import com.example.temp.user.domain.UserAuthenticatedLocation;
import com.example.temp.user.domain.UserAuthenticatedLocationRepository;
import com.example.temp.user.domain.UserRepository;
import com.example.temp.user.dto.UserAuthenticatedLocationView;
import com.example.temp.user.service.dto.FindUserAuthenticatedLocationResponse;
import com.example.temp.user.service.location.FindUserAuthenticatedLocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindUserAuthenticatedLocationServiceImpl implements FindUserAuthenticatedLocationService {
    private final UserRepository userRepository;
    private final UserAuthenticatedLocationRepository userAuthenticatedLocationRepository;

    @Override
    public FindUserAuthenticatedLocationResponse doService(long userId) {
        User user = userRepository.findByIdOrElseThrow(userId);
        List<UserAuthenticatedLocation> userAuthenticatedLocations = userAuthenticatedLocationRepository.findByUserOrElseThrow(user);

        List<UserAuthenticatedLocationView> result = userAuthenticatedLocations.stream()
                .map(UserAuthenticatedLocationView::new)
                .toList();

        return new FindUserAuthenticatedLocationResponse(result);
    }
}
