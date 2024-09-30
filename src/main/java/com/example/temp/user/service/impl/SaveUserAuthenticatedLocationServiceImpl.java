package com.example.temp.user.service.impl;

import com.example.temp.common.exception.CustomException;
import com.example.temp.geo.entity.Address;
import com.example.temp.geo.entity.AddressRepository;
import com.example.temp.user.domain.User;
import com.example.temp.user.domain.UserAuthenticatedLocation;
import com.example.temp.user.domain.UserAuthenticatedLocationRepository;
import com.example.temp.user.domain.UserRepository;
import com.example.temp.user.dto.UserAuthenticatedLocationRequest;
import com.example.temp.user.service.SaveUserAuthenticatedLocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class SaveUserAuthenticatedLocationServiceImpl implements SaveUserAuthenticatedLocationService {
    private static final int LOCATION_LIMIT = 2;
    private final UserRepository userRepository;
    private final UserAuthenticatedLocationRepository userAuthenticatedLocationRepository;
    private final AddressRepository addressRepository;

    @Override
    public void doService(Long userId, UserAuthenticatedLocationRequest request) {
        User user = userRepository.findByIdOrElseThrow(userId);
        validateLocation(user, request.isRepresentativeLocation());

        Address address = request.toEntity();
        Address savedAddr = addressRepository.save(address);

        UserAuthenticatedLocation entity = UserAuthenticatedLocation.build(user, savedAddr, request.isRepresentativeLocation());
        UserAuthenticatedLocation saved = userAuthenticatedLocationRepository.save(entity);
    }

    private void validateLocation(User user, boolean representativeLocation) {
        List<UserAuthenticatedLocation> authenticated = userAuthenticatedLocationRepository.findByUser(user);
        if (authenticated.size() >= LOCATION_LIMIT) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "동네 인증 가능 개수를 초과했습니다.");
        }

        for (var loc : authenticated) {
            if (loc.isRepresentative() && representativeLocation) {
                throw new CustomException(HttpStatus.BAD_REQUEST, "이미 대표로 설정된 동네가 존재합니다.");
            }
        }
    }
}
