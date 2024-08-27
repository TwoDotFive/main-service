package com.example.temp.fanpool.service.impl;

import com.example.temp.fanpool.domain.Fanpool;
import com.example.temp.fanpool.domain.FanpoolRepository;
import com.example.temp.fanpool.dto.command.FindHostedFanpoolByUserCommand;
import com.example.temp.fanpool.dto.FindHostedFanpoolByUserResponse;
import com.example.temp.fanpool.service.FindHostedFanpoolByUserService;
import com.example.temp.user.domain.User;
import com.example.temp.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindHostedFanpoolByUserServiceImpl implements FindHostedFanpoolByUserService {
    private final UserRepository userRepository;
    private final FanpoolRepository fanpoolRepository;

    @Override
    public FindHostedFanpoolByUserResponse doService(FindHostedFanpoolByUserCommand command) {
        User user = userRepository.findByIdOrElseThrow(command.userId());
        List<Fanpool> result = fanpoolRepository.findByHostUserOrderByCreatedAtDesc(user, command.pageable());

        return FindHostedFanpoolByUserResponse.build(result);
    }
}
