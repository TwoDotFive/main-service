package com.example.temp.fanpool.service.impl;

import com.example.temp.common.exception.CustomException;
import com.example.temp.fanpool.domain.Fanpool;
import com.example.temp.fanpool.domain.FanpoolRepository;
import com.example.temp.fanpool.domain.FanpoolUser;
import com.example.temp.fanpool.domain.FanpoolUserRepository;
import com.example.temp.fanpool.dto.command.ParticipateFanpoolCommand;
import com.example.temp.fanpool.service.ParticipateFanpoolService;
import com.example.temp.user.domain.User;
import com.example.temp.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ParticipateFanpoolServiceImpl implements ParticipateFanpoolService {
    private final UserRepository userRepository;
    private final FanpoolRepository fanpoolRepository;
    private final FanpoolUserRepository fanpoolUserRepository;

    @Override
    public void doService(ParticipateFanpoolCommand command) {
        Fanpool fanpool = fanpoolRepository.findByIdOrElseThrow(command.fanpoolId());

        if (!fanpool.isNotHostUser(command.userId())) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "자신이 호스팅한 팬풀에 참여할 수 없습니다.");
        }
        if (fanpool.alreadyFull()) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "신청인원이 가득 찼습니다.");
        }

        User user = userRepository.findByIdOrElseThrow(command.userId());
        Optional<FanpoolUser> found = fanpoolUserRepository.findByFanpoolAndUser(fanpool, user);
        if (found.isPresent()) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "이미 참여 중입니다.");
        }

        FanpoolUser fanpoolUser = FanpoolUser.build(fanpool, user);
        fanpoolUserRepository.save(fanpoolUser);
    }
}
