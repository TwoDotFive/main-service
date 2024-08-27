package com.example.temp.fanpool.service.impl;

import com.example.temp.common.exception.CustomException;
import com.example.temp.fanpool.domain.Fanpool;
import com.example.temp.fanpool.domain.FanpoolRepository;
import com.example.temp.fanpool.dto.UpdateFanpoolStateCommand;
import com.example.temp.fanpool.service.UpdateFanpoolStateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UpdateFanpoolStateServiceImpl implements UpdateFanpoolStateService {
    private final FanpoolRepository fanpoolRepository;

    @Override
    public void doService(UpdateFanpoolStateCommand command) {
        Fanpool target = fanpoolRepository.findByIdOrElseThrow(command.getFanpoolId());

        if (target.isNotHostUser(command.getUserId())) {
            throw new CustomException(HttpStatus.FORBIDDEN, "호스트한 유저만 변경 가능합니다.");
        }

        target.updateState(command.getBody().state());
    }
}
