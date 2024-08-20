package com.example.temp.user.service.location.impl;

import com.example.temp.user.domain.User;
import com.example.temp.user.domain.UserAuthenticatedLocation;
import com.example.temp.user.domain.UserAuthenticatedLocationRepository;
import com.example.temp.user.domain.UserRepository;
import com.example.temp.user.service.location.UpdateUserAuthenticatedLocationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UpdateUserAuthenticatedLocationServiceImpl implements UpdateUserAuthenticatedLocationService {
    private final UserRepository userRepository;
    private final UserAuthenticatedLocationRepository userAuthenticatedLocationRepository;

    @Override
    public void doService(long userId, final long locationId) {
        User user = userRepository.findByIdOrElseThrow(userId);
        List<UserAuthenticatedLocation> target = userAuthenticatedLocationRepository.findByUserOrElseThrow(user);

        target.forEach(o -> {
            o.updateRepresentative(locationId);
            userAuthenticatedLocationRepository.save(o);
        });
    }
}
