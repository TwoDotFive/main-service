package com.example.temp.user.service.location.impl;

import com.example.temp.common.exception.CustomException;
import com.example.temp.user.domain.UserAuthenticatedLocation;
import com.example.temp.user.domain.UserAuthenticatedLocationRepository;
import com.example.temp.user.service.location.DeleteUserAuthenticatedLocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteUserAuthenticatedLocationServiceImpl implements DeleteUserAuthenticatedLocationService {

    private final UserAuthenticatedLocationRepository userAuthenticatedLocationRepository;

    @Override
    @Transactional
    public void doService(long userId, long id) {
        UserAuthenticatedLocation location = userAuthenticatedLocationRepository.findByIdAndUser(id, userId);

        if (location.isRepresentative()) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "대표 동네를 해제하고 삭제를 시도해주세요");
        }

        userAuthenticatedLocationRepository.delete(location);
    }
}
