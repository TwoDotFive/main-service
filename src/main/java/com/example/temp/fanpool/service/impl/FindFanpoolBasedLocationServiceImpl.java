package com.example.temp.fanpool.service.impl;

import com.example.temp.fanpool.domain.Fanpool;
import com.example.temp.fanpool.domain.FanpoolRepository;
import com.example.temp.fanpool.dto.FindFanpoolBasedLocationCommand;
import com.example.temp.fanpool.dto.FindFanpoolBasedLocationResponse;
import com.example.temp.fanpool.service.FindFanpoolBasedLocationService;
import com.example.temp.user.domain.User;
import com.example.temp.user.domain.UserAuthenticatedLocation;
import com.example.temp.user.domain.UserAuthenticatedLocationRepository;
import com.example.temp.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindFanpoolBasedLocationServiceImpl implements FindFanpoolBasedLocationService {
    private final UserRepository userRepository;
    private final UserAuthenticatedLocationRepository userAuthenticatedLocationRepository;
    private final FanpoolRepository fanpoolRepository;

    @Override
    public List<FindFanpoolBasedLocationResponse> doService(long userId, FindFanpoolBasedLocationCommand command) {
        User user = userRepository.findByIdOrElseThrow(userId);
        UserAuthenticatedLocation userAuthenticatedLocation = userAuthenticatedLocationRepository.findByUserOrElseThrow(user);

        List<Fanpool> found = fanpoolRepository.findByDongCd(userAuthenticatedLocation.getFirstLocationDongCd());

        return found.stream()
                .map(FindFanpoolBasedLocationResponse::build)
                .toList();
    }
}
