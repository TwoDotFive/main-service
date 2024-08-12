package com.example.temp.user.service.impl;

import com.example.temp.common.entity.Address;
import com.example.temp.geo.entity.AuthenticatedLocation;
import com.example.temp.geo.entity.AuthenticatedLocationRepository;
import com.example.temp.user.domain.User;
import com.example.temp.user.domain.UserAuthenticatedLocation;
import com.example.temp.user.domain.UserAuthenticatedLocationRepository;
import com.example.temp.user.domain.UserRepository;
import com.example.temp.user.dto.UserAuthenticatedLocationRequest;
import com.example.temp.user.service.SaveUserAuthenticatedLocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class SaveUserAuthenticatedLocationServiceImpl implements SaveUserAuthenticatedLocationService {
    private final UserRepository userRepository;
    private final UserAuthenticatedLocationRepository userAuthenticatedLocationRepository;
    private final AuthenticatedLocationRepository authenticatedLocationRepository;

    @Override
    public void doService(Long userId, UserAuthenticatedLocationRequest request) {
        User user = userRepository.findByIdOrElseThrow(userId);
        Address address = request.toEntity();

        AuthenticatedLocation firstAuthenticationLocation = authenticatedLocationRepository.save(new AuthenticatedLocation(address));

        UserAuthenticatedLocation entity = UserAuthenticatedLocation.build(user, firstAuthenticationLocation);
        userAuthenticatedLocationRepository.save(entity);
    }
}
