package com.example.temp.user.service.impl;

import com.example.temp.user.domain.BlockedUser;
import com.example.temp.user.domain.BlockedUserRepository;
import com.example.temp.user.domain.User;
import com.example.temp.user.domain.UserRepository;
import com.example.temp.user.dto.DeleteUserBlockingCommand;
import com.example.temp.user.service.DeleteUserBlockingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DeleteUserBlockingServiceImpl implements DeleteUserBlockingService {
    private final UserRepository userRepository;
    private final BlockedUserRepository blockedUserRepository;

    @Override
    @Transactional
    public void doService(DeleteUserBlockingCommand command) {
        User user = userRepository.findByIdOrElseThrow(command.userId());
        User targetUser = userRepository.findByIdOrElseThrow(command.targetUserId());
        Optional<BlockedUser> found = blockedUserRepository.findByUserAndTargetUser(user, targetUser);

        found.ifPresent(blockedUserRepository::delete);
    }
}
