package com.example.temp.user.service.impl;

import com.example.temp.common.entity.ReportedUser;
import com.example.temp.common.entity.ReportedUserRepository;
import com.example.temp.user.domain.User;
import com.example.temp.user.domain.UserRepository;
import com.example.temp.user.dto.BlockUserCommand;
import com.example.temp.user.dto.ReportUserCommand;
import com.example.temp.user.service.BlockUserService;
import com.example.temp.user.service.ReportUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReportUserServiceImpl implements ReportUserService {
    private final UserRepository userRepository;
    private final ReportedUserRepository reportRepository;
    private final BlockUserService blockUserService;

    @Override
    @Transactional
    public void doService(ReportUserCommand command) {
        User user = userRepository.findByIdOrElseThrow(command.userId());
        User targetUser = userRepository.findByIdOrElseThrow(command.targetUserId());
        ReportedUser report = ReportedUser.build(user, targetUser, command.content());
        reportRepository.save(report);

        BlockUserCommand blocking = new BlockUserCommand(command.userId(), command.targetUserId());
        blockUserService.doService(blocking);
    }
}
