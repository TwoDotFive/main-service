package com.example.temp.user.service.impl;

import com.example.temp.user.domain.BlockedUser;
import com.example.temp.user.domain.BlockedUserRepository;
import com.example.temp.user.domain.User;
import com.example.temp.user.domain.UserRepository;
import com.example.temp.user.dto.BlockUserCommand;
import com.example.temp.user.service.BlockUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BlockUserServiceImpl implements BlockUserService {
    private final UserRepository userRepository;
    private final BlockedUserRepository blockedUserRepository;

    @Override
    @Transactional
    public void doService(BlockUserCommand command) {
        User user = userRepository.findByIdOrElseThrow(command.userId());
        User targetUser = userRepository.findByIdOrElseThrow(command.targetUserId());

        BlockedUser blocked = BlockedUser.build(user, targetUser);
        blockedUserRepository.save(blocked);
    }
}
