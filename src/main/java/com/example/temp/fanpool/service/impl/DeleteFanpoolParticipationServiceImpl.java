package com.example.temp.fanpool.service.impl;

import com.example.temp.common.exception.CustomException;
import com.example.temp.fanpool.domain.Fanpool;
import com.example.temp.fanpool.domain.FanpoolRepository;
import com.example.temp.fanpool.domain.FanpoolUserRepository;
import com.example.temp.fanpool.dto.command.DeleteFanpoolParticipationCommand;
import com.example.temp.fanpool.service.DeleteFanpoolParticipationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class DeleteFanpoolParticipationServiceImpl implements DeleteFanpoolParticipationService {

    private final FanpoolRepository fanpoolRepository;
    private final FanpoolUserRepository fanpoolUserRepository;

    @Override
    public void doService(DeleteFanpoolParticipationCommand command) {
        Fanpool fanpool = fanpoolRepository.findByIdOrElseThrow(command.fanpoolId());
        if (!fanpool.isNotHostUser(command.userId())) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "자신이 호스팅한 팬풀에 참여 취소할 수 없습니다.");
        }

        fanpoolUserRepository.deleteByFanpoolIdAndUserId(fanpool.getId(), command.userId());
    }
}
