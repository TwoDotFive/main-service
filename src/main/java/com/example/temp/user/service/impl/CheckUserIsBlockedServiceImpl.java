package com.example.temp.user.service.impl;

import com.example.temp.common.exception.CustomException;
import com.example.temp.user.domain.BlockedUser;
import com.example.temp.user.domain.BlockedUserRepository;
import com.example.temp.user.domain.User;
import com.example.temp.user.domain.UserRepository;
import com.example.temp.user.service.CheckUserIsBlockedService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CheckUserIsBlockedServiceImpl implements CheckUserIsBlockedService {
    private final UserRepository userRepository;
    private final BlockedUserRepository blockedUserRepository;

    @Override
    public void doService(long userId, long targetUserId) {
        User user = userRepository.findByIdOrElseThrow(userId);
        User targetUser = userRepository.findByIdOrElseThrow(targetUserId);
        Optional<BlockedUser> result = blockedUserRepository.findByUserAndTargetUser(user, targetUser);
        if (result.isPresent()) {
            throw new CustomException(HttpStatus.FORBIDDEN, "차단된 사용자입니다.");
        }
    }
}
